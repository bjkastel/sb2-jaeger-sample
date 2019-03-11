package net.kasteleiner.sample.tracing.reservation.adapter.jmsSender;

import lombok.RequiredArgsConstructor;
import net.kasteleiner.sample.tracing.reservation.adapter.jmsSender.dto.SmsDto;
import net.kasteleiner.sample.tracing.reservation.domain.JmsSender;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JmsSenderImpl implements JmsSender {
    private final JmsTemplate jmsTemplate;

    @Override public void sendSms(String mobileNumer, String text) {
        SmsDto sms = new SmsDto();
        sms.setNumber(mobileNumer);
        sms.setText(text);

        jmsTemplate.convertAndSend("send_sms", sms);
    }
}
