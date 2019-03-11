package net.kasteleiner.sample.tracing.table.adapter.rest;

import lombok.RequiredArgsConstructor;
import net.kasteleiner.sample.tracing.table.adapter.jpa.entities.ReservationEntity;
import net.kasteleiner.sample.tracing.table.adapter.rest.dto.ReservationDto;
import net.kasteleiner.sample.tracing.table.adapter.rest.mapper.ReservationMapper;
import net.kasteleiner.sample.tracing.table.application.TableApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReservationResource {
    private final TableApplication application;
    private final ReservationMapper reservationMapper;

    @RequestMapping(
            value = "/reservations/fast",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity<List<ReservationEntity>> getReservationLogFast() {
        return new ResponseEntity<>(application.getReservationListFast(), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/reservations/slow",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity<List<ReservationEntity>> getReservationLogSlow() {
        return new ResponseEntity<>(application.getReservationListSlow(), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/reservation",
            method = RequestMethod.PUT
    )
    public ResponseEntity<ReservationEntity> putReservation(@RequestBody ReservationDto reservationDto) {
        ReservationEntity reservationEntity = reservationMapper.dtoToEntity(reservationDto);
        application.processReservation(reservationEntity);

        return new ResponseEntity<>(reservationEntity, HttpStatus.CREATED);
    }
}
