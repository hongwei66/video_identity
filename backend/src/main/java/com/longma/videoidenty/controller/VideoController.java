package com.longma.videoidenty.controller;

import com.alibaba.fastjson.JSONObject;
import com.longma.videoidenty.common.BaseException;
import com.longma.videoidenty.common.IResult;
import com.longma.videoidenty.common.Result;
import com.longma.videoidenty.common.SystemErrorEnum;
import com.longma.videoidenty.config.SysLogAno;
import com.longma.videoidenty.model.ActionVo;
import com.longma.videoidenty.model.MockImgReadyVO;
import com.longma.videoidenty.model.UserInfo;
import com.longma.videoidenty.service.AsyncService;
import com.longma.videoidenty.service.CosService;
import com.longma.videoidenty.util.CommonUtil;
import com.longma.videoidenty.util.SpringUtil;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.utils.Jackson;
import com.squareup.okhttp.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

import static com.longma.videoidenty.controller.UserController.USERS;
import static com.longma.videoidenty.controller.UserController.curUserToken;

@RestController
@RequestMapping("/video")
@Slf4j
public class VideoController {

    @Autowired
    private CosService cosService;

    @Autowired
    private AsyncService asyncService;



    /**
     * 下载视频，并处理出视频中的角色图片
     * @param map
     * @param token
     * @return
     */
    @PostMapping("/dealVideoImg")
    @SysLogAno
    public IResult dealVideoImg(@RequestBody Map<String, String> map, @RequestParam String token) {
        // 根据token
        if (USERS.containsKey(token)) {
            USERS.get(token).setHasDealedVideoUpload(false);
            USERS.get(token).setHasImgUploaded(false);
            String videoKey = map.get("videoKey");
            UserInfo userInfo = USERS.get(token);
            if (StringUtils.isEmpty(videoKey)) {
                log.error("初始视频videoKey为空");
                throw new BaseException(SystemErrorEnum.SYS_PARAM_ERROR.getCode(), "初始视频videoKey为空");
            }

//            if (StringUtils.isEmpty(userInfo.getVideoRoleName1()) || StringUtils.isEmpty(userInfo.getVideoRoleSex1())
//                || StringUtils.isEmpty(userInfo.getVideoRoleName2()) || StringUtils.isEmpty(userInfo.getVideoRoleSex2())) {
//                log.error("视频角色信息为空，userInfo：{}", userInfo);
//                throw new BaseException(SystemErrorEnum.SYS_PARAM_ERROR.getCode(), "视频角色信息为空");
//            }
            // 更新USERS
            String[] videoKeySplits = videoKey.split("/");
            USERS.get(token).setVideoFileName(videoKeySplits[1]);
            USERS.get(token).setBeforeDealVideoObjKey(videoKey);

            // 下载视频
            try {
                asyncService.callDealVideoScrip(token);
            } catch (Exception e) {
                log.error(CommonUtil.getStackTrace(e));
                throw new BaseException(SystemErrorEnum.SYSTEM_ERROR.getCode(), "处理视频失败， err msg: " + e.getMessage());
            }
        } else {
            throw new BaseException(SystemErrorEnum.SYS_NOLOGIN_ERROR.getCode(), SystemErrorEnum.SYS_NOLOGIN_ERROR.getMsg());
        }
        return Result.success();
    }

    @RequestMapping("/setVideoInfo")
    @SysLogAno
    public IResult setVideoInfo(@RequestBody Map<String, String> map, @RequestParam String token) {
        // 根据token
        UserInfo userInfo = USERS.get(token);
        userInfo.setVideoRoleName1(map.get("name1"));
        userInfo.setVideoRoleName2(map.get("name2"));
        userInfo.setVideoRoleSex1(map.get("sex1"));
        userInfo.setVideoRoleSex2(map.get("sex2"));


         return Result.success();
    }

