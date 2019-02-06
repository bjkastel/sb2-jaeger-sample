package net.kasteleiner.sample.tracing.persistence.adapter.jpa.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "requestlog")
@Data
public class RequestLog {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "id_generator",
            sequenceName = "id_sequence")
    private Long id;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer waitTime;
}
