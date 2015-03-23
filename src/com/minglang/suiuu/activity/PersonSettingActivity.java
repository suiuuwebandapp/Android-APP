package com.minglang.suiuu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.minglang.suiuu.R;

public class PersonSettingActivity extends Activity {

    private ImageView personalSettingBack;

    private LinearLayout personalSettingHeadLayout;

    private ImageView personalSettingHeadImage;

    private EditText editNickName;

    private EditText editLocation;

    private EditText editTrade;

    private EditText editSign;

    private String str_NickName;

    private String str_Location;

    private String str_Trade;

    private String str_Sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_setting);

        initView();

        ViewAction();

    }

    private void ViewAction() {

        personalSettingBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        personalSettingHeadLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void initView() {
        personalSettingBack = (ImageView) findViewById(R.id.personalSettingBack);

        personalSettingHeadLayout = (LinearLayout) findViewById(R.id.personalSettingHeadLayout);

        personalSettingHeadImage = (ImageView) findViewById(R.id.personalSettingHeadImage);

        editNickName = (EditText) findViewById(R.id.editNickName);
        editLocation = (EditText) findViewById(R.id.editLocation);
        editTrade = (EditText) findViewById(R.id.editTrade);
        editSign = (EditText) findViewById(R.id.editSign);
    }

}
