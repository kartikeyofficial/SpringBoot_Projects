package com.configuredStructure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("payment-property")
public class PaymentProperties {
    private String type;
    private int retryCount;
    private boolean enabled;
    private int timeout;

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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getTimeOut() {
        return timeout;
    }

    public void setTimeOut(int timeout) {
        this.timeout = timeout;
    }
}
