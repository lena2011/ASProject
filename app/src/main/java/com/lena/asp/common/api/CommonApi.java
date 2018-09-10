package com.lena.asp.common.api;

import android.content.Context;

import com.lena.asp.common.callback.CallbackApi;
import com.lena.asp.common.callback.AbstractOkClientCallback;
import com.lena.asp.common.entity.WeatherEntity;
import com.lena.asp.common.okgo.OkClient;
import com.lena.asp.utils.ApiUrl;
import com.lena.asp.utils.LogUtil;
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
        OkClient.getInstance().getString(mContext, ApiUrl.weatherUrl, httpParams, new AbstractOkClientCallback<WeatherEntity>(WeatherEntity.class) {
            @Override
            public void onSuccess(Response<WeatherEntity> response) {
                super.onSuccess(response);
                LogUtil.i("response=" + response.body());
//                WeatherEntity weatherEntity = JSONObject.parseObject(response.body());
//                callbackApi.onSuccess(response.body());
            }
        });

    }

}
