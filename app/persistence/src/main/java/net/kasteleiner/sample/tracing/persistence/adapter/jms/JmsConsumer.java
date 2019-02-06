package net.kasteleiner.sample.tracing.persistence.adapter.jms;

import lombok.RequiredArgsConstructor;
import net.kasteleiner.sample.tracing.persistence.adapter.jpa.RequestLogRepository;
import net.kasteleiner.sample.tracing.persistence.adapter.jpa.entities.RequestLog;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JmsConsumer {
    private final RequestLogRepository requestLogRepository;

    @JmsListener(destination = "requestLog")
    public void receiveMessage(RequestLog requestLog) {
        requestLogRepository.save(requestLog);
    }
}
