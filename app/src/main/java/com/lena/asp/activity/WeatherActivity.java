package com.lena.asp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Pair;

import com.lena.asp.R;
import com.lena.asp.adapter.OneViewPagerAdapter;
import com.lena.asp.base.BaseActivity;
import com.lena.asp.fragment.CityFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author lilingfei
 * @date 2018/9/8
 */
public class WeatherActivity extends BaseActivity {
    @BindView(R.id.tl_weather_tab)
    TabLayout mTlWeatherTab;
    @BindView(R.id.vp_weather)
    ViewPager mVpWeather;

    private int[] citys = {1, 2};
    private OneViewPagerAdapter mViewPagerAdapter;
    private List<Pair<String, Fragment>> frags;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_weather);

    }

    @Override
    protected void initView() {
        frags = new ArrayList<>();
        for (int city : citys) {
            frags.add(new Pair<String, Fragment>("标题" + city, new CityFragment()));
        }
        mViewPagerAdapter = new OneViewPagerAdapter(getSupportFragmentManager(), frags);
        mVpWeather.setAdapter(mViewPagerAdapter);
        mTlWeatherTab.setupWithViewPager(mVpWeather);

    }
}
