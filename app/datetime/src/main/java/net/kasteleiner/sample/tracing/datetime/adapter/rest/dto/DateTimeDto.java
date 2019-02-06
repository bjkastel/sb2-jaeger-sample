package net.kasteleiner.sample.tracing.datetime.adapter.rest.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DateTimeDto {
    private LocalDateTime dateTime;
}
