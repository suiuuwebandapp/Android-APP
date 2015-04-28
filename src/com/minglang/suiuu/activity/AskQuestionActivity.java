package com.minglang.suiuu.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.sdk.android.oss.OSSService;
import com.alibaba.sdk.android.oss.OSSServiceProvider;
import com.alibaba.sdk.android.oss.callback.SaveCallback;
import com.alibaba.sdk.android.oss.model.OSSException;
import com.alibaba.sdk.android.oss.storage.OSSBucket;
import com.alibaba.sdk.android.oss.storage.OSSFile;
import com.minglang.suiuu.R;
import com.minglang.suiuu.adapter.ShowGVPictureAdapter;
import com.minglang.suiuu.chat.activity.BaiduMapActivity;
import com.minglang.suiuu.chat.activity.ShowBigImage;

import java.io.FileNotFoundException;
import java.util.ArrayList;


/**
 * Created by Administrator on 2015/4/23.
 * 随问和随记的页面
 */

public class AskQuestionActivity extends Activity {
    private GridView gv_show_picture;
    private ArrayList<String> listPicture;
    //返回按钮
    private ImageView iv_top_back;
    private EditText et_search_question;
    private EditText et_question_description;
    private TextView tv_show_your_location;
    private TextView tv_theme_choice;
    private TextView tv_area_choice;
    private static final int REQUEST_CODE_MAP = 8;
    private TextView tv_top_right;
    private static OSSService ossService = OSSServiceProvider.getService();
    private static OSSBucket bucket = ossService.getOssBucket("suiuu");
    private Dialog dialog;

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
                dialog.show();
            }
        });
    }

    private void updateDate(String path) {
        String type = path.substring(path.lastIndexOf("/"));
        String name = type.substring(type.lastIndexOf(".") + 1);
        OSSFile bigfFile = ossService.getOssFile(bucket, "suiuu_content" + type);
        try {
            bigfFile.setUploadFilePath(path, name);
            bigfFile.ResumableUploadInBackground(new SaveCallback() {

                @Override
                public void onSuccess(String objectKey) {
                    Log.i("suiuu", "[onSuccess] - " + objectKey + " upload success!");
                }

                @Override
                public void onProgress(String objectKey, int byteCount, int totalSize) {
                    Log.i("suiuu", "[onProgress] - current upload " + objectKey + " bytes: " + byteCount + " in total: " + totalSize);
                }

                @Override
                public void onFailure(String objectKey, OSSException ossException) {
                    Log.i("suiuu", "[onFailure] - upload " + objectKey + " failed!\n" + ossException.toString());
                    ossException.printStackTrace();
//                    ossException.getException().printStackTrace();
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void initView() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progress_bar);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        listPicture = new ArrayList<>();
        tv_top_right = (TextView) findViewById(R.id.tv_top_right);
        gv_show_picture = (GridView) findViewById(R.id.gv_show_picture);
        iv_top_back = (ImageView) findViewById(R.id.iv_top_back);
        et_search_question = (EditText) findViewById(R.id.search_question);
        et_question_description = (EditText) findViewById(R.id.et_question_description);
        tv_show_your_location = (TextView) findViewById(R.id.tv_show_your_location);
        tv_theme_choice = (TextView) findViewById(R.id.tv_theme_choice);
        tv_area_choice = (TextView) findViewById(R.id.tv_area_choice);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == 9) {
            listPicture = data.getStringArrayListExtra("pictureMessage");
            gv_show_picture.setAdapter(new ShowGVPictureAdapter(this, listPicture));
        } else if (data != null && requestCode == REQUEST_CODE_MAP) {
            double latitude = data.getDoubleExtra("latitude", 0);
            double longitude = data.getDoubleExtra("longitude", 0);
            String locationAddress = data.getStringExtra("address");
            Log.i("suiuu", locationAddress + "logcation");
            if (locationAddress != null && !locationAddress.equals("")) {
                tv_show_your_location.setText(locationAddress);
            } else {
                String st = getResources().getString(R.string.unable_to_get_location);
                Toast.makeText(this, st, Toast.LENGTH_SHORT).show();
            }
        }

    }
}
