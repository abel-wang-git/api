package com.wanghuiwen.user.dao;

import com.wanghuiwen.core.ApiMapper;
import com.wanghuiwen.user.model.MediaResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MediaResourceMapper extends ApiMapper<MediaResource> {
    List<MediaResource> userList(@Param("params") Map<String, Object> params);
}