package com.minglang.suiuu.fragment.loop;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.minglang.suiuu.R;
import com.minglang.suiuu.adapter.ThemeAdapter;
import com.minglang.suiuu.entity.ThemeInfo;

import java.util.List;

/**
 * 主题页面
 * <p/>
 * Created by LZY on 2015/3/17 0017.
 */
public class ThemeFragment extends Fragment {

    private GridView themeGridView;

    private ThemeAdapter themeAdapter;

    private List<ThemeInfo> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_theme, null);

        initView(rootView);

        getInternetServiceData();

        return rootView;
    }

    /**
     * 从网络获取数据
     */
    private void getInternetServiceData() {
        HttpUtils http = new HttpUtils();
        RequestParams params = new RequestParams();

        http.send(HttpRequest.HttpMethod.POST, "", params, new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> objectResponseInfo) {

            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }

    /**
     * 初始化方法
     *
     * @param rootView Fragment的根view
     */
    private void initView(View rootView) {
        themeGridView = (GridView) rootView.findViewById(R.id.themeGridView);
    }

}
