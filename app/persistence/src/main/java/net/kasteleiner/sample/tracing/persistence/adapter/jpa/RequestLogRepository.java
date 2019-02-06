package net.kasteleiner.sample.tracing.persistence.adapter.jpa;

import net.kasteleiner.sample.tracing.persistence.adapter.jpa.entities.RequestLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestLogRepository extends JpaRepository<RequestLog, Long> {
}
