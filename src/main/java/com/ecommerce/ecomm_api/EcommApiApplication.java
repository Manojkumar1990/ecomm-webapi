package com.ecommerce.ecomm_api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "com.ecommerce.ecomm_api"})
public class EcommApiApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(EcommApiApplication.class, args);
		String[] beans = context.getBeanDefinitionNames();
//		for (int i = 0; i < beans.length; i++) {
//			String bean =  beans[i];
//			System.out.println(bean);
//
//
//		}
	}

}
