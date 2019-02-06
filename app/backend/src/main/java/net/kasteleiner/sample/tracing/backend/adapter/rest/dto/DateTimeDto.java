package net.kasteleiner.sample.tracing.backend.adapter.rest.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DateTimeDto {
    private LocalDateTime dateTime;
}
