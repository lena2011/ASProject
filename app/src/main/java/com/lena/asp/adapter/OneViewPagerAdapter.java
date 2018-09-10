package com.lena.asp.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Pair;
import android.view.ViewGroup;

import com.lena.asp.utils.LogUtil;

import java.util.List;

/**
 * File description.
 *
 * @author Administrator
 * @date 2018/5/31
 */
public class OneViewPagerAdapter extends FragmentPagerAdapter {
    private List<Pair<String, Fragment>> mList;

    public OneViewPagerAdapter(FragmentManager fm, List<Pair<String, Fragment>> list) {
        super(fm);
        mList = list;
    }


    @Override
    public Fragment getItem(int position) {
        return mList == null ? null : mList.get(position).second;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position).first;
    }
}
