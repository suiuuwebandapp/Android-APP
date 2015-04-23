package com.minglang.suiuu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;


import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.minglang.suiuu.R;
import com.minglang.suiuu.adapter.LoopDetailsAdapter;
import com.minglang.suiuu.entity.LoopDetails;
import com.minglang.suiuu.entity.LoopDetailsData;
import com.minglang.suiuu.utils.SuHttpRequest;


import java.util.List;

/**
 * 圈子-详情页页面
 */
public class LoopDetailsActivity extends Activity {

    private GridView loopDetailsGridView;

    private LoopDetailsRequestCallBack loopDetailsRequestCallBack = new LoopDetailsRequestCallBack();

    private LoopDetails loopDetails;

    private List<LoopDetailsData> list;

    private LoopDetailsAdapter loopDetailsAdapter;

    private SuHttpRequest suHttpRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loop_details);

        initView();

        ViewAction();
    }

    private void getInternetServiceData() {

        RequestParams params = new RequestParams();
        
        suHttpRequest = SuHttpRequest.newInstance(HttpRequest.HttpMethod.POST, "", loopDetailsRequestCallBack);
        suHttpRequest.setParams(params);

    }

    /**
     * 控件动作
     */
    private void ViewAction() {
        loopDetailsGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }

    /**
     * 初始化方法
     */
    private void initView() {
        loopDetailsGridView = (GridView) findViewById(R.id.loopDetailsGridView);
    }

    class LoopDetailsRequestCallBack extends RequestCallBack<String> {

        @Override
        public void onSuccess(ResponseInfo<String> responseInfo) {

        }

        @Override
        public void onFailure(HttpException error, String msg) {

        }
    }

}
