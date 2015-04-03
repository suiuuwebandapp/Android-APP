package com.minglang.suiuu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.minglang.suiuu.R;
import com.minglang.suiuu.adapter.SettingAdapter;
import com.minglang.suiuu.utils.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 设置页面
 */
public class SettingActivity extends Activity {

    private static final String[] SETTINGS = {"个人设置", "通用设置", "检查更新", "联系我们", "反馈", "去评分"};

    private List<String> stringList;

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
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });

        settingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {

                    case 0:
                        Intent intent0 = new Intent(SettingActivity.this, PersonSettingActivity.class);
                        startActivity(intent0);
                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        break;

                    case 1:
                        break;

                    case 2:
                        break;

                    case 3:
                        Intent intent3 = new Intent(SettingActivity.this, ContactUsActivity.class);
                        startActivity(intent3);
                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        break;

                    case 4:
                        Intent intent4 = new Intent(SettingActivity.this, FeedbackActivity.class);
                        startActivity(intent4);
                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        break;

                    case 5:
                        break;
                }
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

        int statusBarHeight = mTintManager.getConfig().getStatusBarHeight();

        boolean isKITKAT = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        if (isKITKAT) {
            RelativeLayout settingLayout = (RelativeLayout) findViewById(R.id.settingRootLayout);
            settingLayout.setPadding(0, statusBarHeight, 0, 0);
        }

        settingBack = (ImageView) findViewById(R.id.settingBack);
        settingList = (ListView) findViewById(R.id.settingList);

        stringList = new ArrayList<>();

        for (String s : SETTINGS) {
            stringList.add(s);
        }

        SettingAdapter adapter = new SettingAdapter(this, stringList);

        settingList.setAdapter(adapter);

    }

}