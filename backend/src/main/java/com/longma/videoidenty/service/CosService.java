package com.longma.videoidenty.service;


import com.longma.videoidenty.common.BaseException;
import com.longma.videoidenty.common.SystemErrorEnum;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpMethodName;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.GeneratePresignedUrlRequest;
import com.qcloud.cos.model.GetObjectRequest;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.utils.Jackson;
import com.tencent.cloud.CosStsClient;
import com.tencent.cloud.Response;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

import java.net.URLConnection;
import java.util.Date;
import java.util.TreeMap;

@Component
@Slf4j
@Data
public class CosService {

    private final Logger logger = LoggerFactory.getLogger(CosService.class);

    @Value("${cos.secretKey}")
    private String secretKey;

    @Value("${cos.secretId}")
    private String secretId;

    @Value("${cos.regionName}")
    private String regionName;

    // 公有读私有写
    @Value("${cos.bucket}")
    public String bucket;

    private COSClient cosClient;

    private COSCredentials cred;

    private Region region;

    private ClientConfig clientConfig;

    @Value("${download.path}")
    private String downloadPath;

    @PostConstruct
    public void initCosService() {
        cred = new BasicCOSCredentials(secretId, secretKey);
        Region region = new Region(regionName);
        clientConfig = new ClientConfig(region);
        clientConfig.setHttpProtocol(HttpProtocol.https);
        cosClient = new COSClient(cred, clientConfig);
    }

    /**
     * 获取预签名url
     * @param objectKey
     * @return
     */
    public URL generatePresignedUrl(String objectKey) {
        GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucket, objectKey, HttpMethodName.GET);
        // 设置签名过期时间(可选), 若未进行设置, 则默认使用 ClientConfig 中的签名过期时间(1小时)
        // 可以设置任意一个未来的时间，推荐是设置 10 分钟到 3 天的过期时间
        // 这里设置签名在半个小时后过期
        Date expirationDate = new Date(System.currentTimeMillis() + 30L * 60L * 1000L);
        req.setExpiration(expirationDate);

