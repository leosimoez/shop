package br.com.lm.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ShopServicesSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopServicesSpringApplication.class, args);
	}
}
