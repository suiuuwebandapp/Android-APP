package com.minglang.suiuu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.minglang.suiuu.R;

public class OtherUserActivity extends Activity {


    private ImageView otherUserBack;

    private TextView otherUserName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_user);

        initView();

        ViewAction();

    }

    private void ViewAction() {
        otherUserBack.setOnClickListener(new OtherUserClick());
        otherUserName.setText("用户名");
    }

    private void initView() {
        otherUserBack = (ImageView) findViewById(R.id.OtherUserBack);
        otherUserName = (TextView) findViewById(R.id.OtherUserName);
    }

    class OtherUserClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.OtherUserBack:
                    finish();
                    break;
            }
        }
    }

}
