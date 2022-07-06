package com.longma.videoidenty.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
public class ActionVo {
    private String actionName;

    private Date actionTime;
}
