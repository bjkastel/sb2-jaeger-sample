package net.kasteleiner.sample.tracing.table.adapter.jpa.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "reservation")
@Data
public class ReservationEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "id_sequence")
    @SequenceGenerator(
            name = "id_generator",
            sequenceName = "id_sequence")
    private Long id;

    private String firstName;
    private String lastName;
    private Integer reservedSeats;
    private LocalDateTime reservationTime;
    private String mobileNumber;
}
