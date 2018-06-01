package com.lena.asp.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.lena.asp.R;
import com.lena.asp.fragment.FourFrag;
import com.lena.asp.fragment.OneFrag;
import com.lena.asp.fragment.ThreeFrag;
import com.lena.asp.fragment.TwoFrag;
import com.lena.asp.utils.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements OneFrag.OnFragmentInteractionListener {


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
        }else {
            mCurrentFrag = getSupportFragmentManager().findFragmentById(R.id.frag_content);
        }

    }

    private void initFragment() {

        mOneFrag = OneFrag.newInstance("one", "1");
        mTwoFrag = new TwoFrag();
        mThreeFrag = new ThreeFrag();
        mFourFrag = new FourFrag();

    }

    private void customFrag(Fragment fragment) {
        LogUtil.i("mcurrentFrag="+fragment);
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
    protected void onDestroy() {
        super.onDestroy();
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
        }
    }
}
