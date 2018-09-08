package com.lena.asp.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lena.asp.R;
import com.lena.asp.activity.MainActivity;
import com.lena.asp.base.BaseFragment;
import com.lena.asp.utils.LogUtil;

import java.lang.ref.WeakReference;


public class TwoFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Activity mMainActivity;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Activity mActivity;
    private HandlerMessage mHandlerMessage;


    public TwoFrag() {
        // Required empty public constructor
    }


    public static TwoFrag newInstance(String param1, String param2) {
        TwoFrag fragment = new TwoFrag();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_two, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = getActivity();
        mHandlerMessage = new HandlerMessage(mActivity);
        initView();
    }

    private void initView() {
        mHandlerMessage.sendEmptyMessage(1);
    }


    private static class HandlerMessage extends Handler {
        private WeakReference<Activity> mActivityWeakReference;

        private HandlerMessage(Activity activity) {
            mActivityWeakReference = new WeakReference<Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Activity activity = mActivityWeakReference.get();
            LogUtil.i("msg" + activity);
            if (activity != null) {
                switch (msg.what) {
                    case 1:
                        LogUtil.i("msg");
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
