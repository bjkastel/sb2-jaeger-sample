package net.kasteleiner.sample.tracing.sms.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.kasteleiner.sample.tracing.sms.domain.SmsDto;
import net.kasteleiner.sample.tracing.sms.domain.WebSocket;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2
public class SmsApplication {
    private final WebSocket webSocket;

    public void pushSMS(SmsDto sms) {
        log.info(String.format("Sent SMS: %s, %s", sms.getNumber(), sms.getText()));
        webSocket.sendWebSocketUpdate(sms);
    }
}
