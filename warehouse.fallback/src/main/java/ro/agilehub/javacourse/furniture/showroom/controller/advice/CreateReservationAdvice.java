package ro.agilehub.javacourse.furniture.showroom.controller.advice;

import static org.springframework.core.Ordered.LOWEST_PRECEDENCE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import ro.agilehub.javacourse.furniture.showroom.client.core.impl.FallbackReservationException;
import ro.agilehub.javacourse.furniture.showroom.producer.ReservationProducer;

@Order(LOWEST_PRECEDENCE)
@ControllerAdvice
public class CreateReservationAdvice {

	@Autowired
	private ReservationProducer reservationProducer;

	@ExceptionHandler(value = FallbackReservationException.class)
	protected ResponseEntity<Void> handleFallbackReservationException(
			FallbackReservationException ex, WebRequest request) {
		reservationProducer.send(ex.getReservation());
		return ResponseEntity.accepted().build();
	}
}
