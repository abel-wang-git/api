package com.wanghuiwen.base.service.impl;

import com.wanghuiwen.base.dao.ButtonMapper;
import com.wanghuiwen.base.model.Button;
import com.wanghuiwen.base.service.ButtonService;
import com.wanghuiwen.core.service.AbstractService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by wanghuiwen on 2021/08/17.
 */
@Service
@Transactional
public class ButtonServiceImpl extends AbstractService<Button> implements ButtonService {
    @Resource
    private ButtonMapper buttonMapper;


    @Override
    @Cacheable(value = "Role::Button",key = "#roleId")
    public List<Button> getByRole(Long id) {
        return buttonMapper.getByRole(id);
    }
}
