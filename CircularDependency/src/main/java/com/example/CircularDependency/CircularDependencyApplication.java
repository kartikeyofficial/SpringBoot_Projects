package com.example.CircularDependency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.security.auth.login.AppConfigurationEntry;

@SpringBootApplication
public class CircularDependencyApplication {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		OrderService order = context.getBean(OrderService.class);
		order.placeOrder();


		AppConfig config = context.getBean(AppConfig.class);
		config.demo();
	}

}
