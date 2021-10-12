package com.wanghuiwen.base.dao;

import com.wanghuiwen.base.model.Button;
import com.wanghuiwen.core.ApiMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ButtonMapper extends ApiMapper<Button> {
    List<Button> getByRole(Long id);
}