package net.kasteleiner.sample.tracing.table.adapter.bootCli;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@ComponentScan(basePackages = "net.kasteleiner.sample.tracing.table")
@EnableAsync
public class TableConfiguration {
}
