package ro.agilehub.javacourse.furniture.showroom.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import ro.agilehub.javacourse.furniture.warehouse.client.core.model.ReservationDTO;

@Component
@RequiredArgsConstructor
public class ReservationProducer {

	@Value("${activemq.destination}")
	private String destination;

	private final JmsTemplate jmsTemplate;

	public void send(ReservationDTO reservation) {
		if (reservation == null)
			return;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(reservation);

			jmsTemplate.convertAndSend(destination, json);
			System.out.println("Sent reservation to queue...");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
