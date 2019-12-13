package com.hqgml.tencentapi;

import com.hqgml.tencentapi.utlis.Client;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.iai.v20180301.IaiClient;
import com.tencentcloudapi.iai.v20180301.models.GetGroupListRequest;
import com.tencentcloudapi.iai.v20180301.models.GetGroupListResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TencentapiApplicationTests {

    @Test
    void contextLoads() {

        try{

            Credential cred = new Credential("AKIDR6g5RigRsza9QgwzzstLfxIZuHaSg6JT", "HTakb1QXaXOBvj7ejfWOL5jpgLmzxhCd");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("iai.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            IaiClient client = new IaiClient(cred, "ap-beijing", clientProfile);

            String params = "{}";
            GetGroupListRequest req = GetGroupListRequest.fromJsonString(params, GetGroupListRequest.class);

            GetGroupListResponse resp = client.GetGroupList(req);

            System.out.println(GetGroupListRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }

    @Test
    void text(){
        Client client= new Client();
        IaiClient client1 = client.getClient();
        System.out.println(client1);
    }

}
