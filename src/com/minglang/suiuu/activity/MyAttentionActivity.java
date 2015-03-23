package com.minglang.suiuu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.minglang.suiuu.R;

/**
 * 我的关注
 */
public class MyAttentionActivity extends Activity {

    private ImageView MyAttentionBack;

    private ListView MyAttentionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_attention);

        initView();

        ViewAction();

    }

    private void ViewAction() {
        MyAttentionBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        MyAttentionBack = (ImageView) findViewById(R.id.MyAttentionBack);
        MyAttentionList = (ListView) findViewById(R.id.MyAttentionList);
    }

}
