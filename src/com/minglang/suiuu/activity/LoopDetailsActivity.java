package com.minglang.suiuu.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
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
import com.minglang.suiuu.entity.LoopDetailsDataList;
import com.minglang.suiuu.utils.HttpServicePath;
import com.minglang.suiuu.utils.JsonUtil;
import com.minglang.suiuu.utils.SuHttpRequest;
import com.minglang.suiuu.utils.SuiuuInformation;
import com.minglang.suiuu.utils.SystemBarTintManager;

import java.util.List;

/**
 * 圈子-详情页页面
 */
public class LoopDetailsActivity extends Activity {

    private static final String TAG = LoopDetailsActivity.class.getSimpleName();

    private SystemBarTintManager systemBarTintManager;

    /**
     * 状态栏高度
     */
    private int statusBarHeight;

    /**
     * 虚拟按键高度
     */
    private int navigationBarHeight;

    /**
     * 圈子ID
     */
    private String loopID;

    /**
     * 验证信息
     */
    private String Verification;

    /**
     * 数据显示控件
     */
    private GridView loopDetailsGridView;

    private LoopDetailsData loopDetailsData;

    private List<LoopDetailsDataList> list;

    /**
     * 网络请求回调接口
     */
    private LoopDetailsRequestCallBack loopDetailsRequestCallBack = new LoopDetailsRequestCallBack();

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loop_details);

        loopID = getIntent().getStringExtra("loopID");
        Verification = SuiuuInformation.ReadVerification(this);

        initView();
        ViewAction();
        getInternetServiceData();
    }

    /**
     * 网络数据请求
     */
    private void getInternetServiceData() {
        if (progressDialog != null) {
            progressDialog.show();
        }

        RequestParams params = new RequestParams();
        //TODO 忽略身份验证KEY
        params.addBodyParameter(HttpServicePath.key, Verification);
        params.addBodyParameter("circleId", loopID);

        SuHttpRequest suHttpRequest = new SuHttpRequest(HttpRequest.HttpMethod.POST,
                HttpServicePath.LoopDetailsPath, loopDetailsRequestCallBack);
        suHttpRequest.setParams(params);
        suHttpRequest.requestNetworkData();

        Log.i(TAG, Verification);
        Log.i(TAG, loopID);
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
        systemBarTintManager = new SystemBarTintManager(this);
        SystemBarTintManager.SystemBarConfig systemBarConfig = systemBarTintManager.getConfig();
        statusBarHeight = systemBarConfig.getStatusBarHeight();
        navigationBarHeight = systemBarConfig.getNavigationBarHeight();

        /****************设置状态栏颜色*************/
        systemBarTintManager.setStatusBarTintEnabled(true);
        systemBarTintManager.setNavigationBarTintEnabled(false);
        systemBarTintManager.setTintColor(getResources().getColor(R.color.tr_black));

        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(true);
        progressDialog.setMessage(getResources().getString(R.string.load_wait));

        loopDetailsGridView = (GridView) findViewById(R.id.loopDetailsGridView);
    }

    class LoopDetailsRequestCallBack extends RequestCallBack<String> {

        @Override
        public void onSuccess(ResponseInfo<String> responseInfo) {
            String str = responseInfo.result;
            Log.i(TAG, str);
            try {
                LoopDetails loopDetails = JsonUtil.getInstance().fromJSON(LoopDetails.class, str);
                loopDetailsData = loopDetails.getData();
                if (loopDetailsData != null) {
                    list = loopDetailsData.getData();
                    if (list != null) {
                        LoopDetailsAdapter loopDetailsAdapter = new LoopDetailsAdapter(LoopDetailsActivity.this, list);
                        loopDetailsGridView.setAdapter(loopDetailsAdapter);
                    } else {
                        Toast.makeText(LoopDetailsActivity.this, "暂无数据！", Toast.LENGTH_SHORT).show();
                    }
                }
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
                Toast.makeText(LoopDetailsActivity.this, "获取数据失败，请稍候再试！", Toast.LENGTH_SHORT).show();
            }

            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

        }

        @Override
        public void onFailure(HttpException error, String msg) {

            Log.i(TAG, msg);

            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

            Toast.makeText(LoopDetailsActivity.this, "网络异常，请稍候再试！", Toast.LENGTH_SHORT).show();
        }
    }

}
