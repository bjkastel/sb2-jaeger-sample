package net.kasteleiner.sample.tracing.reservation.adapter.jmsSender.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SmsDto {
    private String number;
    private String text;
}
