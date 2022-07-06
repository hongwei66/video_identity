package com.longma.videoidenty.service;

import com.longma.videoidenty.common.BaseException;
import com.longma.videoidenty.common.SystemErrorEnum;
import com.longma.videoidenty.config.SysLogAno;
import com.longma.videoidenty.model.UserInfo;
import com.longma.videoidenty.util.CommonUtil;
import com.longma.videoidenty.util.SpringUtil;
import com.squareup.okhttp.Response;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.io.File;

import static com.longma.videoidenty.controller.UserController.USERS;

@Component
@Slf4j
public class AsyncService {

    @Autowired
    private CosService cosService;

    @Autowired
    private FileService fileService;

    @Async("taskExecutor")
    @SysLogAno
    public void callDealVideoScrip(String token) {

        UserInfo userInfo = USERS.get(token);
        String beforeVideoKey = userInfo.getBeforeDealVideoObjKey();
        // 下载视频到本地/home/dell/first/AlphaPose/examples/demo/
        try {
            cosService.downloadFileFromCos(beforeVideoKey, userInfo.getVideoFileName());
        } catch (Exception e) {
            log.error(CommonUtil.getStackTrace(e));
            throw new BaseException(SystemErrorEnum.SYSTEM_ERROR.getCode(), "下载视频失败，err msg: " + e.getMessage());
        }

        // 清空输出文件夹下的文件（/home/dell/first/AlphaPose/examples/demo/output）
        // FileUtils.deleteDirectory();

        // 请求对应的接口 http://localhost:4010/video_upload
        // http://localhost:4010/video_upload data类型为字符串例如data=”/home/dell/first/AlphaPose/examples/demo/xxx.mp4”
        String body = "";
        String postUrl = "";
        if (StringUtils.equals(SpringUtil.getActiveProfiles()[0], "prod")) {
            body = String.format("{\"data\": \"/home/dell/skating_front/download/%s\"}",userInfo.getVideoFileName());
            postUrl = "http://10.112.6.220:4013/video_upload";
        } else {
            body = String.format("{\"data\": \"/home/dell/first/AlphaPose/examples/demo/%s\"}",userInfo.getVideoFileName());
            postUrl = "http://localhost:8888/video/mockVideoUpload";
        }
        try {
            Response res = CommonUtil.httpPost(postUrl, body, "");
            log.info("[video_upload response] code: {}, body: {}", res.code(), res.body().string());
        } catch (Exception e) {
            log.error(CommonUtil.getStackTrace(e));
            throw new BaseException(SystemErrorEnum.HTTP_REQUEST_ERR);
        }
    }


    @SysLogAno
    public void downloadAndCall(String token) {

        UserInfo userInfo = USERS.get(token);
        String beforeVideoKey = userInfo.getBeforeDealVideoObjKey();
        // 下载视频到本地/home/dell/first/AlphaPose/examples/demo/
        try {
            fileService.download(userInfo.getFile(), userInfo.getVideoFileName());
        } catch (Exception e) {
            log.error(CommonUtil.getStackTrace(e));
            throw new BaseException(SystemErrorEnum.SYSTEM_ERROR.getCode(), "下载视频失败，err msg: " + e.getMessage());
        }

        // 清空输出文件夹下的文件（/home/dell/first/AlphaPose/examples/demo/output）
        // FileUtils.deleteDirectory();

        // 请求对应的接口 http://localhost:4010/video_upload
        // http://localhost:4010/video_upload data类型为字符串例如data=”/home/dell/first/AlphaPose/examples/demo/xxx.mp4”
        String body = "";
        String postUrl = "";
        if (StringUtils.equals(SpringUtil.getActiveProfiles()[0], "prod")) {
            body = String.format("{\"data\": \"/home/ubuntu/skating_front/download/%s\"}",userInfo.getVideoFileName());
            postUrl = "http://106.75.81.133:4013/video_upload";
            try {
                Response res = CommonUtil.httpPost(postUrl, body, "");
                log.info("[video_upload response] code: {}, body: {}", res.code(), res.body().string());
            } catch (Exception e) {
                log.error(CommonUtil.getStackTrace(e));
                throw new BaseException(SystemErrorEnum.HTTP_REQUEST_ERR);
            }
        }

    }


}
