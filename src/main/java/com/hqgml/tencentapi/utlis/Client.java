package com.hqgml.tencentapi.utlis;

import com.hqgml.tencentapi.config.KeyProperties;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.iai.v20180301.IaiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * @data 2019/12/13 12:20
 **/

@Component
@EnableConfigurationProperties(KeyProperties.class)
@Configuration //声明为配置类
public class Client {

    @Autowired
    private  KeyProperties keyProperties;

    @Bean
    public  IaiClient getClient() {
        Credential cred = new Credential(keyProperties.getSecretId(), keyProperties.getSecretKey());
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("iai.tencentcloudapi.com");

        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);

        IaiClient client = new IaiClient(cred, "ap-beijing", clientProfile);

        return client;
    }
}
