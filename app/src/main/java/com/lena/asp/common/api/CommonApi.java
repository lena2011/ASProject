package com.lena.asp.common.api;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.lena.asp.common.callback.CallbackApi;
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
     * @param params
     * @param callbackApi
     */
    public static void getWeatherApi(Context mContext, HttpParams params, final CallbackApi<WeatherEntity> callbackApi) {
        OkClient.getInstance().getString(mContext, ApiUrl.weatherUrl, params, new CallbackOkClient<String>() {
            @Override
            public void onSuccess(String result) {
                WeatherEntity weatherEntity = JSONObject.parseObject(result, WeatherEntity.class);
                callbackApi.onSuccess(weatherEntity);

            }

            @Override
            public void onFail(String message) {
                callbackApi.onFail(message);
            }
        });

    }

}
