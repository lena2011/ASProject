package com.lena.asp.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.TextView;

import com.lena.asp.R;
import com.lena.asp.base.BaseActivity;
import com.lena.asp.utils.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import cat.ereza.customactivityoncrash.config.CaocConfig;

/**
 * @author lilingfei
 * @date 2018/9/8
 */
public class CrashActivity extends BaseActivity {
    @BindView(R.id.btn_close)
    Button mBtnClose;
    @BindView(R.id.btn_restart)
    Button mBtnRestart;
    @BindView(R.id.btn_crash_detail)
    Button mBtnCrashDetail;
    private CaocConfig mCaocConfig;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_custom_crash);

    }

    @Override
    protected void initView() {
        getConfig();
    }

    private void getConfig() {
        mCaocConfig = CustomActivityOnCrash.getConfigFromIntent(getIntent());
        LogUtil.i("cao=" + mCaocConfig);
        if (mCaocConfig == null) {
            finish();
        }
    }

    @OnClick(R.id.btn_close)
    public void onMBtnCloseClicked() {
        CustomActivityOnCrash.closeApplication(this, mCaocConfig);
    }

    @OnClick(R.id.btn_restart)
    public void onMBtnRestartClicked() {
        CustomActivityOnCrash.restartApplication(this, mCaocConfig);
    }

    @OnClick(R.id.btn_crash_detail)
    public void onMBtnCrashDetailClicked() {
        AlertDialog dialog = new AlertDialog.Builder(CrashActivity.this)
                .setTitle("错误详情")
                .setMessage(CustomActivityOnCrash.getAllErrorDetailsFromIntent(this, getIntent()))
                .setPositiveButton("关闭", null)
                .show();
        TextView textView = dialog.findViewById(android.R.id.message);
        if (textView != null) {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.customactivityoncrash_error_activity_error_details_text_size));
        }

    }

}
