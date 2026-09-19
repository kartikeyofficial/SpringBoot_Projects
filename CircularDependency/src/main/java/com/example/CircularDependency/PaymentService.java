package com.example.CircularDependency;

import org.springframework.stereotype.Component;

@Component
public class PaymentService {
    public void pay(){
        System.out.println("Payment Done!");
    }
}
