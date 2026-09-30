package com.configuredStructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConfiguredRunner implements  CommandLineRunner {
    private PaymentGateway paymentGateway;

    @Autowired
    public ConfiguredRunner(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//       paymentGateway.print();
//    }

    @Override
    public void run(String... args) throws Exception {
        paymentGateway.print();
    }
}
