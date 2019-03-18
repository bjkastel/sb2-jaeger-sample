package net.kasteleiner.sample.tracing.table.adapter.jpa.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "reservation")
@Data
public class ReservationEntity {
    @Id
    @GenericGenerator(
            name = "id_generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "id_sequence"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(generator = "id_sequence")
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private Long id;

    private String firstName;
    private String lastName;
    private Integer reservedSeats;
    private LocalDateTime reservationTime;
    private String mobileNumber;
}
