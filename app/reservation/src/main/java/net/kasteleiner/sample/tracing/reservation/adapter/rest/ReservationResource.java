package net.kasteleiner.sample.tracing.reservation.adapter.rest;

import lombok.RequiredArgsConstructor;
import net.kasteleiner.sample.tracing.reservation.application.ReservationApplication;
import net.kasteleiner.sample.tracing.reservation.domain.ReservationDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class ReservationResource {
    private final ReservationApplication application;

    @RequestMapping(
            value = "/reservation",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity<ReservationDto> receiveReservation() throws InterruptedException {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setFirstName("Tim");
        reservationDto.setLastName("Struppi");
        reservationDto.setMobileNumber("+49 152 12311121");
        reservationDto.setReservedSeats(2);
        reservationDto.setReservationTime(LocalDateTime.now());

        application.reserveTable(reservationDto);
        return new ResponseEntity<>(reservationDto, HttpStatus.CREATED);
    }
}
