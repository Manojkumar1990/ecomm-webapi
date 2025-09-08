package com.ecommerce.ecommwebapi;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = { "com.ecommerce.ecommwebapi"})
public class EcommWebApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommWebApiApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
