package ro.agilehub.javacourse.furniture.showroom.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ro.agilehub.javacourse.furniture.showroom.api.model.ReservationDTO;
import ro.agilehub.javacourse.furniture.showroom.mapper.ReservationMapper;
import ro.agilehub.javacourse.furniture.showroom.service.ReservationService;
import ro.agilehub.javacourse.furniture.warehouse.client.core.specification.ReservationApi;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

	private final ReservationApi reservationApi;
	private final ReservationMapper mapper;

	@Override
	public String addReservation(ReservationDTO reservationDTO) {
		ResponseEntity<Void> resp = reservationApi.createResevation(mapper
				.mapToApiReservationDTO(reservationDTO));
		//http://localhost:8080/reservations/600c7ed91399717fb602f909
		HttpHeaders headers = resp.getHeaders();
		if(headers != null && headers.getLocation() != null) {
			String[] paths = headers.getLocation().getPath().split("/");
			String newId = paths[paths.length-1];
			return newId;
		}
		return null;
	}

	@Override
	public ReservationDTO getReservation(String id) {
		ResponseEntity<ro.agilehub.javacourse.furniture.warehouse.client.core.model.ReservationDTO> resp = reservationApi.getReservation(id);
		if(resp.hasBody()) {
			return mapper.mapToReservationDTO(resp.getBody());
		}
		throw new NoSuchElementException("No reservation found with id " + id);
	}

	@Override
	public boolean cancelReservationById(String id) {
		ResponseEntity<Void> resp = reservationApi.deleteReservation(id);
		if(resp.getStatusCode() == HttpStatus.NO_CONTENT)
			return true;
		return false;
	}

	@Override
	public List<ReservationDTO> getAllReservations() {
		ResponseEntity<List<ro.agilehub.javacourse.furniture.warehouse.client.core.model.ReservationDTO>> resp = reservationApi.getReservations();
		if(resp.hasBody())
			return resp.getBody().stream().map(mapper::mapToReservationDTO)
					.collect(Collectors.toList());
		return Collections.emptyList();
	}

}
