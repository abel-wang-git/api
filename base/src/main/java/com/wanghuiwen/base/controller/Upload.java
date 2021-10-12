package com.wanghuiwen.base.controller;

import com.wanghuiwen.common.ImageUploadUtil;
import com.wanghuiwen.common.config.UploadConfig;
import com.wanghuiwen.core.response.Result;
import com.wanghuiwen.core.response.ResultEnum;
import com.wanghuiwen.core.response.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/6.
 */
@Api(value = "文件上传", tags = {"文件上传"})
@Controller
public class Upload {
    @Resource
    private ResultGenerator resultGenerator;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UploadConfig uploadConfig;

    /**
     * 单图图上传　
     *
     * @param request
     * @return
     */
    @ApiOperation(value="富文本上传",tags={"文件上传"},notes="富文本上传")
    @RequestMapping(value = "/upload", method = RequestMethod.POST,name = "富文本上传")
    @ResponseBody
    public Map<String, String> uploadImages(HttpServletRequest request) {
        String up = "image/";
        Map<String, String> res = new HashMap<>();

        try {
            String paths = ImageUploadUtil.upload(request, uploadConfig.getFilePath() + up);
            for (int i = 0; i < paths.split(",").length; i++) {
                res.put("default", uploadConfig.getHost() + uploadConfig.getPrefix() + up + paths.split(",")[i]);
            }
            return res;
        } catch (IOException e) {
           logger.error("图片上传出错",e);
        }
        return res;
    }



    /**
     * 身份证上传
     */
    @ApiOperation(value="图片上传",tags={"文件上传"},notes="图片上传")
    @RequestMapping(value = "/img/upload", method = RequestMethod.POST,name = "图片上传")
    @ResponseBody
    public Result uploadIdCard(HttpServletRequest request) {
        String up = "img/";
        try {
            String spath = ImageUploadUtil.upload(request, uploadConfig.getFilePath() + up);
            return resultGenerator.genSuccessResult(ResultEnum.UPLOADED,uploadConfig.getPrefix() + up + spath.split(",")[0]);
        } catch (IOException e) {
            logger.error("图片上传出错",e);
            return resultGenerator.genResult(ResultEnum.UPLOADED_FAIL);
        }
    }

    /**
     * app安装包
     */
    @ApiOperation(value="apk上传",tags={"文件上传"},notes="apk上传")
    @RequestMapping(value = "/apk/upload", method = RequestMethod.POST)
    @ResponseBody
    public Result uploadApk(HttpServletRequest request) {
        // 创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        // 图片名称
        String fileName = "";
        // 判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                // 记录上传过程起始时的时间，用来计算上传时间
                // int pre = (int) System.currentTimeMillis();
                // 取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    // 取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if (myFileName.trim() != "") {
                        // 获得图片的原始名称
                        String originalFilename = file.getOriginalFilename();
                        // 获得图片后缀名称,如果后缀不为图片格式，则不上传
                        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
                        originalFilename = originalFilename.replace(suffix, "");
                        if (!".apk".equals(suffix)) {
                            throw new RuntimeException("不支持文件格式" + suffix);
                        }

                        File upload = new File(uploadConfig.getFilePath() + "apk" + File.separator);

                        if (!upload.exists()) {
                            upload.mkdirs();
                        }
                        File uploadFile = new File(upload + File.separator + originalFilename + suffix);
                        try {
                            fileName = (originalFilename + suffix);

                            file.transferTo(uploadFile);
                            return resultGenerator.genSuccessResult(uploadConfig.getHost() + uploadConfig.getPrefix()+ "apk/" + fileName);
                        } catch (IOException e) {
                            logger.error("图片上传出错",e);
                            return resultGenerator.genResult(ResultEnum.UPLOADED_FAIL);
                        }
                    }
                }
            }
        }
        return resultGenerator.genResult(ResultEnum.UPLOADED_FAIL);
    }
}
