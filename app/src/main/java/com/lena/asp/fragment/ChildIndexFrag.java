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
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