    @PostMapping("/dealVideo")
    @SysLogAno
    public IResult dealVideo(@RequestParam String token) {

        UserInfo userInfo = USERS.get(token);
        if(!userInfo.isHasImgUploaded()) {
            throw new BaseException(SystemErrorEnum.SYS_PARAM_ERROR.getCode(), "图片信息未设置");
        }
        // push image info http://localhost:4010/image_ready
        String body = String.format("{" +
                "\"user0\": {" +
                "\"name\": \"%s\", \"sex\": %d}," +
                "\"user1\": {" +
                "\"name\": \"%s\", \"sex\": %d}" +
                "}", userInfo.getVideoRoleName1(), Integer.valueOf(userInfo.getVideoRoleSex1()), userInfo.getVideoRoleName2(), Integer.valueOf(userInfo.getVideoRoleSex2()));
        String postUrl = "";
        JSONObject jsonObject = new JSONObject();
        Map<String, String> user0 = new HashMap<>();
        Map<String, String> user1 = new HashMap<>();
        user0.put("name", userInfo.getVideoRoleName1());
        user0.put("sex", userInfo.getVideoRoleSex1().equals("1")?"男":"女");
        user1.put("name", userInfo.getVideoRoleName2());
        user1.put("sex", userInfo.getVideoRoleSex2().equals("1")?"男":"女");

        jsonObject.put("user0", user0);
        jsonObject.put("user1", user1);
        body = jsonObject.toJSONString();

        if (StringUtils.equals(SpringUtil.getActiveProfiles()[0], "prod")) {
            postUrl = "http://119.3.215.168:4010/image_ready";
        } else {
            postUrl = "http://localhost:8888/video/mockImageReady";
        }
        try {
            Response res = CommonUtil.httpPost(postUrl, body, "");
            log.info("[image_ready response] code: {}, body: {}", res.code(), res.body().string());
        } catch (Exception e) {
            log.error(CommonUtil.getStackTrace(e));
            throw new BaseException(SystemErrorEnum.HTTP_REQUEST_ERR);
        }
        return Result.success();
    }

    @PostMapping("/getDealedVideoInfo")
    @SysLogAno
    public IResult getDealedVideoInfo(@RequestParam String token) {
        // 根据token
        if (USERS.containsKey(token)) {
            UserInfo userInfo = USERS.get(token);
            if (userInfo.isHasDealedVideoUpload()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("videoKey", userInfo.getDealedVideoObjKey());
                jsonObject.put("jsonStr", userInfo.getJsonStr());
                return Result.dataSuccess(jsonObject);
            }
        } else {
            throw new BaseException(SystemErrorEnum.SYS_NOLOGIN_ERROR.getCode(), SystemErrorEnum.SYS_NOLOGIN_ERROR.getMsg());
        }
        log.info("USERS: {}", USERS);
        return Result.fail();
    }

    @PostMapping("/getImages")
    @SysLogAno
    public IResult getImages(@RequestParam String token) {

        // 通过UserrController中的curUserToken
        UserInfo userInfo = USERS.get(token);
        // 判断push Image中设置的标志
        if (!userInfo.isHasImgUploaded()) {
            throw new BaseException("-1", "图片数据未就绪");
        }
        JSONObject res = new JSONObject();
        res.put("img0", userInfo.getImg0Key());
        res.put("img1", userInfo.getImg1Key());
        return Result.dataSuccess(res);
    }

    @PostMapping("/pushImages")
    @SysLogAno
    public IResult pushImages(@RequestBody Map<Integer, String> map) {

        // 通过UserrController中的curUserToken
        UserInfo userInfo = USERS.get(curUserToken);
        String img0Path = map.get(0);
        String img1Path = map.get(1);
        // 获取图片后上传到cos
        File img0File = new File(img0Path);
        File img1File = new File(img1Path);
        String img0Key = String.format("video-identity/%s-img0.jpg", userInfo.getVideoFileName().split("\\.")[0]);
        String img1Key = String.format("video-identity/%s-img1.jpg", userInfo.getVideoFileName().split("\\.")[0]);
        PutObjectResult putImg0Result = cosService.uploadLocalFile2Cos(img0Key, img0File);
        PutObjectResult putImg1Result = cosService.uploadLocalFile2Cos(img1Key, img1File);
        log.info("put img0 res: {}, img1 res {}", putImg0Result == null?null:putImg0Result.toString(), putImg1Result == null?null:putImg1Result.toString());


        // 记录图片已上传标志位
        userInfo.setHasImgUploaded(true);
        userInfo.setImg0Key(img0Key);
        userInfo.setImg1Key(img1Key);
        log.info("cur userInfo {}", userInfo);
        return Result.success();
    }

