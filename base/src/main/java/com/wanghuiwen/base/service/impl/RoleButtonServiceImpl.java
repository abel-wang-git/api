package com.wanghuiwen.base.service.impl;

import com.wanghuiwen.base.dao.RoleButtonMapper;
import com.wanghuiwen.base.model.RoleButton;
import com.wanghuiwen.base.service.RoleButtonService;
import com.wanghuiwen.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by wanghuiwen on 2021/08/17.
 */
@Service
@Transactional
public class RoleButtonServiceImpl extends AbstractService<RoleButton> implements RoleButtonService {
    @Resource
    private RoleButtonMapper roleButtonMapper;
}
