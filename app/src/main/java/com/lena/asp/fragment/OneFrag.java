package com.lena.asp.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lena.asp.R;
import com.lena.asp.adapter.OneViewPagerAdapter;
import com.lena.asp.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class OneFrag extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Unbinder unbinder;
    @BindView(R.id.tl_nav)
    TabLayout tlNav;
    @BindView(R.id.vp_container)
    ViewPager vpContainer;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public String[] titles = new String[]{"首页", "标题"};

    private OnFragmentInteractionListener mListener;

    private OneViewPagerAdapter mPagerAdapter;

    public OneFrag() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static OneFrag newInstance(String param1, String param2) {
        OneFrag fragment = new OneFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_one, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        LogUtil.i("initView");
        List<Fragment> frags = new ArrayList<>();
        frags.add(new ChildIndexFrag());
        frags.add(new ChildChannelFrag());

        mPagerAdapter = new OneViewPagerAdapter(getChildFragmentManager(), frags);
        vpContainer.setAdapter(mPagerAdapter);
        tlNav.setupWithViewPager(vpContainer);

        tlNav.getTabAt(0).setText("首页");
        tlNav.getTabAt(1).setText("频道");
        tlNav.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
