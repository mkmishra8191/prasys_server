package com.prasys.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.prasys")
@EntityScan(basePackages={"com.prasys.framework.entity"})
@EnableJpaRepositories(basePackages={"com.prasys.framework.repo"})
public class CompanyApplication {
    public static void main(String[] args) {
        SpringApplication.run(CompanyApplication.class, args);

    }
}
