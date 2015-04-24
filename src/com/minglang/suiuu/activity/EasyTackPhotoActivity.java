package com.minglang.suiuu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.minglang.suiuu.R;
import com.minglang.suiuu.adapter.EasyTackPhotoAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/4/24.
 */
public class EasyTackPhotoActivity extends Activity {
    private ImageView iv_cancel;
    private TextView tv_cancel;
    private ArrayList<String> picList = new ArrayList<>();
    private ListView lv_picture_description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_tackphoto);
        picList = this.getIntent().getStringArrayListExtra("pictureMessage");
        initView();
        iv_cancel.setVisibility(View.GONE);
        tv_cancel.setVisibility(View.VISIBLE);
        lv_picture_description.setAdapter(new EasyTackPhotoAdapter(this,picList));
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        iv_cancel = (ImageView) findViewById(R.id.iv_top_back);
        tv_cancel = (TextView) findViewById(R.id.tv_top_cancel);
        lv_picture_description = (ListView) findViewById(R.id.lv_picture_description);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("suiuu","laillllllllllllllllllllllllllllllllllll");
        if (data != null && resultCode == 9) {
            picList = data.getStringArrayListExtra("pictureMessage");
            lv_picture_description.setAdapter(new EasyTackPhotoAdapter(this,picList));
        }
    }
}
