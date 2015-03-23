package com.minglang.suiuu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.minglang.suiuu.R;

/**
 * 粉丝页面
 */
public class FansActivity extends Activity {

    private ImageView fansBack;

    private ListView fansList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fans);

        initView();

        ViewAction();

    }

    private void ViewAction() {
        fansBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 初始化
     */
    private void initView() {
        fansBack = (ImageView) findViewById(R.id.fansBack);
        fansList = (ListView) findViewById(R.id.fansList);
    }

}
