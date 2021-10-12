package com.wanghuiwen.base.service.impl;

import com.wanghuiwen.base.config.ProjectConstant;
import com.wanghuiwen.base.dao.SysWhitelistMapper;
import com.wanghuiwen.base.model.SysWhitelist;
import com.wanghuiwen.base.service.SysWhitelistService;
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
public class SysWhitelistServiceImpl extends AbstractService<SysWhitelist> implements SysWhitelistService {
    @Resource
    private SysWhitelistMapper sysWhitelistMapper;

    @Override
    @Cacheable(value=ProjectConstant.WHITE_LIST_CACHE_KEY,keyGenerator = "baseKeyGenerator" )
    public List<SysWhitelist> listAll() {
        return sysWhitelistMapper.selectAll();
    }
}
