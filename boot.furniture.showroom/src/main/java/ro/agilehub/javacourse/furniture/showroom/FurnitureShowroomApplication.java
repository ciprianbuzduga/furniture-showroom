package ro.agilehub.javacourse.furniture.showroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "ro.agilehub.javacourse.furniture.showroom")
@SpringBootApplication
public class FurnitureShowroomApplication {

    public static void main(final String[] args) {
        SpringApplication.run(FurnitureShowroomApplication.class, args);
    }
}
