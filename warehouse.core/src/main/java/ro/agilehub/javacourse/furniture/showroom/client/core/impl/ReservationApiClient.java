package ro.agilehub.javacourse.furniture.showroom.client.core.impl;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import ro.agilehub.javacourse.furniture.warehouse.client.core.model.ReservationDTO;
import ro.agilehub.javacourse.furniture.warehouse.client.core.specification.ReservationApi;

@FeignClient(name = "reservationApiClient", url = "${reservations.url:http://localhost:8080}")
public interface ReservationApiClient extends ReservationApi {

	String FALLBACK_RESERVATION = "fallBackReservation";

	@Override
	@CircuitBreaker(name = FALLBACK_RESERVATION, fallbackMethod = "backupCreateReservation")
	@RateLimiter(name = FALLBACK_RESERVATION)
	@RequestMapping(value = "/reservations", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	ResponseEntity<Void> createResevation(@Valid ReservationDTO reservationDTO);

	default ResponseEntity<Void> backupCreateReservation(ReservationDTO reservationDTO,
			CallNotPermittedException exception) {
		throw new FallbackReservationException(reservationDTO);
	}

	default ResponseEntity<Void> backupCreateReservation(ReservationDTO reservationDTO,
			Exception exception) {
		throw new FallbackReservationException(reservationDTO);
	}
}
