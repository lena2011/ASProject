package com.lena.asp.common.callback;

/**
 * Created by lilingfei on 2018/7/13.
 */

public interface CallbackApi<T> {
    void onSuccess(T t);

    void onFail(String message);
}
