package net.kasteleiner.sample.tracing.table.adapter.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"net.kasteleiner.sample.tracing.table.adapter.jpa.entities"} )
@EnableJpaRepositories(basePackages = {"net.kasteleiner.sample.tracing.table.adapter.jpa"})
public class JpaConfiguration {
}
