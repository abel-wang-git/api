package com.wanghuiwen.base.service;
import com.wanghuiwen.base.model.SysWhitelist;
import com.wanghuiwen.core.service.Service;
import com.wanghuiwen.core.response.Result;

import java.util.List;

/**
 * Created by wanghuiwen on 2021/07/13.
 */
public interface SysWhitelistService extends Service<SysWhitelist> {
    List<SysWhitelist> listAll();
}
