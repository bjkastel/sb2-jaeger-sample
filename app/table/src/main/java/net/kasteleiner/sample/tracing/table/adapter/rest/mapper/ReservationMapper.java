package net.kasteleiner.sample.tracing.table.adapter.rest.mapper;

import net.kasteleiner.sample.tracing.table.adapter.jpa.entities.ReservationEntity;
import net.kasteleiner.sample.tracing.table.adapter.rest.dto.ReservationDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    ReservationEntity dtoToEntity(ReservationDto reservationDto);
}
