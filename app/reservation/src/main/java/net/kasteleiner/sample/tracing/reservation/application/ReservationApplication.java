package net.kasteleiner.sample.tracing.reservation.application;

import lombok.RequiredArgsConstructor;
import net.kasteleiner.sample.tracing.reservation.domain.JmsSender;
import net.kasteleiner.sample.tracing.reservation.domain.ReservationDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class ReservationApplication {

    private final JmsSender jmsSender;
    private final RestTemplateBuilder restTemplateBuilder;

    public void sendSms(String mobileNumber, String text) {
        jmsSender.sendSms(mobileNumber, text);
    }

    public ReservationDto reserveTable(ReservationDto reservation) {


        String messageText = String.format("Hello %s, thank you for your reservation", reservation.getFirstName());
        sendSms(reservation.getMobileNumber(), messageText);

        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.put("http://localhost:8081/reservation", reservation);

        return reservation;
    }
}