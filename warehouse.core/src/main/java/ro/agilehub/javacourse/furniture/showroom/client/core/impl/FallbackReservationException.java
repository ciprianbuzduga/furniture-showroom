package ro.agilehub.javacourse.furniture.showroom.client.core.impl;

import ro.agilehub.javacourse.furniture.warehouse.client.core.model.ReservationDTO;

public class FallbackReservationException extends RuntimeException {
	private static final long serialVersionUID = 738099501247558947L;

	private final ReservationDTO reservation;

	public FallbackReservationException() {
		this.reservation = null;
	}

	public FallbackReservationException(ReservationDTO reservation) {
		this.reservation = reservation;
	}

	public ReservationDTO getReservation() {
		return reservation;
	}

}
