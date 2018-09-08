package com.lena.asp.fragment;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lena.asp.R;
import com.lena.asp.common.api.CommonApi;
import com.lena.asp.common.callback.CallbackApi;
import com.lena.asp.common.entity.WeatherEntity;
import com.lena.asp.utils.LogUtil;
import com.lena.asp.utils.StringUtils;
import com.lzy.okgo.model.HttpParams;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

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
    @BindView(R.id.iv_pic)
    ImageView mIvPic;
    @BindView(R.id.tv_rongyun)
    TextView mTvRongyun;
    @BindView(R.id.ll_child_index)
    LinearLayout mLlChildIndex;
    private String type;

    public ChildIndexFrag() {
    }

    public static ChildIndexFrag getInstance(String type) {
        ChildIndexFrag indexFrag = new ChildIndexFrag();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        indexFrag.setArguments(bundle);
        return indexFrag;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtil.i("setUserVisibleHint" + isVisibleToUser);
        if (isVisibleToUser && mLlChildIndex != null) {
            mLlChildIndex.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.i("onCreate");
        if (null != getArguments()) {
            type = getArguments().getString("typ");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtil.i("onCreateView");
        View view = inflater.inflate(R.layout.frag_child_index, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;

    }

    private void initView() {
        mTvWeather.setText("天气");


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtil.i("onActivityCreated");
        initData();
    }

    private void initData() {
        testRxJava();


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

    private void testRxJava() {
        String[] names = {"l", "i"};
        Observable.from(names)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        LogUtil.i("name" + s);
                    }
                });
        final int drawableRes = R.mipmap.ic_launcher;
        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Drawable drawable = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    drawable = getActivity().getTheme().getDrawable(drawableRes);
                } else {
                    drawable = ContextCompat.getDrawable(getActivity(), drawableRes);
                }
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Drawable>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.i("error!");
                    }

                    @Override
                    public void onNext(Drawable drawable) {
                        mIvPic.setImageDrawable(drawable);
                    }
                });

        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        LogUtil.i("number=" + integer);
                    }
                });
        Observable.just("")
                .map(new Func1<String, Bitmap>() {
                    @Override
                    public Bitmap call(String s) {
                        return null;
                    }
                })
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {

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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.i("onDestroy");
    }


}
