package com.configuredStructure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PaymentGateway {
    @Value("${paymentGateway.type:Razorpay}")
    private String type;
    @Value("${paymentGateway.retry-count}")
    private int retryCount;


//    public PaymentGateway(@Value("${paymentGateway.type}") String type,
//                          @Value("${paymentGateway.retry-count}") int retryCount) {
//        this.type = type;
//        this.retryCount = retryCount;
//    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }
}
