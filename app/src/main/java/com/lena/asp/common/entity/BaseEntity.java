package com.lena.asp.common.entity;

/**
 * Created by lilingfei on 2018/7/13.
 */

public class BaseEntity {


    /**
     * ErrorCode : 0
     * Token : null
     * Count : 0
     */

    private int ErrorCode;
    private Object Token;
    private int Count;

    public int getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(int ErrorCode) {
        this.ErrorCode = ErrorCode;
    }

    public Object getToken() {
        return Token;
    }

    public void setToken(Object Token) {
        this.Token = Token;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int Count) {
        this.Count = Count;
    }
}
