package net.kasteleiner.sample.tracing.table.domain;

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
