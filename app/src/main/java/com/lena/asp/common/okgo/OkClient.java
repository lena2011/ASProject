package com.lena.asp.common.okgo;

import android.content.Context;

import com.lena.asp.common.callback.AbstractOkClientCallback;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.HttpParams;

/**
 * @auther lilingfei
 * @date 2018/7/14
 */

public class OkClient {
    private static OkClient mOkClient = null;
    private int retryCount = 0;

    public OkClient() {
    }

    public static OkClient getInstance() {
        if (mOkClient == null) {
            synchronized (OkClient.class) {
                if (mOkClient == null) {
                    mOkClient = new OkClient();
                }
            }
        }
        return mOkClient;
    }


    public void getString(Context mContext, String url, HttpParams httpParams, final AbsCallback callback) {
        OkGo.<String>get(url)
                .tag(mContext)
                .params(httpParams)
                .retryCount(retryCount)
                .execute(callback);
    }

    public void postString(Context mContext, String url, HttpParams httpParams, final AbsCallback callback) {
        OkGo.<String>post(url)
                .tag(mContext)
                .params(httpParams)
                .retryCount(retryCount)
                .execute(callback);
    }


    public void cancelTag(Context mContext) {
        OkGo.getInstance().cancelTag(mContext);
    }
}
