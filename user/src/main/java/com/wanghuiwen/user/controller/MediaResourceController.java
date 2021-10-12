package com.wanghuiwen.user.controller;

import com.wanghuiwen.common.config.UploadConfig;
import com.wanghuiwen.core.controller.Ctrl;
import com.wanghuiwen.core.response.Result;
import com.wanghuiwen.user.model.MediaResource;
import com.wanghuiwen.user.service.MediaResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
* Created by wanghuiwen on 2021/08/19.
*/
@Api(value = "媒体文件", tags = {"media_resource"})
@RestController
@RequestMapping("/media/resource")
public class MediaResourceController extends Ctrl{
    @Resource
    private MediaResourceService mediaResourceService;

    @Resource
    private UploadConfig uploadConfig;

    @ApiOperation(value = "媒体文件添加", tags = {"media_resource"}, notes = "media_resource添加")
    @PostMapping(value="/upload",name="媒体文件添加")
    public Result add(
            @RequestParam Integer resourceType,
            @RequestParam(required = false) String resourceUrl,
            @RequestParam(required = false) Long pid,
            @RequestParam(required = false) MultipartFile file,
            Authentication authentication) {
        MediaResource mediaResource = new MediaResource();
        mediaResource.setResourceType(resourceType);
        mediaResource.setPid(pid);
        if(file!=null){
            MediaResource resource = mediaResourceService.saveFile(file);
            mediaResource.setResourceUrl(resource.getResourceUrl());
            mediaResource.setVideoCover(resource.getVideoCover());
            mediaResource.setResourceType(resource.getResourceType());
        }else {
            mediaResource.setResourceUrl(resourceUrl);
        }

        mediaResource.setUserId(getAuthUser(authentication).getId());
        mediaResourceService.save(mediaResource);
        return resultGenerator.genSuccessResult(mediaResource);
    }

    @ApiOperation(value = "媒体文件删除", tags = {"media_resource"}, notes = "媒体文件删除")
    @PostMapping(value="/delete",name="媒体文件删除")
    public Result delete(Long resourceId) {
        mediaResourceService.deleteById(resourceId);
        return resultGenerator.genSuccessResult();
    }


    @ApiOperation(value = "访问媒体资源", tags = {"media_resource"}, notes = "访问媒体资源")
    @GetMapping(value="/view/{id}",name="访问媒体资源")
    public void add(@PathVariable Long id, HttpServletResponse response) {
        MediaResource mediaResource =  mediaResourceService.findById(id);
        try {
            File file = new File(uploadConfig.getFilePath()+mediaResource.getResourceUrl().replaceFirst(uploadConfig.getPrefix(),""));
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.setHeader("Content-Disposition", "attachment; filename="+file.getName().replace(" ", "_"));
            InputStream iStream = new FileInputStream(file);
            IOUtils.copy(iStream, response.getOutputStream());
            response.flushBuffer();
        } catch (java.nio.file.NoSuchFileException e) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }


    @ApiOperation(value = "媒体文件列表", tags = {"media_resource"}, notes = "媒体文件列表")
    @PostMapping(value="/list",name="媒体文件列表")
    public Result list() {
        List<MediaResource> resourceList = mediaResourceService.findAll();
        return resultGenerator.genSuccessResult(resourceList);
    }

    @ApiOperation(value = "用户媒体文件列表", tags = {"media_resource"}, notes = "用户媒体文件列表")
    @GetMapping(value="/list/user",name="用户媒体文件列表")
    public Result userList(@RequestParam(required = false) Long pid,Authentication authentication ) {
        Map<String,Object> params = new HashMap<>();
        params.put("pid",pid);
        params.put("userId",getAuthUser(authentication).getId());
        List<MediaResource> resourceList = mediaResourceService.userList(params);
        return resultGenerator.genSuccessResult(resourceList);
    }
}
