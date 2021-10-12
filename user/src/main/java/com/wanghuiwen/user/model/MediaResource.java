package com.wanghuiwen.user.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Table(name = "media_resource")
public class MediaResource extends Resource implements Serializable   {
    @Id
    @Column(name = "resource_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resourceId;

    /**
     * 资源路径
     */
    @Column(name = "resource_url")
    @NotEmpty(message = "media.resourceUrl.NotEmpty")
    private String resourceUrl;

    /**
     * 资源类型 1 图片 2 视频 3目录
     */
    @Column(name = "resource_type")
    @NotNull(message = "media.resourceType.NotEmpty")
    private Integer resourceType;

    @Column(name = "user_id")
    private Long userId;

    /**
     * 视频封面
     */
    @Column(name = "video_cover")
    private String videoCover;

    private Long pid;

    private static final long serialVersionUID = 1L;

    /**
     * @return resource_id
     */
    public Long getResourceId() {
        return resourceId;
    }

    /**
     * @param resourceId
     */
    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * 获取资源路径
     *
     * @return resource_url - 资源路径
     */
    public String getResourceUrl() {
        return resourceUrl;
    }

    /**
     * 设置资源路径
     *
     * @param resourceUrl 资源路径
     */
    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    /**
     * 获取资源类型 1 图片 2 视频 3目录
     *
     * @return resource_type - 资源类型 1 图片 2 视频 3目录
     */
    public Integer getResourceType() {
        return resourceType;
    }

    /**
     * 设置资源类型 1 图片 2 视频 3目录
     *
     * @param resourceType 资源类型 1 图片 2 视频 3目录
     */
    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取视频封面
     *
     * @return video_cover - 视频封面
     */
    public String getVideoCover() {
        return videoCover;
    }

    /**
     * 设置视频封面
     *
     * @param videoCover 视频封面
     */
    public void setVideoCover(String videoCover) {
        this.videoCover = videoCover;
    }

    /**
     * @return pid
     */
    public Long getPid() {
        return pid;
    }

    /**
     * @param pid
     */
    public void setPid(Long pid) {
        this.pid = pid;
    }
}