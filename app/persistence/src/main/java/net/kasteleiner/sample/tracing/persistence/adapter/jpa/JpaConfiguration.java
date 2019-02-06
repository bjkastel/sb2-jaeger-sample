package net.kasteleiner.sample.tracing.persistence.adapter.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"net.kasteleiner.sample.tracing.persistence.adapter.jpa.entities"} )
@EnableJpaRepositories(basePackages = {"net.kasteleiner.sample.tracing.persistence.adapter.jpa"})
public class JpaConfiguration {
}
