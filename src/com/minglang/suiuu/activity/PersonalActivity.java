package com.minglang.suiuu.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.minglang.suiuu.R;
import com.minglang.suiuu.utils.SystemBarTintManager;

/**
 * 个人信息
 */
public class PersonalActivity extends Activity {

    private TextView saveView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        initView();
    }

    private void initView() {

        SystemBarTintManager mTintManager = new SystemBarTintManager(this);
        mTintManager.setStatusBarTintEnabled(true);
        mTintManager.setNavigationBarTintEnabled(false);
        mTintManager.setTintColor(getResources().getColor(R.color.tr_black));

        int statusHeight = mTintManager.getConfig().getStatusBarHeight();

        boolean isKITKAT = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        if (isKITKAT) {
            RelativeLayout personalRootLayout = (RelativeLayout) findViewById(R.id.personalRootLayout);
            personalRootLayout.setPadding(0, statusHeight, 0, 0);
        }

        saveView = (TextView) findViewById(R.id.personalSave);

    }

}