        URL url = cosClient.generatePresignedUrl(req);
        logger.info("presigned url for {}: {}", objectKey, url.toString());
        return url;
    }

    /**
     * 下载对象到本地
     * @param objKey
     */
    public void downloadFileFromCos(String objKey, String fileName) {
        File downFile = new File(downloadPath + fileName);
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucket, objKey);
        ObjectMetadata downObjectMeta = cosClient.getObject(getObjectRequest, downFile);
    }

    public PutObjectResult uploadLocalFile2Cos(String objKey, File file) {
        return cosClient.putObject(bucket, objKey, file);
    }

    /**
     * 获取公有读的对象的访问url
     * @param bucket 需指定公有读bucket
     * @param objKey
     * @return
     */
    public URL getPublicObjectUrl(String bucket, String objKey) {
        URL url = cosClient.getObjectUrl(bucket, objKey);
        return url;
    }

    /**
     * 上传文件到指定bucketName
     * @param objUrl 对象url地址
     * @param objKey 对象键即对象存储路径 eg：/example/example.jpg 如目录不存在将会自动创建
     * @param bucket 桶名称
     */
    public void putObjectFromUrl(String objUrl, String objKey, String bucket) {
        try {
            URL url = new URL(objUrl);
            URLConnection urlConnection = url.openConnection();
            InputStream stream = urlConnection.getInputStream();
            String key = objKey;
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(urlConnection.getContentType());
            objectMetadata.setContentLength(urlConnection.getContentLength());
            PutObjectResult putObjectResult = cosClient.putObject(bucket, key, stream, objectMetadata);
            log.info(objUrl + "上传结果" +putObjectResult.toString());
        } catch (Exception e) {
            logger.error("put object error: {}", e);
            throw new BaseException(SystemErrorEnum.SYSTEM_ERROR.getCode(), String.format("上传对象失败, params: %s, %s, %s", objUrl, objKey, bucket));
        }
    }



    /**
     * 获取临时凭证
     * @return
     */
    public Response getTmpCredential(String bucketName) {
        TreeMap<String, Object> config = new TreeMap<String, Object>();
        logger.info("secretId: {}", secretId);
        try {
            // 替换为您的 SecretId
            config.put("SecretId", secretId);
            // 替换为您的 SecretKey
            config.put("SecretKey", secretKey);

            // 临时密钥有效时长，单位是秒，默认1800秒，目前主账号最长2小时（即7200秒），子账号最长36小时（即129600秒）
            config.put("durationSeconds", 1800);

            // 换成您的 bucket
            config.put("bucket", bucketName);
            // 换成 bucket 所在地区
            config.put("region", regionName);

            // 这里改成允许的路径前缀，可以根据自己网站的用户登录态判断允许上传的具体路径，例子：a.jpg 或者 a/* 或者 * 。
            // 如果填写了“*”，将允许用户访问所有资源；除非业务需要，否则请按照最小权限原则授予用户相应的访问权限范围。
            config.put("allowPrefixes", new String[] {
                    "*"
            });

            // 密钥的权限列表。必须在这里指定本次临时密钥所需要的权限。
            // 简单上传、表单上传和分片上传需要以下的权限，其他权限列表请看 https://cloud.tencent.com/document/product/436/31923
            String[] allowActions = new String[] {
                    // 简单上传
                    "name/cos:PutObject",
                    // 表单上传、小程序上传
                    "name/cos:PostObject",
                    // 分块上传
                    "name/cos:InitiateMultipartUpload",
                    "name/cos:ListMultipartUploads",
                    "name/cos:ListParts",
                    "name/cos:UploadPart",
                    "name/cos:CompleteMultipartUpload"
            };
            config.put("allowActions", allowActions);

            Response response = CosStsClient.getCredential(config);
            //成功返回临时密钥信息，如下打印密钥信息
            logger.info(Jackson.toJsonPrettyString(response));
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(SystemErrorEnum.SYSTEM_ERROR, "未获取到cos临时凭证");
        }
    }

    public static void main(String[] args) {
        TreeMap<String, Object> config = new TreeMap<String, Object>();
        CosService cosService = new CosService();

        try {
            // 替换为您的 SecretId
            config.put("SecretId", "AKIDrXx70cy3qMddMoysVYOzKPDl22lDOIwJ");
            // 替换为您的 SecretKey
            config.put("SecretKey", "7yYruue7JGwLTMyJNHuFK8gxxg9LGtGO");

            // 临时密钥有效时长，单位是秒，默认1800秒，目前主账号最长2小时（即7200秒），子账号最长36小时（即129600秒）
            config.put("durationSeconds", 7200);

            // 换成您的 bucket
            config.put("bucket", "keep2ai-1308112438");
            // 换成 bucket 所在地区
            config.put("region", "ap-beijing");

            // 这里改成允许的路径前缀，可以根据自己网站的用户登录态判断允许上传的具体路径，例子：a.jpg 或者 a/* 或者 * 。
            // 如果填写了“*”，将允许用户访问所有资源；除非业务需要，否则请按照最小权限原则授予用户相应的访问权限范围。
            config.put("allowPrefixes", new String[] {
                    "*"
            });

            // 密钥的权限列表。必须在这里指定本次临时密钥所需要的权限。
            // 简单上传、表单上传和分片上传需要以下的权限，其他权限列表请看 https://cloud.tencent.com/document/product/436/31923
            String[] allowActions = new String[] {
                    // 简单上传
                    "name/cos:PutObject",
                    // 表单上传、小程序上传
                    "name/cos:PostObject",
                    // 分块上传
                    "name/cos:InitiateMultipartUpload",
                    "name/cos:ListMultipartUploads",
                    "name/cos:ListParts",
                    "name/cos:UploadPart",
                    "name/cos:CompleteMultipartUpload"
            };
            config.put("allowActions", allowActions);

            Response response = CosStsClient.getCredential(config);
            //成功返回临时密钥信息，如下打印密钥信息
            System.out.println(Jackson.toJsonPrettyString(response));
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("no valid secret !");
        }
    }






}
