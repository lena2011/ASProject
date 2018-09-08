package com.lena.asp.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

/**
 * File description.
 *
 * @author Administrator
 * @date 2018/5/30
 */
public abstract class BaseFragment extends Fragment {
    private static final String STATE_SAVE_OR_HIDDEN = "STATE_SAVE_OR_HIDDEN";

    public Activity mActivity;

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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = getActivity();
        initData();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SAVE_OR_HIDDEN, isHidden());
    }

    /**
     * 初始化，调接口
     */
    public abstract void initData();
}