    @PostMapping("/pushVideoJsonInfo")
    @SysLogAno
    public IResult pushVideoJsonInfo(@RequestBody Map<String, String> map) {

        // 通过UserrController中的curUserToken
        UserInfo userInfo = USERS.get(curUserToken);

        // 将视频上传到cos，设置afterdealed videokey
        String videoPath = map.get("video");
        String jsonPath = map.get("json");
        String[] videoNameSplits = userInfo.getVideoFileName().split("\\.");
        String videoKey = "video-identity/" + videoNameSplits[0] + "-dealed." + videoNameSplits[1];
        File videoFile = new File(videoPath);
        File jsonFile = new File(jsonPath);
        PutObjectResult putObjectResult = cosService.uploadLocalFile2Cos(videoKey, videoFile);
        log.info("upload dealed video res: {}", putObjectResult == null?null:putObjectResult.toString());
        String jsonStr = CommonUtil.readJsonStrFromFile(jsonFile);

        // 设置视频处理成功标志位
        userInfo.setDealedVideoObjKey(videoKey);
        userInfo.setJsonStr(jsonStr);
        userInfo.setHasDealedVideoUpload(true);
        log.info("cur user info: {}", userInfo);
        return Result.success();
    }



    /**
     * http://localhost:4010/video_upload 视频上传成功
     * http://localhost:4010/image_ready  图片信息设置成功
     * @return
     */
    @SysLogAno
    @PostMapping("/mockVideoUpload")
    public IResult mockVideoUpload(@RequestBody Map<String, String> map) {
        return null;
    }
    @SysLogAno
    @PostMapping("/mockImageReady")
    public IResult mockImageReady() {
        return null;
    }



    @SysLogAno
    @PostMapping("/mockWebsocketInfo")
    public IResult mockWebsocketInfo(@RequestBody Map<String, String> params) {
        if (!params.containsKey("user")) {
            throw new BaseException(SystemErrorEnum.SYS_PARAM_ERROR);
        }
        String user = params.get("user");
        WebsocketServer ws = WebsocketServer.webSocketMap.get(params.get("user"));
        JSONObject data = new JSONObject();
        data.put("user", user);

        // mock list
        List<ActionVo> arr = Lists.newArrayList();
        arr.add(new ActionVo("salanghai", new Date(System.currentTimeMillis())));
        arr.add(new ActionVo("salanghai1", new Date(System.currentTimeMillis())));
        arr.add(new ActionVo("salanghai2", new Date(System.currentTimeMillis())));
        data.put("actionList", arr);
        // mock map
        HashMap<String, List<ActionVo>> map = new HashMap<>();
        map.put("action1", arr);
        data.put("actionMap", map);
        // mock obj
        data.put("obj", new ActionVo("778", new Date(System.currentTimeMillis())));
        ws.sendMessage(data.toJSONString());
        return Result.success();
    }

    @SysLogAno
    @PostMapping("/mockVideoStream")
    @ResponseBody
    public void mockVideoStream(HttpServletResponse response) {

        try {
            int i = 0;
            while(i < 100) {

            }
            response.setContentType("multipart/x-mixed-replace;boundary=end");
            response.flushBuffer();
        } catch (java.nio.file.NoSuchFileException e) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }


