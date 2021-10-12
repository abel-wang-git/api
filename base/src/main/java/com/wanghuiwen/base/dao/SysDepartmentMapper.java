package com.wanghuiwen.base.dao;

import com.wanghuiwen.base.model.SysDepartment;
import com.wanghuiwen.base.model.User;
import com.wanghuiwen.core.ApiMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysDepartmentMapper extends ApiMapper<SysDepartment> {
    List<SysDepartment> getChild(Long id);
}