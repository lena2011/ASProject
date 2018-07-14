package com.lena.asp.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lena.asp.common.okgo.OkClient;

/**
 * @author lilingfei
 * @date 2018/7/14
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkClient.getInstance().cancelTag(this);
    }
}
