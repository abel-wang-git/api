package com.wanghuiwen.base.service.impl;

import com.wanghuiwen.base.dao.MenuMapper;
import com.wanghuiwen.base.model.Menu;
import com.wanghuiwen.base.service.MenuService;
import com.wanghuiwen.core.service.AbstractService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by wanghuiwen on 2021/07/13.
 */
@Service
@Transactional
public class MenuServiceImpl extends AbstractService<Menu> implements MenuService {
    @Resource
    private MenuMapper menuMapper;

    @Override
    @Cacheable(value = "Role::Menu",key = "#roleId")
    public List<Menu> getByRole(Long roleId) {
        return menuMapper.getByRole(roleId);
    }
}
