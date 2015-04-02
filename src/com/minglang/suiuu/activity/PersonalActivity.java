package com.minglang.suiuu.activity;

import android.app.Activity;
import android.os.Bundle;

import com.minglang.suiuu.R;
import com.minglang.suiuu.utils.SystemBarTintManager;

/**
 * 个人信息
 */
public class PersonalActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        initView();
    }

    private void initView(){
        SystemBarTintManager mTintManager = new SystemBarTintManager(this);
        mTintManager.setStatusBarTintEnabled(true);
        mTintManager.setNavigationBarTintEnabled(false);
        mTintManager.setTintColor(getResources().getColor(R.color.tr_black));
    }

}
