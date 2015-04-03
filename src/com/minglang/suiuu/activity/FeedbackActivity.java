package com.minglang.suiuu.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.minglang.suiuu.R;
import com.minglang.suiuu.utils.SystemBarTintManager;

/**
 * 反馈页面
 */
public class FeedbackActivity extends Activity {

    private static final String TAG = FeedbackActivity.class.getSimpleName();

    private ImageView back;

    private TextView sendText;

    private EditText feedbackText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        initView();

        ViewAction();

    }

    private void ViewAction() {

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });

        sendText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = feedbackText.getText().toString();
                Toast.makeText(FeedbackActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });

        feedbackText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String str = s.toString();

                Log.i(TAG, "Text:" + str);
                Log.i(TAG, "start:" + String.valueOf(start));
                Log.i(TAG, "before:" + String.valueOf(before));
                Log.i(TAG, "count:" + String.valueOf(count));

                if(!TextUtils.isEmpty(s)){
                    sendText.setTextColor(getResources().getColor(R.color.remindColor));
                }else{
                    sendText.setTextColor(getResources().getColor(R.color.titleColor));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    /**
     * 初始化方法
     */
    private void initView() {

        SystemBarTintManager mTintManager = new SystemBarTintManager(this);
        mTintManager.setStatusBarTintEnabled(true);
        mTintManager.setNavigationBarTintEnabled(false);
        mTintManager.setTintColor(getResources().getColor(R.color.tr_black));

        int statusHeight = mTintManager.getConfig().getStatusBarHeight();

        boolean isKITKAT = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        if (isKITKAT) {
            RelativeLayout feedbackRootLayout = (RelativeLayout) findViewById(R.id.feedbackRootLayout);
            feedbackRootLayout.setPadding(0, statusHeight, 0, 0);
        }
        back = (ImageView) findViewById(R.id.feedbackReturn);

        sendText = (TextView) findViewById(R.id.feedbackSend);

        feedbackText = (EditText) findViewById(R.id.feedbackText);
    }

}
