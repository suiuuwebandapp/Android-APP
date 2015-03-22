package com.minglang.suiuu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.minglang.suiuu.R;

public class SettingActivity extends Activity {

    private static final String[] SETTINGS = {"个人设置", "通用设置", "检查更新", "反馈", "关于"};

    private ImageView settingBack;

    private ListView settingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initView();

        ViewAction();

    }

    private void ViewAction() {

        settingBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        settingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intent0 = new Intent(SettingActivity.this, PersonSettingActivity.class);
                        startActivity(intent0);
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                }
            }
        });

    }

    /**
     * 初始化方法
     */
    private void initView() {

        settingBack = (ImageView) findViewById(R.id.settingBack);
        settingList = (ListView) findViewById(R.id.settingList);

        settingList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, SETTINGS));

    }

}