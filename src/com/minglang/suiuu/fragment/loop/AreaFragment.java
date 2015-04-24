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

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.minglang.suiuu.R;
import com.minglang.suiuu.adapter.AreaAdapter;
import com.minglang.suiuu.entity.Loop;
import com.minglang.suiuu.utils.JsonUtil;
import com.minglang.suiuu.entity.LoopData;
import com.minglang.suiuu.utils.SuHttpRequest;

import java.util.List;

/**
 * 地区页面
 * <p/>
 * A simple {@link Fragment} subclass.
 * Use the {@link AreaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AreaFragment extends Fragment {

    private static final String TAG = AreaFragment.class.getSimpleName();

    private GridView areaGridView;

    private AreaAdapter areaAdapter;

    private List<LoopData> list;

    private AreaRequestCallback areaRequestCallback = new AreaRequestCallback();

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
    public static AreaFragment newInstance(String param1, String param2) {
        AreaFragment fragment = new AreaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public AreaFragment() {
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

        View rootView = inflater.inflate(R.layout.fragment_area, null);

        initView(rootView);

        //getInternetServiceData();

        return rootView;
    }

    private void ViewAtion() {
        areaGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    /**
     * 从网络获取数据
     */
    private void getInternetServiceData() {
        RequestParams params = new RequestParams();
        SuHttpRequest suHttpRequest = SuHttpRequest.newInstance(HttpRequest.HttpMethod.POST, "", areaRequestCallback);
        suHttpRequest.setParams(params);
    }

    /**
     * 初始化方法
     *
     * @param rootView Fragment根View
     */
    private void initView(View rootView) {
        areaGridView = (GridView) rootView.findViewById(R.id.areaGridView);
    }

    class AreaRequestCallback extends RequestCallBack<String> {

        @Override
        public void onSuccess(ResponseInfo<String> responseInfo) {
            String str = responseInfo.result;
            Loop loop = JsonUtil.getInstance().fromJSON(Loop.class, str);
            if (loop != null) {
                if (Integer.parseInt(loop.getStatus()) == 1) {
                    list = loop.getData();
                    areaAdapter = new AreaAdapter(getActivity(), loop, list);
                    areaGridView.setAdapter(areaAdapter);
                } else {
                    Toast.makeText(getActivity(), "数据获取失败，请重试！", Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onFailure(HttpException error, String msg) {

            Log.i(TAG, error.getMessage());
            Log.i(TAG, msg);

            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        }
    }

}
