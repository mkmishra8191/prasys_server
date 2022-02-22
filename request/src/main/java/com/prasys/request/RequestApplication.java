package com.prasys.request;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.prasys")
@EntityScan(basePackages={"com.prasys.framework.entity"})
@EnableJpaRepositories(basePackages={"com.prasys.framework.repo"})
public class RequestApplication {
    public static void main(String[] args) {
        SpringApplication.run(RequestApplication.class, args);

    }
}
