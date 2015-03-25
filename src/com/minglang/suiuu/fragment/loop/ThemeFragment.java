package com.minglang.suiuu.fragment.loop;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.minglang.suiuu.R;

/**
 *
 * 主题页面
 *
 * Created by LZY on 2015/3/17 0017.
 */
public class ThemeFragment extends Fragment {

    private ListView themeList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_theme,null);

        initView(rootView);

        return rootView;
    }

    private void initView(View rootView){
        themeList = (ListView) rootView.findViewById(R.id.themeList);
    }

}
