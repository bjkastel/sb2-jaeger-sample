package net.kasteleiner.sample.tracing.reservation.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationDto {
    private String firstName;
    private String lastName;
    private Integer reservedSeats;
    private LocalDateTime reservationTime;
    private String mobileNumber;
}
