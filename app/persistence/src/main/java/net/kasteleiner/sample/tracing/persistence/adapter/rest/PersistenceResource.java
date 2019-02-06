package net.kasteleiner.sample.tracing.persistence.adapter.rest;

import lombok.RequiredArgsConstructor;
import net.kasteleiner.sample.tracing.persistence.adapter.jms.dto.DateTimeDto;
import net.kasteleiner.sample.tracing.persistence.adapter.jpa.RequestLogRepository;
import net.kasteleiner.sample.tracing.persistence.adapter.jpa.entities.RequestLog;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PersistenceResource {
    private final RequestLogRepository requestLogRepository;

    @RequestMapping(
            value = "/requestlogfast",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity<List<RequestLog>> getRequestLogFast() {
        List<RequestLog> requestLogs = new ArrayList<>();
        Iterable<RequestLog> requestLogIterable = requestLogRepository.findAll();
        requestLogIterable.iterator().forEachRemaining(requestLogs::add);
        return new ResponseEntity<>(requestLogs, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/requestlog",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity<List<RequestLog>> getRequestLog() {
        List<RequestLog> requestLogs = new ArrayList<>();

        long count = requestLogRepository.count();
        for  (long i = 0; i < count; i++) {
            Optional<RequestLog> requestLog = requestLogRepository.findById(i);
            requestLog.ifPresent(requestLogs::add);
        }

        return new ResponseEntity<>(requestLogs, HttpStatus.OK);
    }
}
