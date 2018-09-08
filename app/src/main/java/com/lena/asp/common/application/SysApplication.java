package com.lena.asp.common.application;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.lena.asp.activity.CrashActivity;
import com.lena.asp.activity.MainActivity;
import com.lena.asp.activity.WeatherActivity;
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

import cat.ereza.customactivityoncrash.config.CaocConfig;
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
        initCrashConfig();

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

    private void initRongYun() {
        RongIM.init(this);
        RongIM.setConnectionStatusListener(new RongIMClient.ConnectionStatusListener() {
            @Override
            public void onChanged(ConnectionStatus connectionStatus) {
                LogUtil.i("connection=" + connectionStatus);
                switch (connectionStatus) {
                    case CONNECTED:
//                        连接成功
                        break;
                    case DISCONNECTED:
//                        断开连接
                        Toast.makeText(getApplicationContext(), "断开连接", Toast.LENGTH_SHORT).show();
                        reconnect();
                        break;
                    case CONNECTING:
//                        连接中
                        break;
                    case NETWORK_UNAVAILABLE:
//                        网络不可用

                        break;
                    case KICKED_OFFLINE_BY_OTHER_CLIENT:
//                        用户账户在其他设备登陆
                        break;
                    default:
                        break;

                }
            }
        });
    }

    private void initCrashConfig() {
        CaocConfig.Builder.create()
                .errorActivity(CrashActivity.class)
                .restartActivity(WeatherActivity.class)
                .apply();
    }

    private void reconnect() {
        if (getApplicationInfo().packageName.equals(SysApplication.getProcessName(android.os.Process.myPid()))) {
            RongIM.connect(Constant.IMToken2012, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                @Override
                public void onTokenIncorrect() {
                    Toast.makeText(getApplicationContext(), "onTokenIncorrect", Toast.LENGTH_SHORT).show();
                    // TODO: 2018/8/7 token获取错误的时候需要重新获取
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token 对应的用户 id
                 */
                @Override
                public void onSuccess(String userid) {
                    LogUtil.i("userID=" + userid);
                    Toast.makeText(getApplicationContext(), "onSuccess", Toast.LENGTH_SHORT).show();

                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    Toast.makeText(getApplicationContext(), "onError", Toast.LENGTH_SHORT).show();
                    LogUtil.e("errorcode" + errorCode + "message" + errorCode.getMessage());
                }

            });

        }
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
