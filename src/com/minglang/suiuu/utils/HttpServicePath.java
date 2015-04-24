package com.minglang.suiuu.utils;

/**
 * 存放接口URL
 * <p/>
 * Created by Administrator on 2015/4/24.
 */
public class HttpServicePath {

    /**
     * 混淆码
     */
    public static String ConfusedCode = "sui@uu~9527&*";

    /**
     * 服务器地址
     */
    private static String RootPath = "http://192.168.11.202";

    /**
     * 应用自带登陆URL
     * <p/>
     * 请求方式:POST
     * <p/>
     * 请求参数:用户名 username 、密码 password
     */
    public static String SelfLoginPath = RootPath + "app-login/app-login";

    /**
     * 第三方登陆地址
     * <p/>
     * 请求方式:POST
     * <p/>
     * 请求参数:openId（第三方唯一标识符）、nickname（昵称）、sex （男：1、女：2、保密/不详：3）
     * headImg 头像URL、type（1、QQ 2、微信 3、微博）
     * sign 信息混淆（sign = md5 openId+type+混淆码）
     */
    public static String ThirdPartyPath = RootPath + "/app-login/access-login";
}
