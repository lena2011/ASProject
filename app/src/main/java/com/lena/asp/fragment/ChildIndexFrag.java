package com.lena.asp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lena.asp.R;

/**
 * File description.
 *
 * @author Administrator
 * @date 2018/5/31
 */
public class ChildIndexFrag extends Fragment {
    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.frag_child_index,container,false);
    }
}
