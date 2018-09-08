package com.lena.asp.common.api;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.lena.asp.common.callback.CallbackApi;
import com.lena.asp.common.callback.OkClientCallback;
import com.lena.asp.common.entity.WeatherEntity;
import com.lena.asp.common.okgo.CallbackOkClient;
import com.lena.asp.common.okgo.OkClient;
import com.lena.asp.utils.ApiUrl;
import com.lena.asp.utils.LogUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

/**
 * @author lilingfei
 * @date 2018/7/13
 */

public class CommonApi {

    /**
     * 获取天气接口
     *
     * @param mContext
     * @param city
     * @param callbackApi
     */
    public static void getWeatherApi(Context mContext, String city, final CallbackApi<WeatherEntity> callbackApi) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("city", city);
        OkClient.getInstance().getString(mContext, ApiUrl.weatherUrl, httpParams, new OkClientCallback() {
            @Override
            public void onSuccess(Response response) {
                super.onSuccess(response);
                LogUtil.i("response=" + response);
            }
        });

    }

}
