package com.hqgml.tencentapi.bean;

import lombok.Data;

/**
 * @data 2019/12/13 20:29
 **/
@Data
public class FaceBean {

    private String PersonId;
    private String FaceId;
    private double Score;
    private String PersonName;
    private String ErrorMsg;

}
