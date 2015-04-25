package com.minglang.suiuu.entity;

import java.io.Serializable;

/**
 * 第三方登陆信息返回数据实体类
 * <p/>
 * Created by Administrator on 2015/4/25.
 */
public class UserBack implements Serializable {

    public String status;

    public UserBackData data;

    public String message;

    public String token;

}
