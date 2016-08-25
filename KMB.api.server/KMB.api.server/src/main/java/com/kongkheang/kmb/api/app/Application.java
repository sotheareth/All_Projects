package com.kongkheang.kmb.api.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Main class
 * 
 * @author Mr.SAY SEAK LENG
 *
 */
@SpringBootApplication(scanBasePackages = "com.kongkheang.kmb")
public class Application {
	
	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);
	}
}
