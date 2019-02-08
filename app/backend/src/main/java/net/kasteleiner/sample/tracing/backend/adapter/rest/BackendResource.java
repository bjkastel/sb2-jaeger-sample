package net.kasteleiner.sample.tracing.backend.adapter.rest;

import io.opentracing.Scope;
import io.opentracing.Tracer;
import lombok.RequiredArgsConstructor;
import net.kasteleiner.sample.tracing.backend.adapter.rest.dto.DateTimeDto;
import net.kasteleiner.sample.tracing.backend.adapter.rest.dto.RequestLogDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@RestController
@RequiredArgsConstructor
public class BackendResource {
    private final RestTemplateBuilder restTemplateBuilder;
    private final JmsTemplate jmsTemplate;
    private final Tracer tracer;

    @RequestMapping(
            value = "/trigger",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity<RequestLogDto> trigger() throws InterruptedException {
        RequestLogDto requestLogDto = new RequestLogDto();

        // Retrieve start date time.
        ResponseEntity<DateTimeDto> startDateRespEntity = retrieveCurrentDateTime();
        requestLogDto.setStartDate(startDateRespEntity.getBody().getDateTime());

        int waitTime;

        // ----------------------------------------------------------------------
        // Open own Span for the megaFancyBusinessCalculation.
        // It's using try-with-resources feature to close the span automatically.
        try (Scope scope = tracer.buildSpan("megaFancyBusinessCalculation").startActive(true)) {
            waitTime = megaFancyBusinessCalculation();
            scope.span().setTag("waitTime", waitTime);
        }
        // ----------------------------------------------------------------------

        // Add wait time to request log.
        requestLogDto.setWaitTime(waitTime);

        // Retrieve end date time.
        ResponseEntity<DateTimeDto> endDateRespEntity = retrieveCurrentDateTime();
        requestLogDto.setEndDate(endDateRespEntity.getBody().getDateTime());

        // Send request log to persistence service via JMS queue.
        jmsTemplate.convertAndSend("requestLog", requestLogDto);

        // Return request log object.
        return new ResponseEntity<>(requestLogDto, HttpStatus.OK);
    }

    private ResponseEntity<DateTimeDto> retrieveCurrentDateTime() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        return restTemplate.getForEntity("http://localhost:8082/datetime/current", DateTimeDto.class);
    }

    private int megaFancyBusinessCalculation() throws InterruptedException {
        Random random = new Random();
        int waitTime = random.nextInt(5000);
        Thread.sleep(waitTime);
        return waitTime;
    }
}
