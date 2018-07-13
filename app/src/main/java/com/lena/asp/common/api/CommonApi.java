package com.lena.asp.common.api;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.lena.asp.common.callback.CallbackApi;
import com.lena.asp.common.entity.WeatherEntity;
import com.lena.asp.utils.ApiUrl;
import com.lena.asp.utils.LogUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

/**
 * Created by lilingfei on 2018/7/13.
 */

public class CommonApi {
    public static void getWeatherApi(Context mContext, HttpParams params, final CallbackApi<WeatherEntity> callbackApi) {
        OkGo.<String>get(ApiUrl.weatherUrl)
                .tag(mContext)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtil.i("response=" + response.body());
                        if (response.body() != null) {
                            WeatherEntity weatherEntity = JSONObject.parseObject(response.body(), WeatherEntity.class);
                            callbackApi.onSuccess(weatherEntity);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        callbackApi.onFail(response.message());
                    }
                });
    }

}
