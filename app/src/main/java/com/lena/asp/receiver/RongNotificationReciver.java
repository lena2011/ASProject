package com.lena.asp.receiver;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import io.rong.push.notification.PushMessageReceiver;
import io.rong.push.notification.PushNotificationMessage;

/**
 * @author lilingfei
 * @date 2018/8/8
 */
public class RongNotificationReciver extends PushMessageReceiver {
    @Override
    public boolean onNotificationMessageArrived(Context context, PushNotificationMessage pushNotificationMessage) {
        // TODO: 2018/8/8 如果需要自定义通知栏，return true 
        return false;
    }

    @Override
    public boolean onNotificationMessageClicked(Context context, PushNotificationMessage pushNotificationMessage) {
        // TODO: 2018/8/8 如果自定义了通知栏则不会出发
//        Intent intent = new Intent();
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        Uri.Builder builder = Uri.parse("rong://" + context.getPackageName()).buildUpon();
//
//        builder.appendPath("conversation").appendPath("")
//                .appendQueryParameter("targetId", pushNotificationMessage.getTargetId())
//                .appendQueryParameter("title", pushNotificationMessage.getTargetUserName());
//        Uri uri = builder.build();
//        intent.setData(uri);
//        context.startActivity(intent);
        return false;
    }
}
