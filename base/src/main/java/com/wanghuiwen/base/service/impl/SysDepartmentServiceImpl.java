package com.wanghuiwen.base.service.impl;

import com.wanghuiwen.base.dao.SysDepartmentMapper;
import com.wanghuiwen.base.model.SysDepartment;
import com.wanghuiwen.base.service.SysDepartmentService;
import com.wanghuiwen.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by wanghuiwen on 2021/07/24.
 */
@Service
@Transactional
public class SysDepartmentServiceImpl extends AbstractService<SysDepartment> implements SysDepartmentService {
    @Resource
    private SysDepartmentMapper sysDepartmentMapper;
}
