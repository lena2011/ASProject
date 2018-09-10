package com.lena.asp.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lena.asp.R;
import com.lena.asp.base.BaseActivity;
import com.lena.asp.utils.LogUtil;
import com.lena.asp.utils.SharedPreferenceUtil;
import com.lena.asp.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imkit.model.UIConversation;
import io.rong.imlib.model.Conversation;

/**
 * @author lilingfei
 * @date 2018/8/7
 */
public class ConversationListActivity extends BaseActivity {
    @BindView(R.id.tv_conversation)
    TextView mTvConversation;
    @BindView(R.id.conversationlist)
    FrameLayout mConversationlist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_conversation_list);

        initData();
        initListener();
    }

    private void initData() {
        String userId = SharedPreferenceUtil.getUserId(this);
        if (!StringUtils.isEmpty(userId)) {
            mTvConversation.setText(userId);
        }
    }

    private void initListener() {
        RongIM.setConversationListBehaviorListener(new RongIM.ConversationListBehaviorListener() {
            @Override
            public boolean onConversationPortraitClick(Context context, Conversation.ConversationType conversationType, String s) {
                return false;
            }

            @Override
            public boolean onConversationPortraitLongClick(Context context, Conversation.ConversationType conversationType, String s) {
                return false;
            }

            @Override
            public boolean onConversationLongClick(Context context, View view, UIConversation uiConversation) {
                return false;
            }

            /**点击会话列表中的item
             * @param context
             * @param view
             * @param uiConversation
             * @return
             */
            @Override
            public boolean onConversationClick(Context context, View view, UIConversation uiConversation) {
//                if (uiConversation!=null){
//                Intent intent=new Intent(context,ConversationActivity.class);
//                intent.putExtra("title",uiConversation.getUIConversationTitle()!=null?uiConversation.getUIConversationTitle():"");
//                context.startActivity(intent);
//                return true;
//                }else {
//                    return false;
//                }
                return false;

            }
        });

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.i("onResume");
        ConversationListFragment fragment = new ConversationListFragment();
        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话非聚合显示
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")//设置群组会话聚合显示
                .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")//设置讨论组会话非聚合显示
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")//设置系统会话非聚合显示
                .build();
        fragment.setUri(uri);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //rong_content 为你要加载的 id
        transaction.add(R.id.conversationlist, fragment);
        transaction.commit();

    }

    @OnClick(R.id.tv_conversation)
    public void onViewClicked() {
        RongIM.getInstance().startPrivateChat(this, "2011", "聊天中");
    }
}
