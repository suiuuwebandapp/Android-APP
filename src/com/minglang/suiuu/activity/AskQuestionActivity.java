package com.minglang.suiuu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.sdk.android.oss.OSSService;
import com.alibaba.sdk.android.oss.OSSServiceProvider;
import com.alibaba.sdk.android.oss.storage.OSSBucket;
import com.minglang.suiuu.R;
import com.minglang.suiuu.adapter.ShowGVPictureAdapter;
import com.minglang.suiuu.chat.activity.BaiduMapActivity;
import com.minglang.suiuu.chat.activity.ShowBigImage;

import java.util.ArrayList;


/**
 * Created by Administrator on 2015/4/23.
 * 随问和随记的页面
 */

public class AskQuestionActivity extends Activity {
    private GridView gv_show_picture;
    private ArrayList<String> listPicture;
    private ImageView iv_top_back;
    private EditText et_search_question;
    private EditText et_question_description;
    private TextView tv_show_your_location;
    private static final int REQUEST_CODE_MAP = 8;
    private TextView tv_top_right;
    private static OSSService ossService = OSSServiceProvider.getService();
    private static OSSBucket bucket = ossService.getOssBucket("suiuu");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_question);
        int record = getIntent().getIntExtra("record", 0);
        initView();
        if (record == 1) {
            et_search_question.setHint(R.string.image_theme);
            et_question_description.setHint(R.string.activity_description);
        }
        gv_show_picture.setAdapter(new ShowGVPictureAdapter(this, listPicture));
        gv_show_picture.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == listPicture.size()) {
                    Intent intent = new Intent(AskQuestionActivity.this, SelectPictureActivity.class);
                    startActivityForResult(intent, 1);
                } else {
                    Intent showPicture = new Intent(AskQuestionActivity.this, ShowBigImage.class);
                    showPicture.putExtra("path", listPicture.get(position));
                    startActivity(showPicture);
                }
            }
        });
        iv_top_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_show_your_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(AskQuestionActivity.this, BaiduMapActivity.class), REQUEST_CODE_MAP);

            }
        });
        tv_top_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void initView() {
        listPicture = new ArrayList<>();
        tv_top_right = (TextView) findViewById(R.id.tv_top_right);
        gv_show_picture = (GridView) findViewById(R.id.gv_show_picture);
        iv_top_back = (ImageView) findViewById(R.id.iv_top_back);
        et_search_question = (EditText) findViewById(R.id.search_question);
        et_question_description = (EditText) findViewById(R.id.et_question_description);
        tv_show_your_location = (TextView) findViewById(R.id.tv_show_your_location);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == 9) {
            listPicture = data.getStringArrayListExtra("pictureMessage");
            gv_show_picture.setAdapter(new ShowGVPictureAdapter(this, listPicture));
        }else if(data != null && requestCode == REQUEST_CODE_MAP) {
            double latitude = data.getDoubleExtra("latitude", 0);
            double longitude = data.getDoubleExtra("longitude", 0);
            String locationAddress = data.getStringExtra("address");
            Log.i("suiuu",locationAddress+"logcation");
            if (locationAddress != null && !locationAddress.equals("")) {
                tv_show_your_location.setText(locationAddress);
            } else {
                String st = getResources().getString(R.string.unable_to_get_location);
                Toast.makeText(this, st, Toast.LENGTH_SHORT).show();
            }
        }

    }
}
