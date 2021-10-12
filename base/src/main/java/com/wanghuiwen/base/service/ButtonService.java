package com.wanghuiwen.base.service;
import com.wanghuiwen.base.model.Button;
import com.wanghuiwen.core.service.Service;
import com.wanghuiwen.core.response.Result;

import java.util.List;

/**
 * Created by wanghuiwen on 2021/08/17.
 */
public interface ButtonService extends Service<Button> {
    List<Button> getByRole(Long id);
}
