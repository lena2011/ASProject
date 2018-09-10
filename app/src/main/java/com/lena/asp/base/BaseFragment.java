package com.lena.asp.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * File description.
 *
 * @author Administrator
 * @date 2018/5/30
 */
public abstract class BaseFragment extends Fragment {
    private static final String STATE_SAVE_OR_HIDDEN = "STATE_SAVE_OR_HIDDEN";

    public Activity mActivity;
    /**
     * 第一次加载
     */
    private boolean isFirstLoad = false;
    /**
     * 状态是否可见
     */
    private boolean isVisible;
    /**
     * 标志位，view是否加载好
     */
    private boolean isPrepared;


    /**
     * fragmentTransaction show hide,调用此方法
     *
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            unVisible();
        }
    }

    private void onVisible() {
        lazyLoad();
    }

    private void unVisible() {

    }

    private void lazyLoad() {
        if (!isPrepared && !isVisible && !isFirstLoad) {
            return;
        }
        isFirstLoad = false;
        initData();
    }

    /**
     * 初始化，调接口
     */
    public abstract void initData();

    /**
     * 与viewpager一起，调用此方法
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            unVisible();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            boolean isHidden = savedInstanceState.getBoolean(STATE_SAVE_OR_HIDDEN);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            if (isHidden) {
                transaction.hide(this);
            } else {
                transaction.show(this);
            }
            transaction.commit();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = initView(inflater, container, savedInstanceState);
        isFirstLoad = true;
        isPrepared = true;
        lazyLoad();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = getActivity();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SAVE_OR_HIDDEN, isHidden());
    }

    /**
     * 生成view
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    public abstract View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
}
