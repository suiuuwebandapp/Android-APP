package com.minglang.suiuu.fragment.loop;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.minglang.suiuu.R;
import com.minglang.suiuu.adapter.ThemeAdapter;
import com.minglang.suiuu.entity.Loop;
import com.minglang.suiuu.utils.JsonUtil;
import com.minglang.suiuu.utils.LoopData;

import java.util.List;

/**
 * 主题页面
 * <p/>
 * A simple {@link Fragment} subclass.
 * Use the {@link ThemeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThemeFragment extends Fragment {

    private static final String TAG = ThemeFragment.class.getSimpleName();

    private GridView themeGridView;

    private ThemeAdapter themeAdapter;

    private List<LoopData> list;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AreaFragment.
     */
    public static ThemeFragment newInstance(String param1, String param2) {
        ThemeFragment fragment = new ThemeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ThemeFragment(){
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_theme, null);

        initView(rootView);

        //getInternetServiceData();

        return rootView;
    }

    private void ViewAction() {
        themeGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    /**
     * 从网络获取数据
     */
    private void getInternetServiceData() {
        HttpUtils http = new HttpUtils();
        RequestParams params = new RequestParams();
        http.send(HttpRequest.HttpMethod.POST, "", params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> objectResponseInfo) {
                String str = objectResponseInfo.result;
               Loop loopInfo =  JsonUtil.getInstance().fromJSON(Loop.class,str);
                if (Integer.parseInt(loopInfo.getStatus()) == 1) {
                    list = loopInfo.getData();
                    if (list != null && list.size() > 0) {
                        themeAdapter = new ThemeAdapter(getActivity(), loopInfo, list);
                        themeGridView.setAdapter(themeAdapter);
                    }
                } else {
                    Toast.makeText(getActivity(), "数据获取失败，请重试！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Log.i(TAG, "Message:" + e.getMessage());
                Log.i(TAG, "Information:" + s);
            }
        });
    }

    /**
     * 初始化方法
     *
     * @param rootView Fragment根view
     */
    private void initView(View rootView) {
        themeGridView = (GridView) rootView.findViewById(R.id.themeGridView);
    }

}
