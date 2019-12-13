package com.hqgml.tencentapi.controller;

import com.hqgml.tencentapi.bean.PersonList;
import com.tencentcloudapi.iai.v20180301.IaiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.iai.v20180301.models.*;


/**
 * @data 2019/12/13 19:34
 **/
@RequestMapping("Person")
public class FacePersonController {

    @Autowired
    private IaiClient client;

    @PostMapping("Create")
    @ResponseBody
    public ResponseEntity<String> CreatePerson(
            @RequestParam(value = "GroupId") String GroupId,
            @RequestParam(value = "PersonName") String PersonName,
            @RequestParam(value = "PersonId") String PersonId,
            @RequestParam(value = "Image", required = false) String Image,
            @RequestParam(value = "Url", required = false) String url
    ) {
        try {
            String params = "{\"GroupId\":\"" + GroupId + "\",\"PersonName\":\"" + PersonName + "\",\"PersonId\":\"" + PersonId + "\",\"Image\":\"" + Image + "\",\"Url\":\"" + url + "\"}";
            CreatePersonRequest req = CreatePersonRequest.fromJsonString(params, CreatePersonRequest.class);
            CreatePersonResponse resp = client.CreatePerson(req);
            return ResponseEntity.ok(CreatePersonRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            return ResponseEntity.status(400).body(e.toString());
        }
    }

    @ResponseBody
    @GetMapping("Delete")
    public ResponseEntity<String> DeletePerson(@RequestParam(value = "PersonId ") String PersonId) {
        try {
            String params = "{\"PersonId\":\"" + PersonId + "\"}";
            DeletePersonRequest req = DeletePersonRequest.fromJsonString(params, DeletePersonRequest.class);
            DeletePersonResponse resp = client.DeletePerson(req);
            return ResponseEntity.ok(DeletePersonRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            return ResponseEntity.status(500).body(e.toString());
        }
    }

    @ResponseBody
    @GetMapping("GetList")
    public ResponseEntity<PersonList> GetListPerson(@RequestParam(value = "GroupId") String GroupId) {
        PersonList personList = new PersonList();
        try {
            String params = "{\"GroupId\":\"" + GroupId + "\"}";
            GetPersonListRequest req = GetPersonListRequest.fromJsonString(params, GetPersonListRequest.class);

            GetPersonListResponse resp = client.GetPersonList(req);

            PersonInfo[] personInfos = resp.getPersonInfos();
            for (PersonInfo personInfo : personInfos) {
                personList.setPersonId(personInfo.getPersonId());
                personList.setFaceIds(personInfo.getFaceIds());
                personList.setPersonName(personInfo.getPersonName());
            }
            return ResponseEntity.ok(personList);
        } catch (TencentCloudSDKException e) {
            return ResponseEntity.badRequest().body(personList);
        }
    }
}
