package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class PaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(PaymentServiceApplication.class);
        app.setAllowBeanDefinitionOverriding(true);
        app.run(args);
	}

}
