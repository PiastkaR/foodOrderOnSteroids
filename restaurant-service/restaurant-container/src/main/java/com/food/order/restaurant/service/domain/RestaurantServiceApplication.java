package com.food.order.restaurant.service.domain;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = { "com.food.order.restaurant.service.dataaccess", "com.food.order.dataaccess" })
@EntityScan(basePackages = { "com.food.order.restaurant.service.dataaccess", "com.food.order.dataaccess" })
@SpringBootApplication(scanBasePackages = "com.food.order.system")
public class RestaurantServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestaurantServiceApplication.class, args);
    }
}
