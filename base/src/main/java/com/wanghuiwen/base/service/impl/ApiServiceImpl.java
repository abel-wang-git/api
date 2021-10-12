package com.wanghuiwen.base.service.impl;

import com.wanghuiwen.base.config.ProjectConstant;
import com.wanghuiwen.base.dao.ApiMapper;
import com.wanghuiwen.base.dao.RoleMapper;
import com.wanghuiwen.base.model.Api;
import com.wanghuiwen.base.model.Role;
import com.wanghuiwen.base.model.RoleMenu;
import com.wanghuiwen.base.service.ApiService;
import com.wanghuiwen.core.service.AbstractService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by wanghuiwen on 2021/07/13.
 */
@Service
@Transactional
public class ApiServiceImpl extends AbstractService<Api> implements ApiService {
    @Resource
    private ApiMapper apiMapper;
    @Resource
    private RoleMapper roleMapper;

    @Override
    public Api selectByUrlAndMethod(String url, String method) {
        return apiMapper.selectByUrlAndMethod(url,method);
    }

    @Override
    @Cacheable(value="Role::Api",key = "#roleId")
    public List<Api> getByRole(Long roleId) {
        return apiMapper.getByRole(roleId);
    }

    @Override
    @Cacheable(value= ProjectConstant.API_LIST_CACHE_KEY,keyGenerator = "baseKeyGenerator")
    public List<Api> listAll() {
        return apiMapper.selectAll();
    }
}
