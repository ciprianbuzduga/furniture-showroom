package ro.agilehub.javacourse.furniture.showroom.client.core.configuration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.auth.BasicAuthRequestInterceptor;
import ro.agilehub.javacourse.furniture.showroom.client.core.impl.ReservationApiClient;

@Configuration
@EnableFeignClients(basePackageClasses = ReservationApiClient.class)
public class FeignConfiguration {

	@Bean
	public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
		return new BasicAuthRequestInterceptor("user", "password");
	}
}
