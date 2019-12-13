package com.hqgml.tencentapi.controller;

import com.hqgml.tencentapi.bean.FaceBean;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.iai.v20180301.IaiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.tencentcloudapi.iai.v20180301.models.*;

/**
 * @data 2019/12/13 20:07
 **/
@Controller
@RequestMapping("Search")
public class FaceSearchController {

    @Autowired
    private IaiClient client;

    @ResponseBody
    @PostMapping
    public ResponseEntity<FaceBean> Search(
            @RequestParam(value = "GroupId") String GroupId,
            @RequestParam(value = "Image", required = false) String Image,
            @RequestParam(value = "Url", required = false) String Url
//    @RequestParam(value = "MaxPersonNum",required = false,defaultValue = "1") String MaxPersonNum
    ) {
        FaceBean faceBean = new FaceBean();
        try {
            String params = "{\"GroupIds\":[\""+GroupId+"\"],\"Image\":\""+Image+"\",\"Url\":\""+Url+"\",\"MaxPersonNumPerGroup\":1,\"NeedPersonInfo\":1}";
            SearchFacesRequest req = SearchFacesRequest.fromJsonString(params, SearchFacesRequest.class);
            SearchFacesResponse resp = client.SearchFaces(req);
            Result[] results = resp.getResults();
            for (Result result : results) {
                Candidate[] candidates = result.getCandidates();
                for (Candidate candidate : candidates) {
                    faceBean.setPersonId(candidate.getPersonId());
                    faceBean.setPersonId(candidate.getFaceId());
                    faceBean.setScore(candidate.getScore());
                    faceBean.setPersonName(candidate.getPersonName());
                }
            }
            return ResponseEntity.ok(faceBean);
        } catch (TencentCloudSDKException e) {
            faceBean.setErrorMsg(e.toString());
            return ResponseEntity.badRequest().body(faceBean);
        }
    }
}
