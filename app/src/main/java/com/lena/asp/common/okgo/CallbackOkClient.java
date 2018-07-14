package com.lena.asp.common.okgo;

/**
 * @auther lilingfei
 * @date 2018/7/14
 */

public interface CallbackOkClient<T> {
    void onSuccess(T t);

    void onFail(String message);
}
