package com.longma.videoidenty.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

@Component
@Slf4j
public class FileService {


    @Value("${download.path}")
    private String downloadPath;

    /**
     * 下载文件到本地
     * @param multipartFile
     * @param fileName
     */
    public void download(MultipartFile multipartFile, String fileName) {
        String fileOldName = multipartFile.getOriginalFilename();
        int beginIndex = fileOldName.lastIndexOf(".");
        String suffix = fileOldName.substring(beginIndex);
        File upFile = new File(downloadPath + "/" + fileName);
        OutputStream outputStream = null;
        try {
            byte[] fileByte = multipartFile.getBytes();
            outputStream = new FileOutputStream(upFile);
            outputStream.write(fileByte);
            log.info("<==  文件写出完成: " + fileName);
        } catch (Exception e) {
            log.error("", e);
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (Exception e) {
                log.error("", e);
            }
        }
    }
}
