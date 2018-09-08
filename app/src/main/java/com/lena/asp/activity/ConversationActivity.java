package com.lena.asp.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.lena.asp.R;
import com.lena.asp.base.BaseAppActivity;
import com.lena.asp.utils.LogUtil;
import com.lena.asp.utils.StringUtils;

import java.util.Collection;
import java.util.Iterator;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imlib.MessageTag;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.TypingMessage.TypingStatus;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.message.TextMessage;
import io.rong.message.VoiceMessage;

/**
 * @author lilingfei
 * @date 2018/8/6
 */
public class ConversationActivity extends BaseAppActivity {
    private final int SET_TEXT_TYPING_TITLE = 0;
    private final int SET_VOICE_TYPING_TITLE = 1;
    private final int SET_TARGETID_TITLE = 2;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;



    private String userId;
    private Handler mHandler;
    private  String title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_conversation);
        ButterKnife.bind(this);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        userId=getIntent().getData().getQueryParameter("targetId");
         title = getIntent().getData().getQueryParameter("title");
        if (!StringUtils.isEmpty(title)){
            //todo 设置标题为对方昵称
            mTvTitle.setText(title);
        }
    }

    private void initData() {
        LogUtil.i("ConversationActivity i");
//        userId = SharedPreferenceUtil.getUserId(this);
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(android.os.Message msg) {

                switch (msg.what) {
                    case SET_TEXT_TYPING_TITLE:
                        mTvTitle.setText("对方正在输入。。。");
                        break;
                    case SET_VOICE_TYPING_TITLE:
                        mTvTitle.setText("对方正在讲话。。。");
                        break;
                    case SET_TARGETID_TITLE:
                        // TODO: 2018/8/8 对方的姓名
                        mTvTitle.setText(title);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

    }

    private void initListener() {
        RongIM.getInstance().setSendMessageListener(new RongIM.OnSendMessageListener() {
            /**d
             * @param message
             * @return
             */
            @Override
            public Message onSend(Message message) {
                LogUtil.i("message="+message);
                Message.SentStatus result = message.getSentStatus();
                LogUtil.i("result=" + result);
                if (result != null) {
                    LogUtil.i("re" + Message.SentStatus.SENT);
                }
                return message;
            }

            @Override
            public boolean onSent(Message message, RongIM.SentMessageErrorCode sentMessageErrorCode) {
                LogUtil.i("message" + message + "sentMessageErrorCode=" + sentMessageErrorCode);
                return false;
            }
        });
        RongIMClient.setTypingStatusListener(new RongIMClient.TypingStatusListener() {


            @Override
            public void onTypingStatusChanged(Conversation.ConversationType type, String targetId, Collection<TypingStatus> typingStatusSet) {
                //当输入状态的会话类型和targetID与当前会话一致时，才需要显示
                LogUtil.i("type=" + type+"targetId="+targetId+",userId="+userId);
                if (type.equals(Conversation.ConversationType.PRIVATE) && targetId.equals(userId)) {
                    //count表示当前会话中正在输入的用户数量，目前只支持单聊，所以判断大于0就可以给予显示了
                    int count = typingStatusSet.size();
                    LogUtil.i("count="+count);
                    if (count > 0) {
                        Iterator iterator = typingStatusSet.iterator();
                        TypingStatus status = (TypingStatus) iterator.next();
                        String objectName = status.getTypingContentType();

                        MessageTag textTag = TextMessage.class.getAnnotation(MessageTag.class);
                        MessageTag voiceTag = VoiceMessage.class.getAnnotation(MessageTag.class);
                        //匹配对方正在输入的是文本消息还是语音消息
                        if (objectName.equals(textTag.value())) {
                            //显示“对方正在输入”
                            mHandler.sendEmptyMessage(SET_TEXT_TYPING_TITLE);
                        } else if (objectName.equals(voiceTag.value())) {
                            //显示"对方正在讲话"
                            mHandler.sendEmptyMessage(SET_VOICE_TYPING_TITLE);
                        }
                    } else {
                        //当前会话没有用户正在输入，标题栏仍显示原来标题
                        mHandler.sendEmptyMessage(SET_TARGETID_TITLE);
                    }
                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        ConversationFragment frag = (ConversationFragment) getSupportFragmentManager().findFragmentById(R.id.conversation);
        if (!frag.onBackPressed()) {
            finish();
        }


    }
}
