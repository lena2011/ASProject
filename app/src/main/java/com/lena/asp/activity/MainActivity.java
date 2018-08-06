package com.lena.asp.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.lena.asp.R;
import com.lena.asp.base.BaseActivity;
import com.lena.asp.common.application.SysApplication;
import com.lena.asp.fragment.FourFrag;
import com.lena.asp.fragment.OneFrag;
import com.lena.asp.fragment.ThreeFrag;
import com.lena.asp.fragment.TwoFrag;
import com.lena.asp.utils.Constant;
import com.lena.asp.utils.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

public class MainActivity extends BaseActivity implements OneFrag.OnFragmentInteractionListener {


    @BindView(R.id.tv_tab_one)
    TextView tvTabOne;
    @BindView(R.id.tv_tab_two)
    TextView tvTabTwo;
    @BindView(R.id.layout_tab)
    LinearLayout layoutTab;
    @BindView(R.id.frag_content)
    FrameLayout fragContent;
    @BindView(R.id.tv_tab_three)
    RadioButton tvTabThree;
    @BindView(R.id.tv_tab_four)
    RadioButton tvTabFour;
    private OneFrag mOneFrag;
    private TwoFrag mTwoFrag;
    private ThreeFrag mThreeFrag;
    private FourFrag mFourFrag;
    private Fragment mCurrentFrag = new Fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment();
        if (savedInstanceState == null) {
            customFrag(mOneFrag);
        } else {
            mCurrentFrag = getSupportFragmentManager().findFragmentById(R.id.frag_content);
        }
        connect(Constant.IMToken);

    }

    private void initFragment() {

        mOneFrag = OneFrag.newInstance("one", "1");
        mTwoFrag = new TwoFrag();
        mThreeFrag = new ThreeFrag();
        mFourFrag = new FourFrag();

    }

    private void customFrag(Fragment fragment) {
        LogUtil.i("mcurrentFrag=" + fragment);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (mCurrentFrag != fragment) {
            fragmentTransaction.hide(mCurrentFrag);
            mCurrentFrag = fragment;
            if (!mCurrentFrag.isAdded()) {
                fragmentTransaction.add(R.id.frag_content, mCurrentFrag);
            }

        }
        fragmentTransaction.show(mCurrentFrag);
        fragmentTransaction.commit();


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * <p>连接服务器，在整个应用程序全局，只需要调用一次，需在 {@link #init(Context)} 之后调用。</p>
     * <p>如果调用此接口遇到连接失败，SDK 会自动启动重连机制进行最多10次重连，分别是1, 2, 4, 8, 16, 32, 64, 128, 256, 512秒后。
     * 在这之后如果仍没有连接成功，还会在当检测到设备网络状态变化时再次进行重连。</p>
     *
     * @param IMToken 从服务端获取的用户身份令牌（Token）。
     * @return RongIM  客户端核心类的实例。
     */
    private void connect(String IMToken) {
        LogUtil.i( getApplicationInfo().packageName + "," + SysApplication.getProcessName(android.os.Process.myPid()));
        if (getApplicationInfo().packageName.equals(SysApplication.getProcessName(android.os.Process.myPid()))) {

            RongIM.connect(IMToken, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                @Override
                public void onTokenIncorrect() {
                    LogUtil.e("onTokenIncorrect");
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token 对应的用户 id
                 */
                @Override
                public void onSuccess(String userid) {
                    LogUtil.i("userID=" + userid);
                    startActivity(new Intent(MainActivity.this, ConversationListActivity.class));

                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    LogUtil.e("errorcode" + errorCode + "message" + errorCode.getMessage());
                }
            });
        }

    }


    @OnClick({R.id.tv_tab_one, R.id.tv_tab_two, R.id.tv_tab_three, R.id.tv_tab_four})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_tab_one:
                customFrag(mOneFrag);
                break;
            case R.id.tv_tab_two:
                customFrag(mTwoFrag);
                break;
            case R.id.tv_tab_three:
                customFrag(mThreeFrag);
                break;
            case R.id.tv_tab_four:
                customFrag(mFourFrag);
                break;
            default:
                break;
        }
    }


}
