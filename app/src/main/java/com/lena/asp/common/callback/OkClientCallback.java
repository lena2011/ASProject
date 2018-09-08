package com.lena.asp.common.callback;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lena.asp.common.entity.BaseEntity;
import com.lena.asp.utils.LogUtil;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;

/**
 * @author lilingfei
 * @date 2018/9/8
 */
public abstract class OkClientCallback<T> extends AbsCallback<T> {
    private Class<T> mClass;
    private BaseEntity mBaseEntity;

    public OkClientCallback(Class<T> mClass) {
        this.mClass = mClass;
    }

    /**
     * ui线程,判断token失效问题
     *
     * @param response
     */
    @Override
    public void onSuccess(Response<T> response) {
        LogUtil.i("response=" + response.body());
    }

    /**
     * 子线程
     *
     * @param response
     * @return
     * @throws Throwable
     */
    @Override
    public T convertResponse(okhttp3.Response response) throws Throwable {
        LogUtil.i("response=" + response.body().string());
        T entity = null;
        synchronized (this) {
            try {
                if (response.body() != null) {
                    String json = response.body().string();
                    entity = JSON.parseObject(json, mClass);
                    mBaseEntity = (BaseEntity) entity;
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return entity;
    }
}
