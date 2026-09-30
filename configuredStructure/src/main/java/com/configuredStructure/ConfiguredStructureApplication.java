package com.configuredStructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ConfiguredStructureApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfiguredStructureApplication.class, args);
//		PaymentGateway paymentGateway = context.getBean(PaymentGateway.class);
//		paymentGateway.setType("Paytm");
//		paymentGateway.setRetryCount(5);



	}

}
