package com.hqgml.tencentapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * @Data 2019/12/13 12:13
 **/
@Component
@ConfigurationProperties(prefix = "tenlent.keys")
public class KeyProperties {
    private String SecretId;
    private String SecretKey;

    public String getSecretId() {
        return SecretId;
    }

    public void setSecretId(String secretId) {
        SecretId = secretId;
    }

    public String getSecretKey() {
        return SecretKey;
    }

    public void setSecretKey(String secretKey) {
        SecretKey = secretKey;
    }


}
