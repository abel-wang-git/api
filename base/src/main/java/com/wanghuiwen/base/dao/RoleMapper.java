package com.wanghuiwen.base.dao;

import com.wanghuiwen.base.model.Role;
import com.wanghuiwen.core.ApiMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper extends ApiMapper<Role> {
    List<Role> getByUser(Long userId);

    void deleteApiById(long roleId);

    void deleteMenuById(long roleId);

    void deleteButtonByRole(long roleId);
}