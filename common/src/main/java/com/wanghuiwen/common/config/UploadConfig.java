package com.wanghuiwen.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Component
@ConfigurationProperties("upload")
public class UploadConfig {

    private String filePath;

    private String host;

    private String prefix;
    /**
     * 上传方式
     */
    private String type;

    List<String> supportExtension;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public List<String> getSupportExtension() {
        return supportExtension;
    }

    public void setSupportExtension(List<String> supportExtension) {
        this.supportExtension = supportExtension;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * 判断是否支持上传
     * @param extension
     * @return
     */
    public boolean isSupportExtension(String extension){
        for (String s : this.supportExtension) {
            if(s.equalsIgnoreCase(extension)){
                return true;
            }
        }
        return false;
    }
}
