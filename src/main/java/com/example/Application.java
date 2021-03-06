package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


/*https://dzone.com/articles/add-login-to-your-spring-boot-app-in-10-mins*/

@SpringBootApplication
@EntityScan(basePackages = {"com.example"})  // scan JPA entities
public class Application  {


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}


}
