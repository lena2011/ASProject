package com.lena.asp.common.okgo;

import com.alibaba.fastjson.JSONObject;
import com.lena.asp.common.entity.BaseEntity;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;

/**
 * @author lilingfei
 * @date 2018/7/14
 */

public abstract class JsonCallback<T> extends AbsCallback<T> {
    private Class<T> mClass;

    @Override
    public T convertResponse(okhttp3.Response response) throws Throwable {
        T entity;
        if (response.body() == null) {
            return null;
        }
        String jsonStr = response.body().string();
        entity = JSONObject.parseObject(jsonStr, mClass);
        return entity;
    }
}
