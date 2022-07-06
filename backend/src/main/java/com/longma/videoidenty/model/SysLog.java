package com.longma.videoidenty.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class SysLog {

    private String className;

    private String methodName;

    private String params;

    private Date startTime;

    private String remark;

}
