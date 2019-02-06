package net.kasteleiner.sample.tracing.backend.adapter.rest.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestLogDto {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer waitTime;
}
