package com.lena.asp.common.okgo;

import android.content.Context;
import android.telecom.Call;

import com.lena.asp.common.callback.CallbackApi;
import com.lena.asp.common.entity.BaseEntity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

/**
 * @auther lilingfei
 * @date 2018/7/14
 */

public class OkClient {
    private static OkClient mOkClient;


    public static OkClient getInstance() {
        if (mOkClient == null) {
            mOkClient = new OkClient();
        }
        return mOkClient;
    }


    public void getString(Context mContext, String url, HttpParams httpParams, final CallbackOkClient callbackOkClient) {
        OkGo.<String>get(url)
                .tag(mContext)
                .params(httpParams)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response.body() != null) {
                            callbackOkClient.onSuccess(response.body());
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        callbackOkClient.onFail(response.message());
                    }
                });
    }

    public void postString(Context mContext, String url, HttpParams httpParams, final CallbackOkClient callbackOkClient) {
        OkGo.<String>post(url)
                .tag(mContext)
                .params(httpParams)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (response.body() != null) {
                            callbackOkClient.onSuccess(response.body());
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        callbackOkClient.onFail(response.message());
                    }
                });
    }


    public void cancelTag(Context mContext) {
        OkGo.getInstance().cancelTag(mContext);
    }
}
