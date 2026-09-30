package com.configuredStructure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PaymentGateway {
//    @Value("${paymentGateway.type:Razorpay}")
//    private String type;
//    @Value("${paymentGateway.retry-count}")
//    private int retryCount;


//    public PaymentGateway(@Value("${paymentGateway.type}") String type,
//                          @Value("${paymentGateway.retry-count}") int retryCount) {
//        this.type = type;
//        this.retryCount = retryCount;
//    }
    private PaymentProperties paymentProperties;

    public PaymentGateway(PaymentProperties paymentProperties){
        this.paymentProperties = paymentProperties;
    }

    public String getType() {
        return paymentProperties.getType();
    }


    public int getRetryCount() {
        return paymentProperties.getRetryCount();
    }



    public boolean isEnabled() {
        return paymentProperties.isEnabled();
    }


    public int getTimeOut() {
        return paymentProperties.getTimeOut();
    }

}
