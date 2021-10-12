package com.wanghuiwen.base.service;
import com.wanghuiwen.base.model.Menu;
import com.wanghuiwen.core.service.Service;
import com.wanghuiwen.core.response.Result;

import java.util.List;

/**
 * Created by wanghuiwen on 2021/07/13.
 */
public interface MenuService extends Service<Menu> {
    List<Menu> getByRole(Long roleId);
}
