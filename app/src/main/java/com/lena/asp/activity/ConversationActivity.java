package com.lena.asp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.lena.asp.R;
import com.lena.asp.base.BaseActivity;
import com.lena.asp.fragment.ConversationFrag;
import com.lena.asp.utils.LogUtil;

import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imlib.model.CSCustomServiceInfo;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;

/**
 * @author lilingfei
 * @date 2018/8/6
 */
public class ConversationActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_conversation);
        initData();
        initListener();
    }

    private void initListener() {
        RongIM.getInstance().setSendMessageListener(new RongIM.OnSendMessageListener() {
            /**d
             * @param message
             * @return
             */
            @Override
            public Message onSend(Message message) {
                Message.SentStatus result = message.getSentStatus();
                LogUtil.i("result=" + result);
                if (result != null) {
                    LogUtil.i("re" + Message.SentStatus.SENT);
                }
                return message;
            }

            @Override
            public boolean onSent(Message message, RongIM.SentMessageErrorCode sentMessageErrorCode) {
                LogUtil.i("message"+message+"sentMessageErrorCode="+sentMessageErrorCode);
                return false;
            }
        });
    }

    private void initData() {
        LogUtil.i("ConversationActivity i");


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        RongIM.getInstance().disconnect();
    }

    @Override
    public void onBackPressed() {
        ConversationFragment frag = (ConversationFragment) getSupportFragmentManager().findFragmentById(R.id.conversation);
        if (!frag.onBackPressed()) {
            finish();
        }


    }
}
