package com.wanghuiwen.base.dao;

import com.wanghuiwen.base.model.User;
import com.wanghuiwen.core.ApiMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper extends ApiMapper<User> {
    List<User> list(@Param("params") Map<String, Object> params);

    void deleteRoleById(Long userId);

    void deleteRoleByIdNoAdmin(Long userId);

    List<User> getByDepartment(List<Long> id);
}