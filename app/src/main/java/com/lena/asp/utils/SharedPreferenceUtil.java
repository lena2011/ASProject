package com.lena.asp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.lang.UScript;
import android.view.ContextMenu;

import com.lena.asp.Entity.Person;

/**
 * @author lilingfei
 * @date 2018/8/7
 */
public class SharedPreferenceUtil {
    private final static String USER_ID = "USERID";

    public static void saveUserId(Context mContext, String userId) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(USER_ID, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userId", userId);
        editor.commit();
    }

    public static String getUserId(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_ID, Context.MODE_PRIVATE);
        return sharedPreferences.getString("userId", "");
    }
}
