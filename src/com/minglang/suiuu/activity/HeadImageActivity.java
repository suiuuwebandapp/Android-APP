package com.minglang.suiuu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.minglang.suiuu.R;

public class HeadImageActivity extends Activity {

    private ImageView HDHeadImage;

    private ImageView HDHeadImageback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_image);

        initView();

        ViewAction();

    }

    private void ViewAction() {
        HDHeadImageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        HDHeadImage = (ImageView) findViewById(R.id.HDHeadImage);
        HDHeadImageback = (ImageView) findViewById(R.id.HDHeadImageBack);
    }

}
