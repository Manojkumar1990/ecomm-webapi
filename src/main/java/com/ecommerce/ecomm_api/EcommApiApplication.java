package com.ecommerce.ecomm_api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.ecommerce")
@SpringBootApplication
public class EcommApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommApiApplication.class, args);
	}

}
