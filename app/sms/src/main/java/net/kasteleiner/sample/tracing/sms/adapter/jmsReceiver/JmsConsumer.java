package net.kasteleiner.sample.tracing.sms.adapter.jmsReceiver;

import lombok.RequiredArgsConstructor;
import net.kasteleiner.sample.tracing.sms.application.SmsApplication;
import net.kasteleiner.sample.tracing.sms.domain.SmsDto;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JmsConsumer {
    private final SmsApplication application;

    @JmsListener(destination = "send_sms")
    public void receiveMessage(SmsDto sms) {
        application.pushSMS(sms);
    }
}
