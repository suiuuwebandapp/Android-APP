package com.minglang.suiuu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.minglang.suiuu.R;
import com.minglang.suiuu.adapter.LoopDetailsAdapter;
import com.minglang.suiuu.entity.LoopDetails;
import com.minglang.suiuu.entity.LoopDetailsData;
import com.minglang.suiuu.utils.HttpServicePath;
import com.minglang.suiuu.utils.JsonUtil;
import com.minglang.suiuu.utils.SuHttpRequest;
import com.minglang.suiuu.utils.SuiuuInformation;

import java.util.List;

/**
 * 圈子-详情页页面
 */
public class LoopDetailsActivity extends Activity {

    private static final String TAG = LoopDetailsActivity.class.getSimpleName();

    /**
     * 圈子ID
     */
    private String loopID;

    /**
     * 验证信息
     */
    private String Verification;

    private GridView loopDetailsGridView;

    private LoopDetailsRequestCallBack loopDetailsRequestCallBack = new LoopDetailsRequestCallBack();

    private LoopDetails loopDetails;

    private List<LoopDetailsData> list;

    private LoopDetailsAdapter loopDetailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loop_details);

        loopID = getIntent().getStringExtra("loopID");
        Verification = SuiuuInformation.ReadVerification(this);

        initView();

        ViewAction();
    }

    /**
     * 网络数据请求
     */
    private void getInternetServiceData() {

        RequestParams params = new RequestParams();
        params.addBodyParameter("app_suiuu_sign", Verification);
        params.addBodyParameter("circleId", loopID);

        SuHttpRequest suHttpRequest = SuHttpRequest.newInstance(HttpRequest.HttpMethod.POST,
                HttpServicePath.LoopDetailsPath, loopDetailsRequestCallBack);
        suHttpRequest.setParams(params);
        suHttpRequest.requestNetworkData();

    }

    /**
     * 控件动作
     */
    private void ViewAction() {
        loopDetailsGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String articleId = list.get(position).getArticleId();

                Intent intent = new Intent(LoopDetailsActivity.this, LoopArticleActivity.class);
                intent.putExtra("articleId", articleId);
                startActivity(intent);

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
            String str = responseInfo.result;
            loopDetails = JsonUtil.getInstance().fromJSON(LoopDetails.class, str);
            if (loopDetails != null) {
                list = loopDetails.getData();
                loopDetailsAdapter = new LoopDetailsAdapter(LoopDetailsActivity.this, loopDetails, list);
                loopDetailsGridView.setAdapter(loopDetailsAdapter);
            }
        }

        @Override
        public void onFailure(HttpException error, String msg) {
            Toast.makeText(LoopDetailsActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    }

}
