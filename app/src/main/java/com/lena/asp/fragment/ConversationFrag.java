package com.lena.asp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imlib.model.CSCustomServiceInfo;

/**
 * @author lilingfei
 * @date 2018/8/7
 */
public class ConversationFrag extends ConversationFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        intData();
    }

    private void intData() {
        //首先需要构造使用客服者的用户信息
        CSCustomServiceInfo.Builder csBuilder = new CSCustomServiceInfo.Builder();
        CSCustomServiceInfo csInfo = csBuilder.nickName("融云").build();

/**
 * 启动客户服聊天界面。
 *
 * @param context           应用上下文。
 * @param customerServiceId 要与之聊天的客服 Id。
 * @param title             聊天的标题，如果传入空值，则默认显示与之聊天的客服名称。
 * @param customServiceInfo 当前使用客服者的用户信息。{@link io.rong.imlib.model.CSCustomServiceInfo}
 */
        RongIM.getInstance().startCustomerServiceChat(getActivity(), "KEFU153354037244226", "在线客服", csInfo);
    }



}
