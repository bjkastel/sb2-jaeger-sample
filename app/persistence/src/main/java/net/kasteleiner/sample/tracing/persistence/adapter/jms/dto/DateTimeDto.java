package net.kasteleiner.sample.tracing.persistence.adapter.jms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateTimeDto {
    private LocalDateTime dateTime;
}
