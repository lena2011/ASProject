package com.lena.asp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.lena.asp.R;
import com.lena.asp.adapter.OneViewPagerAdapter;
import com.lena.asp.base.BaseAppActivity;
import com.lena.asp.fragment.CityFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author lilingfei
 * @date 2018/9/8
 */
public class WeatherActivity extends BaseAppActivity {
    @BindView(R.id.tl_weather_tab)
    TabLayout mTlWeatherTab;
    @BindView(R.id.vp_weather)
    ViewPager mVpWeather;

    private int[] citys = {1, 2};
    private List<Fragment> frags = new ArrayList<>();
    private OneViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_weather);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        for (int city : citys) {
            frags.add(new CityFragment().getInstance(city));
        }
        mViewPagerAdapter = new OneViewPagerAdapter(getSupportFragmentManager(), frags);
        mVpWeather.setAdapter(mViewPagerAdapter);
        mTlWeatherTab.setupWithViewPager(mVpWeather);
        for (int i = 0; i < citys.length; i++) {
            mTlWeatherTab.getTabAt(i).setText("标题");
        }
    }
}
