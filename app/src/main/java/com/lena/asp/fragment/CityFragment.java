package com.lena.asp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lena.asp.R;
import com.lena.asp.base.BaseFragment;
import com.lena.asp.common.api.ItemApi;
import com.lena.asp.common.callback.CallbackApi;
import com.lena.asp.common.entity.ItemEntity;
import com.lena.asp.utils.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author lilingfei
 * @date 2018/9/8
 */
public class CityFragment extends BaseFragment {
    @BindView(R.id.tv_city)
    TextView mTvCity;
    Unbinder unbinder;

    private int cityId;

    public CityFragment getInstance(int cityId) {
        CityFragment cityFragment = new CityFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("cityId", cityId);
        cityFragment.setArguments(bundle);
        return cityFragment;
    }

    @Override
    public void initData() {
        ItemApi.getItemDetail(mActivity, "205", new CallbackApi<ItemEntity>() {
            @Override
            public void onSuccess(ItemEntity o) {
                LogUtil.i("o=" + o);
            }

            @Override
            public void onFail(String message) {

            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            Bundle bundle = getArguments();
            cityId = bundle.getInt("cityId");
            LogUtil.i("cityId=" + cityId);
        }
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_city, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
