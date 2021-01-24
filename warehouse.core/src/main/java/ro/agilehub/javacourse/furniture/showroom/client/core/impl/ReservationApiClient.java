package ro.agilehub.javacourse.furniture.showroom.client.core.impl;

import org.springframework.cloud.openfeign.FeignClient;

import ro.agilehub.javacourse.furniture.warehouse.client.core.specification.ReservationApi;

@FeignClient(name = "reservationApiClient", url = "${reservations.url:http://localhost:8080}")
public interface ReservationApiClient extends ReservationApi {

}
