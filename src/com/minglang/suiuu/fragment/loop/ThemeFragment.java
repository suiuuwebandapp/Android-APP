package com.minglang.suiuu.fragment.loop;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.minglang.suiuu.R;

/**
 * 主题页面
 * <p/>
 * Created by LZY on 2015/3/17 0017.
 */
public class ThemeFragment extends Fragment {

    private ListView themeList;
    private ImageView iv_pic;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_theme, null);

        initView(rootView);

        return rootView;
    }

    /**
     * 初始化方法
     *
     * @param rootView Fragment的根view
     */
    private void initView(View rootView) {
        themeList = (ListView) rootView.findViewById(R.id.themeList);
        iv_pic = (ImageView)rootView.findViewById(R.id.iv_pc);
    }

}
