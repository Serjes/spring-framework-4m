package ru.otus.dz31;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrixDashboard
public class Main {

    public static void main(String[] args) throws Exception {

        SpringApplication.run(Main.class);

    }
}