    // 花滑2.0
    /**
     * 视频处理好后调用，通知前端开始获取视频流以及时评相关识别数据
     * @return
     */
    @GetMapping("/pushVideoReady")
    @SysLogAno
    public IResult videoReady() {
        WebsocketServer websocketServer = WebsocketServer.webSocketMap.get("1");
        if (websocketServer == null) {
            throw new BaseException(SystemErrorEnum.SYSTEM_ERROR, "未查询到对应的视频状态监控socket");
        }
        websocketServer.sendMessage("ready");
        return Result.success();
    }

    /**
     * 接收视频识别报告数据
     * @return
     */
    @PostMapping("/pushReportData")
    @SysLogAno
    public IResult pushReportData(@RequestBody Map<String, Object> param) {
        log.info("pushReportData: {}", param);
        WebsocketServer websocketServer = WebsocketServer.webSocketMap.get("1");
        if (websocketServer == null) {
            throw new BaseException(SystemErrorEnum.SYSTEM_ERROR, "未查询到对应的视频状态监控socket");
        }
        JSONObject jsonObject = new JSONObject(param);
        websocketServer.sendMessage(jsonObject.toJSONString());
        return Result.success();
    }

    /**
     * 视频上传后，下载到本地，并将视频本地地址发送到视频处理端
     * @return
     */
    @RequestMapping("/uploadVideo")
    @SysLogAno
    public IResult uploadVideo(@RequestBody Map<String, Object> map, @RequestParam String token) {
        // 根据token
        if (USERS.containsKey(token)) {
            USERS.get(token).setHasDealedVideoUpload(false);
            USERS.get(token).setHasImgUploaded(false);
            String videoKey = (String)map.get("videoKey");
            UserInfo userInfo = USERS.get(token);
            if (StringUtils.isEmpty(videoKey)) {
                log.error("初始视频videoKey为空");
                throw new BaseException(SystemErrorEnum.SYS_PARAM_ERROR.getCode(), "初始视频videoKey为空");
            }

            // 更新USERS
            String[] videoKeySplits = videoKey.split("/");
            userInfo.setVideoFileName(videoKeySplits[1]);
            userInfo.setBeforeDealVideoObjKey(videoKey);

            // 下载视频
            try {
                asyncService.callDealVideoScrip(token);
            } catch (Exception e) {
                log.error(CommonUtil.getStackTrace(e));
                throw new BaseException(SystemErrorEnum.SYSTEM_ERROR.getCode(), "处理视频失败， err msg: " + e.getMessage());
            }
        } else {
            throw new BaseException(SystemErrorEnum.SYS_NOLOGIN_ERROR.getCode(), SystemErrorEnum.SYS_NOLOGIN_ERROR.getMsg());
        }
        return Result.success();
    }

    /**
     * 视频上传后，下载到本地，并将视频本地地址发送到视频处理端，不通过cos下载
     * @return
     */
    @RequestMapping("/uploadVideoV2")
    @SysLogAno
    public IResult uploadVideoV2(@RequestParam("file") MultipartFile multipartFile, @RequestParam() String token, @RequestParam String videoName) {
        // 根据token
        if (USERS.containsKey(token)) {
            USERS.get(token).setHasDealedVideoUpload(false);
            USERS.get(token).setHasImgUploaded(false);
            UserInfo userInfo = USERS.get(token);
            if (multipartFile== null) {
                log.error("文件内容为空");
                throw new BaseException(SystemErrorEnum.SYS_PARAM_ERROR, "视频内容为空");
            }
            // 更新USERS
            userInfo.setVideoFileName(videoName);
            userInfo.setFile(multipartFile);

            // 下载视频
            try {
                asyncService.downloadAndCall(token);
            } catch (Exception e) {
                log.error(CommonUtil.getStackTrace(e));
                throw new BaseException(SystemErrorEnum.SYSTEM_ERROR.getCode(), "处理视频失败， err msg: " + e.getMessage());
            }
        } else {
            throw new BaseException(SystemErrorEnum.SYS_NOLOGIN_ERROR.getCode(), SystemErrorEnum.SYS_NOLOGIN_ERROR.getMsg());
        }
        return Result.success();
    }



}
