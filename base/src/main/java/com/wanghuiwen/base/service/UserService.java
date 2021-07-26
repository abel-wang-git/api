package com.wanghuiwen.base.service;
import com.wanghuiwen.base.model.User;
import com.wanghuiwen.core.config.AuthUser;
import com.wanghuiwen.core.service.Service;
import com.wanghuiwen.core.response.Result;

import java.util.List;
import java.util.Map;

/**
 * Created by wanghuiwen on 2021/07/13.
 */
public interface UserService extends Service<User> {
    List<User> list(Map<String, Object> params);

    Result addRole(List<Long> roles, Long userId);

    Result login(AuthUser userDetails);
}
