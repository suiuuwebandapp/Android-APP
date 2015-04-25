package com.minglang.suiuu.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.minglang.suiuu.entity.RequestData;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/4/25.
 */
public class SuiuuInformation implements Serializable {

    /**
     * 文件名
     */
    private static final String PREFERENCE_NAME = "Suiuu";

    /**
     * 第三方唯一ID
     */
    private static final String THIRD_PARTY_ID = "ThirdPartyID";

    /**
     * 昵称
     */
    private static final String NICKNAME = "NickName";

    /**
     * 性别
     */
    private static final String GENDER = "Gender";

    /**
     * 头像URL
     */
    private static final String HEAD_IMG = "HeadImage";

    private static final String TYPE = "Type";

    public static void WriteInformation(Context context, RequestData requestData) {

        if (context == null) {
            return;
        }

        if (requestData == null) {
            return;
        }

        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_APPEND);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(THIRD_PARTY_ID, requestData.getID());
        editor.putString(NICKNAME, requestData.getNickName());
        editor.putString(GENDER, requestData.getGender());
        editor.putString(HEAD_IMG, requestData.getImagePath());
        editor.putString(TYPE, requestData.getType());
        editor.commit();
    }

}
