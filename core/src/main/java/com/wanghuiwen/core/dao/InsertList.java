package com.wanghuiwen.core.dao;

import com.wanghuiwen.core.dao.provider.InsertListProvider;
import org.apache.ibatis.annotations.InsertProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.List;

@RegisterMapper
public interface InsertList<T> {

    @InsertProvider(
            type = InsertListProvider.class,
            method = "insertListNoAuto"
    )
    int insertListNoAuto(List<? extends T> var1);
}
