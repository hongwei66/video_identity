package com.longma.videoidenty.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    private Integer id;

    private String userName;

    private String password;

    private List<String> roles;

    private String introduction;

    private String avatar;

    private String videoFileName;

    private String beforeDealVideoObjKey;

    private String dealedVideoObjKey;

    private String videoRoleName1;

    private String videoRoleName2;

    private String videoRoleSex1;

    private String videoRoleSex2;

    private String img0Key;

    private String img1Key;

    private boolean hasImgUploaded;

    private boolean hasDealedVideoUpload;

    private String jsonStr;

    private MultipartFile file;
}
