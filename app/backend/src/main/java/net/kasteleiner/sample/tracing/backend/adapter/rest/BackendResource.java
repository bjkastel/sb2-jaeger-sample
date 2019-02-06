package net.kasteleiner.sample.tracing.backend.adapter.rest;

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

    @RequestMapping(
            value = "/trigger",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity<RequestLogDto> trigger() throws InterruptedException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestLogDto requestLogDto = new RequestLogDto();

        ResponseEntity<DateTimeDto> startDateRespEntity = restTemplate.getForEntity("http://localhost:8082/datetime/current", DateTimeDto.class);
        requestLogDto.setStartDate(startDateRespEntity.getBody().getDateTime());

        Random random = new Random();
        int waitTime = random.nextInt(5000);
        requestLogDto.setWaitTime(waitTime);
        Thread.sleep(waitTime);

        ResponseEntity<DateTimeDto> endDateRespEntity = restTemplate.getForEntity("http://localhost:8082/datetime/current", DateTimeDto.class);
        requestLogDto.setEndDate(endDateRespEntity.getBody().getDateTime());

        jmsTemplate.convertAndSend("requestLog", requestLogDto);
        return new ResponseEntity<>(requestLogDto, HttpStatus.OK);
    }
}
