package com.lena.asp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lena.asp.R;
import com.lena.asp.common.api.CommonApi;
import com.lena.asp.common.callback.CallbackApi;
import com.lena.asp.common.entity.WeatherEntity;
import com.lena.asp.utils.StringUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * File description.
 *
 * @author Administrator
 * @date 2018/5/31
 */
public class ChildIndexFrag extends Fragment {
    Unbinder unbinder;
    @BindView(R.id.tv_weather)
    TextView mTvWeather;
    @BindView(R.id.tv_pm25)
    TextView mTvPm25;
    @BindView(R.id.tv_pm10)
    TextView mTvPm10;
    @BindView(R.id.tv_quality)
    TextView mTvQuality;
    private String type;

    public ChildIndexFrag getInstance(String type) {
        ChildIndexFrag indexFrag = new ChildIndexFrag();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        indexFrag.setArguments(bundle);
        return indexFrag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != getArguments()) {
            type = getArguments().getString("typ");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_child_index, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("city", "北京");
        CommonApi.getWeatherApi(getActivity(), httpParams, new CallbackApi<WeatherEntity>() {
            @Override
            public void onSuccess(WeatherEntity weatherEntity) {
                updateData(weatherEntity);
            }

            @Override
            public void onFail(String message) {

            }
        });
    }

    private void updateData(WeatherEntity weatherEntity) {
        if (null == weatherEntity.getData()) {
            return;
        }
        mTvWeather.setText(weatherEntity.getData().getShidu());
        mTvPm25.setText(StringUtils.formatD(weatherEntity.getData().getPm25()));
        mTvPm10.setText(StringUtils.formatD(weatherEntity.getData().getPm10()));
        mTvQuality.setText(weatherEntity.getData().getQuality());


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
