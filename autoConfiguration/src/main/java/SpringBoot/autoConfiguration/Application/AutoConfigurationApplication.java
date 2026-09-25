package SpringBoot.autoConfiguration.Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AutoConfigurationApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(AutoConfigurationApplication.class, args);
		OrderService order = context.getBean(OrderService.class);
		order.placeOrder();

	}

}
