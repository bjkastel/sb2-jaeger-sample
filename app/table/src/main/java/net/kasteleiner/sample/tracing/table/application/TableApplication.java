package net.kasteleiner.sample.tracing.table.application;

import io.opentracing.Scope;
import io.opentracing.Tracer;
import lombok.RequiredArgsConstructor;
import net.kasteleiner.sample.tracing.table.adapter.jpa.ReservationRepository;
import net.kasteleiner.sample.tracing.table.adapter.jpa.entities.ReservationEntity;
import net.kasteleiner.sample.tracing.table.domain.JmsSender;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class TableApplication {
    private final Tracer tracer;
    private final JmsSender jmsSender;
    private final ReservationRepository reservationRepository;

    public void sendSms(String mobileNumber, String text) {
        jmsSender.sendSms(mobileNumber, text);
    }

    public List<ReservationEntity> getReservationListFast() {
        List<ReservationEntity> reservationEntities = new ArrayList<>();
        Iterable<ReservationEntity> reservationIterable = reservationRepository.findAll();
        reservationIterable.iterator().forEachRemaining(reservationEntities::add);
        return reservationEntities;
    }

    @RequestMapping(
            value = "/reservations/slow",
            method = RequestMethod.GET
    )
    @ResponseBody
    public List<ReservationEntity> getReservationListSlow() {
        List<ReservationEntity> reservationEntities = new ArrayList<>();

        long count = reservationRepository.count();
        for  (long i = 0; i < count; i++) {
            Optional<ReservationEntity> reservation = reservationRepository.findById(i);
            reservation.ifPresent(reservationEntities::add);
        }

        return reservationEntities;
    }

    public void processReservation(ReservationEntity reservationEntity) {
        reservationRepository.save(reservationEntity);

        int waitTime;

        // ----------------------------------------------------------------------
        // Open own Span for the megaFancyBusinessCalculation.
        // It's using try-with-resources feature to close the span automatically.
        try (Scope scope = tracer.buildSpan("megaFancyBusinessCalculation").startActive(true)) {
            waitTime = megaFancyBusinessCalculation();
            scope.span().setTag("waitTime", waitTime);
        }
        // ----------------------------------------------------------------------

        sendSms(reservationEntity.getMobileNumber(), "Your reservation has been accepted!");
    }

    private int megaFancyBusinessCalculation() {
        Random random = new Random();
        int waitTime = random.nextInt(500);
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            throw new RuntimeException("Hurry up, can't wait to finish...", e);
        }
        return waitTime;
    }
}
