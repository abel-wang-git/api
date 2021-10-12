package com.wanghuiwen.user.service;
import com.wanghuiwen.user.model.MediaResource;
import com.wanghuiwen.core.service.Service;
import com.wanghuiwen.core.response.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by wanghuiwen on 2021/08/19.
 */
public interface MediaResourceService extends Service<MediaResource> {
    List<MediaResource> userList(Map<String, Object> params);

    MediaResource saveFile(MultipartFile file);
}
