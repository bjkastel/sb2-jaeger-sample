package net.kasteleiner.sample.tracing.datetime.adapter.rest;

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
public class DateTimeResource {

    @RequestMapping(
            value = "/datetime/current",
            method = RequestMethod.GET,
            produces = "application/json"
    )
    @ResponseBody
    public ResponseEntity<DateTimeDto> datetimeCurrent() throws InterruptedException {
        Random random = new Random();
        Thread.sleep(random.nextInt(5000));
        DateTimeDto dateTimeDto = new DateTimeDto();
        dateTimeDto.setDateTime(LocalDateTime.now());
        return new ResponseEntity<>(dateTimeDto, HttpStatus.OK);
    }
}
