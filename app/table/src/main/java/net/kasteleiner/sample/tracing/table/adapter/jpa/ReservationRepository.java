package net.kasteleiner.sample.tracing.table.adapter.jpa;

import net.kasteleiner.sample.tracing.table.adapter.jpa.entities.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
}
