package com.wanghuiwen.user.model;

import java.util.List;

import static java.util.Arrays.asList;

public class Resource {

    // 1 图片 2 视频 3目录
    public static final int RESOURCE_VIDEO = 2;
    public static final int RESOURCE_IMAGE = 1;
    public static final int RESOURCE_DIR = 3;
    List<String> videos = asList("mp4");
    List<String> image = asList("jpg","jpeg","png");
    /**
     * 通过后缀名判断类型
     * @param extension 0 时是不支持的文件类型
     */
    public int getResourceTypeByExtension(String extension) {
        for (String video : videos) {
            if(video.equalsIgnoreCase(extension)){
                return RESOURCE_VIDEO;
            }
        }

        for (String img : image) {
            if(img.equalsIgnoreCase(extension)){
                return RESOURCE_IMAGE;
            }

        }
        return 0;
    }
}
