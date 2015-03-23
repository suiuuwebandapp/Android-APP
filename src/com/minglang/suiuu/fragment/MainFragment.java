package com.minglang.suiuu.fragment;

import com.minglang.suiuu.R;
import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


/**
 * 主页面
 */
public class MainFragment extends Fragment {

    private ListView mianPageListView;

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_main, null);

        initView(rootView);

		return rootView;
	}

    /**初始化方法*/
    private void initView(View rootView){
        mianPageListView = (ListView) rootView.findViewById(R.id.mainPageListView);
    }

}
