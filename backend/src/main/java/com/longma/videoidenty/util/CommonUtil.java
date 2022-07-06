package com.longma.videoidenty.util;

import com.longma.videoidenty.common.BaseException;
import com.longma.videoidenty.common.SystemErrorEnum;
import com.squareup.okhttp.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import java.io.*;

@Slf4j
public class CommonUtil {

    public static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");
    /**
     * 完整的堆栈信息
     *
     * @param e Exception
     * @return Full StackTrace
     */
    public static String getStackTrace(Exception e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return sw.toString();
    }



    public static Response httpPost(String url, String json,String authorization) throws IOException {
        log.info("[httpPost req] url: {}, jsonBody: {}, header: {}", url, json, authorization);
        OkHttpClient httpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).addHeader("Authorization","Basic "+authorization).post(requestBody).build();
        Response response = httpClient.newCall(request).execute();
        return response;
    }

    public static String readJsonStrFromFile(File jsonFile) {
        String jsonStr = null;
        try {
            //File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (Exception e) {
            log.error("read json file failed: " + jsonFile.getPath());
            log.error(getStackTrace(e));
            throw new BaseException(SystemErrorEnum.SYSTEM_ERROR, "读取Json文件异常");
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println(httpPost("http://127.0.0.1:8888/users/login", "{\"username\": \"123\"}", ""));
        } catch (Exception e) {
            System.out.println(getStackTrace(e));
        }



    }
}
