package com.wanghuiwen.user.service.impl;

import com.wanghuiwen.common.VideoScreenshotUtil;
import com.wanghuiwen.common.config.UploadConfig;
import com.wanghuiwen.core.ServiceException;
import com.wanghuiwen.user.dao.MediaResourceMapper;
import com.wanghuiwen.user.model.MediaResource;
import com.wanghuiwen.user.service.MediaResourceService;
import com.wanghuiwen.core.service.AbstractService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wanghuiwen.user.model.Resource.RESOURCE_VIDEO;


/**
 * Created by wanghuiwen on 2021/08/19.
 */
@Service
@Transactional
public class MediaResourceServiceImpl extends AbstractService<MediaResource> implements MediaResourceService {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private MediaResourceMapper mediaResourceMapper;
    @Resource
    private UploadConfig uploadConfig;

    @Override
    public List<MediaResource> userList(Map<String, Object> params) {
        return mediaResourceMapper.userList(params);
    }

    public MediaResource saveFile(MultipartFile file){
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if(!uploadConfig.isSupportExtension(extension)){
            throw new ServiceException("不支持的文件格式","upload.noSupportExtension");
        }
        // 重命名上传后的文件名
        String fileName = FilenameUtils.getBaseName(file.getOriginalFilename())+ "_" + new Date().getTime()+"."+extension;

        String filePath = uploadConfig.getFilePath() + fileName ;

        File dest = new File(filePath);
        try {
            // todo 上传文件 这里应该支持多种方式 通过  uploadConfig.getType 判断
            Files.copy(file.getInputStream(), dest.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        MediaResource mediaResource = new MediaResource();
        mediaResource.setResourceUrl(FilenameUtils.getFullPath(uploadConfig.getPrefix())+fileName);
        mediaResource.setResourceType(mediaResource.getResourceTypeByExtension(extension));
        if(mediaResource.getResourceType() == RESOURCE_VIDEO){
            File tergetImage = new File(uploadConfig.getFilePath() + FilenameUtils.getBaseName(fileName) + ".jpg" );
            VideoScreenshotUtil.getScreenshot(dest,tergetImage);
            mediaResource.setVideoCover(uploadConfig.getPrefix() + FilenameUtils.getBaseName(fileName) + ".jpg");
        }
        return mediaResource;
    }

}
