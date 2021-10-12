package com.wanghuiwen.base.service;
import com.wanghuiwen.base.model.Api;
import com.wanghuiwen.core.service.Service;
import com.wanghuiwen.core.response.Result;

import java.util.List;

/**
 * Created by wanghuiwen on 2021/07/13.
 */
public interface ApiService extends Service<Api> {

    Api selectByUrlAndMethod(String url, String method);

    List<Api> getByRole(Long roleId);

    List<Api> listAll();
}
