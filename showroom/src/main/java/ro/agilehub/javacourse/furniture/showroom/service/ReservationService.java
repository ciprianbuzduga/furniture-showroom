package ro.agilehub.javacourse.furniture.showroom.service;

import java.util.List;

import ro.agilehub.javacourse.furniture.showroom.api.model.ReservationDTO;

public interface ReservationService {

	String addReservation(ReservationDTO reservationDTO);

	ReservationDTO getReservation(String id);

	boolean cancelReservationById(String id);

	List<ReservationDTO> getAllReservations();

}
