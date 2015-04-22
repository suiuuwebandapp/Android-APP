package com.minglang.suiuu.utils;

import com.google.gson.Gson;
import com.minglang.suiuu.entity.Loop;
import com.minglang.suiuu.entity.LoopDetails;

/**
 * JSON数据解析类
 * <p/>
 * 使用Gson来进行数据解析
 * <p/>
 * Created by Administrator on 2015/4/21.
 */
public class JsonParse {

    private static Gson gson = new Gson();

    /**
     * 解析圈子Json数据
     *
     * @param string 圈子Json字符串
     * @return
     */
    public static Loop parseLoopResult(String string) {
        Loop loop;
        loop = gson.fromJson(string, Loop.class);
        return loop;
    }

    /**
     * 解析圈子详情页Json数据
     *
     * @param string 圈子详情页Json字符串
     * @return
     */
    public static LoopDetails parseLoopDetailsResult(String string) {
        LoopDetails loopDetails;
        loopDetails = gson.fromJson(string, LoopDetails.class);
        return loopDetails;
    }

}
