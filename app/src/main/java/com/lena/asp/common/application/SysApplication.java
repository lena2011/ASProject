package com.lena.asp.common.application;

import android.app.Application;
import android.text.TextUtils;

import com.lena.asp.utils.Constant;
import com.lena.asp.utils.LogUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import okhttp3.OkHttpClient;

/**
 * Created by lilingfei on 2018/7/13.
 */

public class SysApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initOKGo();
        initRongYun();

    }

    private void initRongYun() {
        RongIM.init(this);
    }


    /**
     * 全局配置initgo，可以配置超时时间，cookie管理，加拦截器，信任证书
     * 全局公共头，公共参数
     */
    private void initOKGo() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));
        OkGo.getInstance().init(this)
                .setOkHttpClient(builder.build())
                .setCacheMode(CacheMode.NO_CACHE)
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                .setRetryCount(3)
        ;
    }


    /**
     * 获取进程号对应的进程名
     */
    public static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }
}
