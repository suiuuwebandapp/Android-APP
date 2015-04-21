package com.minglang.suiuu.utils;

import com.google.gson.Gson;
import com.minglang.suiuu.entity.LoopInfo;

/**
 * JSON数据解析类
 * <p/>
 * 使用Gson来进行数据解析
 * <p/>
 * Created by Administrator on 2015/4/21.
 */
public class JsonParse {

    private static Gson gson = new Gson();

    public static LoopInfo parseLoopResult(String string) {
        LoopInfo loopInfo;
        loopInfo = gson.fromJson(string, LoopInfo.class);
        return loopInfo;
    }

}
