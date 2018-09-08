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
import android.widget.Toast;

import com.lena.asp.R;
import com.lena.asp.base.BaseAppActivity;
import com.lena.asp.common.application.SysApplication;
import com.lena.asp.fragment.FourFrag;
import com.lena.asp.fragment.OneFrag;
import com.lena.asp.fragment.ThreeFrag;
import com.lena.asp.fragment.TwoFrag;
import com.lena.asp.utils.LogUtil;
import com.lena.asp.utils.SharedPreferenceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

public class MainActivity extends BaseAppActivity implements OneFrag.OnFragmentInteractionListener {


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
//        connect(Constant.IMToken2012);

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

    /**
     * <p>连接服务器，在整个应用程序全局，只需要调用一次，需在 {@link } 之后调用。</p>
     * <p>如果调用此接口遇到连接失败，SDK 会自动启动重连机制进行最多10次重连，分别是1, 2, 4, 8, 16, 32, 64, 128, 256, 512秒后。
     * 在这之后如果仍没有连接成功，还会在当检测到设备网络状态变化时再次进行重连。</p>
     *
     * @param IMToken 从服务端获取的用户身份令牌（Token）。
     * @return RongIM  客户端核心类的实例。
     */
    private void connect(String IMToken) {

        if (getApplicationInfo().packageName.equals(SysApplication.getProcessName(android.os.Process.myPid()))) {
            LogUtil.i("token=" + IMToken);
            RongIM.connect(IMToken, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                @Override
                public void onTokenIncorrect() {
                    Toast.makeText(MainActivity.this, "onTokenIncorrect", Toast.LENGTH_SHORT).show();
                    // TODO: 2018/8/7 token获取错误的时候需要重新获取
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token 对应的用户 id
                 */
                @Override
                public void onSuccess(String userid) {
                    LogUtil.i("userID=" + userid);
                    SharedPreferenceUtil.saveUserId(MainActivity.this, userid);
                    Toast.makeText(MainActivity.this, "onSuccess", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, ConversationListActivity.class));
                    updateUserProvider();
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    Toast.makeText(MainActivity.this, "onError", Toast.LENGTH_SHORT).show();
                    LogUtil.e("errorcode" + errorCode + "message" + errorCode.getMessage());
                }

            });

        }

    }

    private void updateUserProvider() {
        RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
            @Override
            public UserInfo getUserInfo(String s) {
                // TODO: 2018/8/8 如果是异步获取用户信息，需要再异步回调接口中刷新用户信息和群组信息
//                RongIM.getInstance().refreshUserInfoCache(new UserInfo());
//                RongIM.getInstance().refreshGroupInfoCache(new Group());
                return findUserById(s);
            }
        }, true);
    }

    /**
     * @param s
     * @return
     */
    private UserInfo findUserById(String s) {
        UserInfo userInfo = null;
        if (s.equals("2011")) {
            userInfo = new UserInfo("2011", "lena", Uri.parse("http://rongcloud-web.qiniudn.com/docs_demo_rongcloud_logo.png"));
        } else {
            userInfo = new UserInfo("2012", "zaoren", Uri.parse("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1533703776647&di=00c33b7a0618a631d948626e466943f8&imgtype=0&src=http%3A%2F%2Fwww.qqzhi.com%2Fuploadpic%2F2015-01-29%2F075027854.jpg"));
        }
        return userInfo;

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
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
    protected void onResume() {
        super.onResume();
//        startActivity(new Intent(MainActivity.this, ConversationListActivity.class));
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

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
