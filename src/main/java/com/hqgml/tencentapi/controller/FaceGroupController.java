package com.hqgml.tencentapi.controller;

import com.hqgml.tencentapi.bean.Error;
import com.hqgml.tencentapi.bean.GroupListBean;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.iai.v20180301.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.tencentcloudapi.iai.v20180301.IaiClient;

import java.util.ArrayList;
import java.util.List;

/**
 * @data 2019/12/13 12:26
 **/
@Controller
@RequestMapping("Group")
public class FaceGroupController {

    @Autowired
    private IaiClient client;


    /**
     * 创建组
     *
     * @param GroupName
     * @param GroupId
     * @return
     */
//    @PostMapping("Create")
    @PostMapping
    public ResponseEntity<String> CreateGroup(@RequestParam(value = "GroupName") String GroupName, @RequestParam(value = "GroupId") String GroupId) {
        try {
            String params = "{\"GroupName\":\"" + GroupName + "\",\"GroupId\":\"" + GroupId + "\"}";
            CreateGroupRequest req = CreateGroupRequest.fromJsonString(params, CreateGroupRequest.class);
            CreateGroupResponse resp = client.CreateGroup(req);
            String requestId = resp.getRequestId();
            return ResponseEntity.ok(requestId);
        } catch (TencentCloudSDKException e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
    }

    /**
     * 获取组集合的页面展示
     *
     * @param model
     * @return
     */
    @GetMapping("GetListPage")
    public String GetGroupList(ModelMap model) {
        try {
            String params = "{\"Limit\":1000}";
            GetGroupListRequest req = GetGroupListRequest.fromJsonString(params, GetGroupListRequest.class);
            GetGroupListResponse resp = client.GetGroupList(req);
            GroupInfo[] groupInfos = resp.getGroupInfos();
            List<GroupListBean> groupList = new ArrayList<>();
            for (GroupInfo groupInfo : groupInfos) {
                GroupListBean groupListBean = new GroupListBean();
                groupListBean.setGroupId(groupInfo.getGroupId());
                groupListBean.setGroupName(groupInfo.getGroupName());
                groupList.add(groupListBean);
            }
            model.addAttribute("groupLists", groupList);

            return "grouplist";
        } catch (TencentCloudSDKException e) {
            model.addAttribute("error", e.toString());
            return "error";
        }
    }

    /**
     * 获取组json
     *
     * @return
     */
    @ResponseBody
//    @GetMapping("GetList")
    @GetMapping
    public ResponseEntity<List> GetGroupListPage() {
        List<GroupListBean> groupList = new ArrayList<>();
        try {
            String params = "{\"Limit\":1000}";
            GetGroupListRequest req = GetGroupListRequest.fromJsonString(params, GetGroupListRequest.class);
            GetGroupListResponse resp = client.GetGroupList(req);
            GroupInfo[] groupInfos = resp.getGroupInfos();

            for (GroupInfo groupInfo : groupInfos) {
                GroupListBean groupListBean = new GroupListBean();
                groupListBean.setGroupId(groupInfo.getGroupId());
                groupListBean.setGroupName(groupInfo.getGroupName());
                groupList.add(groupListBean);
            }
            return ResponseEntity.ok(groupList);
        } catch (TencentCloudSDKException e) {
            List<Error> errors = new ArrayList<>();
            Error error = new Error();
            errors.add(error);
            return ResponseEntity.badRequest().body(errors);
        }
    }

    /**
     * 删除返回json
     *
     * @param GroupId
     * @return
     */
    @ResponseBody
//    @GetMapping("Delete")
    @DeleteMapping
    public String DeleteGroup(@RequestParam(value = "GroupId") String GroupId) {
        try {
            String params = "{\"GroupId\":\"" + GroupId + "\"}";
            DeleteGroupRequest req = DeleteGroupRequest.fromJsonString(params, DeleteGroupRequest.class);
            DeleteGroupResponse resp = client.DeleteGroup(req);
            return resp.getRequestId();
        } catch (TencentCloudSDKException e) {
            return e.toString();
        }
    }

    /**
     * 删除返回页面
     *
     * @param model
     * @param GroupId
     * @return
     */
//    @GetMapping("DeletePage")
    @DeleteMapping("DeletePage")
    public String DeleteGroupPage(ModelMap model, @RequestParam(value = "GroupId") String GroupId) {
        try {
            String params = "{\"GroupId\":\"" + GroupId + "\"}";
            DeleteGroupRequest req = DeleteGroupRequest.fromJsonString(params, DeleteGroupRequest.class);
            DeleteGroupResponse resp = client.DeleteGroup(req);
            return "redirect:GetListPage";
        } catch (TencentCloudSDKException e) {
            System.out.println(e);
            model.addAttribute("error", e.toString());
            return "errorw";
        }
    }

}
