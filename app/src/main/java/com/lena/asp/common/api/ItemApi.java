package com.lena.asp.common.api;

import android.content.Context;

import com.lena.asp.common.callback.AbstractOkClientCallback;
import com.lena.asp.common.callback.CallbackApi;
import com.lena.asp.common.entity.ItemEntity;
import com.lena.asp.common.okgo.OkClient;
import com.lena.asp.utils.ApiUrl;
import com.lena.asp.utils.LogUtil;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;


/**
 * @author lilingfei
 * @date 2018/9/10
 */
public class ItemApi {
    public static void getItemDetail(Context context, String productId, final CallbackApi callbackApi) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("productid", productId);
        OkClient.getInstance().getString(context, ApiUrl.detailUtl, httpParams, new AbstractOkClientCallback<ItemEntity>(ItemEntity.class) {
            @Override
            public void onSuccess(Response<ItemEntity> response) {
                super.onSuccess(response);
                ItemEntity entity = response.body();
                callbackApi.onSuccess(entity);
            }
        });
    }
}
