package com.example.xmlConfiguration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class PaymentService {
    private String Type;
    private int retryCount;

    public PaymentService(String Type,int retryCount){
        this.Type = Type;
        this.retryCount = retryCount;
    }
    public void pay(){
        System.out.println("Payment Done, Type of Payment is: "
                +Type+
                "\nAnd Also retry count is: "
                +retryCount);
    }
}
