package com.minglang.suiuu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.minglang.suiuu.R;

public class OtherUserActivity extends Activity {

    /**
     * 返回键
     */
    private ImageView otherUserBack;

    /**
     * 收藏
     */
    private TextView collection;

    /**
     * 头像
     */
    private ImageView headImage;

    /**
     * 足迹
     */
    private TextView footprint;

    /**
     * 关注
     */
    private TextView attention;

    /**
     * 会话
     */
    private TextView conversation;

    private GridView otherUserLoop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_user);

        initView();

        ViewAction();

    }

    /**
     * 控件动作
     */
    private void ViewAction() {
        otherUserBack.setOnClickListener(new OtherUserClick());

        collection.setOnClickListener(new OtherUserClick());

        headImage.setOnClickListener(new OtherUserClick());

        footprint.setOnClickListener(new OtherUserClick());

        attention.setOnClickListener(new OtherUserClick());

        conversation.setOnClickListener(new OtherUserClick());
    }

    /**
     * 初始化方法
     */
    private void initView() {
        otherUserBack = (ImageView) findViewById(R.id.OtherUserBack);

        collection = (TextView) findViewById(R.id.otherUserCollection);

        headImage = (ImageView) findViewById(R.id.otherUserHeadImage);

        footprint = (TextView) findViewById(R.id.otherUserFootprint);

        attention = (TextView) findViewById(R.id.otherUserAttention);

        conversation = (TextView) findViewById(R.id.otherUserConversation);

        otherUserLoop = (GridView) findViewById(R.id.otherUserLoop);

        otherUserLoop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    class OtherUserClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.OtherUserBack:
                    finish();
                    break;

                case R.id.otherUserCollection:
                    break;

                case R.id.otherUserHeadImage:
                    break;

                case R.id.otherUserFootprint:
                    break;

                case R.id.otherUserAttention:
                    break;

                case R.id.otherUserConversation:
                    break;

            }

        }
    }

}
