package com.example.xmlConfiguration;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class XmlConfigurationApplication {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		// Get bean By id/name
//		OrderService order1 =(OrderService) context.getBean("orderService");
//		order1.placeOrder();

		// get bean By Type
//		OrderService order = context.getBean(OrderService.class);
//		order.placeOrder();

		// get bean by Both id/name and type
		OrderService order2 = context.getBean("orderService", OrderService.class);
		order2.placeOrder();


		PaymentService payment =(PaymentService) context.getBean("paymentService");
		payment.pay();


	}

}
