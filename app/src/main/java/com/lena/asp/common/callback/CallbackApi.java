package com.lena.asp.common.callback;

/**
 * @author lilingfei
 * @date 2018/7/13
 */

public interface CallbackApi<T> {
    /**
     * 调用接口返回成功
     * @param t
     */
    void onSuccess(T t);

    /**
     * 调用接口返回失败
     * @param message
     */
    void onFail(String message);
}
