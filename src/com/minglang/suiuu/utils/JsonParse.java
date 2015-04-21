package com.minglang.suiuu.utils;

import com.google.gson.Gson;
import com.minglang.suiuu.entity.ThemeInfo;

/**
 * JSON数据解析类
 * <p/>
 * 使用Gson来进行数据解析
 * <p/>
 * Created by Administrator on 2015/4/21.
 */
public class JsonParse {

    private static Gson gson = new Gson();

    public static ThemeInfo parseThemeData(String string) {
        ThemeInfo themeInfo;
        themeInfo = gson.fromJson(string, ThemeInfo.class);
        return themeInfo;
    }

}
