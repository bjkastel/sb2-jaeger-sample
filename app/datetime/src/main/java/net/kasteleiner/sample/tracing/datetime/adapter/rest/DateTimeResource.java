package net.kasteleiner.sample.tracing.datetime.adapter.rest;

import io.opentracing.Tracer;
import lombok.RequiredArgsConstructor;
import net.kasteleiner.sample.tracing.datetime.adapter.rest.dto.DateTimeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Random;

@RestController
@RequiredArgsConstructor
public class DateTimeResource {
    private final Tracer tracer;

    @RequestMapping(
            value = "/datetime/current",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    @ResponseBody
    public ResponseEntity<DateTimeDto> datetimeCurrent() throws InterruptedException {
        Random random = new Random();
        int waitTime = random.nextInt(5000);

        // ----------------------------------------------------------------------
        // Add waitTime as Tag to current Span
        tracer.activeSpan().setTag("waitTime", waitTime);
        // ----------------------------------------------------------------------

        Thread.sleep(waitTime);
        DateTimeDto dateTimeDto = new DateTimeDto();
        dateTimeDto.setDateTime(LocalDateTime.now());

        return new ResponseEntity<>(dateTimeDto, HttpStatus.OK);
    }
}
