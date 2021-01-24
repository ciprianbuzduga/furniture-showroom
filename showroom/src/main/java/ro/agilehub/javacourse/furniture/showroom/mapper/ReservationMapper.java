package ro.agilehub.javacourse.furniture.showroom.mapper;

import org.mapstruct.Mapper;

import ro.agilehub.javacourse.furniture.showroom.api.model.ReservationDTO;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

	ReservationDTO mapToReservationDTO(ro.agilehub.javacourse.furniture.warehouse.client.core.model.ReservationDTO api);

	ro.agilehub.javacourse.furniture.warehouse.client.core.model.ReservationDTO mapToApiReservationDTO(
			ReservationDTO dto);
}
