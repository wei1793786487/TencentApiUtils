package com.hqgml.tencentapi.bean;

import lombok.Data;

/**
 * @data 2019/12/13 20:57
 **/
@Data
public class PersonList {
    private String PersonName;
    private String PersonId;
    private String[] FaceIds;
    private String Error;
}
