package com.minglang.suiuu.activity;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.minglang.suiuu.R;

/**
 * 新提醒页面
 */

public class NewRemindActivity extends Activity {

    private ImageView newRemindBack;

    private TextView newAt, newComment, newReply, newAttention;

    private ImageView newRemindSlider;

    private ViewPager newRemindPager;

    private int currIndex = 1;// 当前页卡编号

    private int sliderViewWidth;//图片宽度

    private int tabWidth;// 每个tab头的宽度

    private int offsetX;//偏移量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_remind);

        initView();

        ViewAction();

    }

    private void ViewAction() {
        newRemindBack.setOnClickListener(new NewRemindClick());

        newAt.setOnClickListener(new NewRemindClick(0));
        newComment.setOnClickListener(new NewRemindClick(1));
        newReply.setOnClickListener(new NewRemindClick(2));
        newAttention.setOnClickListener(new NewRemindClick(3));

        newRemindPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                if(newRemindSlider.getVisibility()== View.INVISIBLE){
                    newRemindSlider.setVisibility(View.VISIBLE);
                }

                Animation anim = new TranslateAnimation(tabWidth * currIndex + offsetX, tabWidth * i + offsetX, 0, 0);
                currIndex = i;
                anim.setFillAfter(true);
                anim.setDuration(200);
                newRemindSlider.startAnimation(anim);

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initView() {
        newRemindBack = (ImageView) findViewById(R.id.newRemindBack);

        newAt = (TextView) findViewById(R.id.newAt);
        newComment = (TextView) findViewById(R.id.newComment);
        newReply = (TextView) findViewById(R.id.newReply);
        newAttention = (TextView) findViewById(R.id.newAttention);

        newRemindSlider = (ImageView) findViewById(R.id.newRemindSlider);
        newRemindSlider.setVisibility(View.INVISIBLE);

        newRemindPager = (ViewPager) findViewById(R.id.newRemindPager);

        initImageView();
    }

    private void initImageView() {
        sliderViewWidth = BitmapFactory.decodeResource(getResources(), R.drawable.slider).getWidth();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        tabWidth = screenW / 2;
        if (sliderViewWidth > tabWidth) {
            newRemindSlider.getLayoutParams().width = tabWidth;
            sliderViewWidth = tabWidth;
        }

        offsetX = (tabWidth - sliderViewWidth) / 2;
    }

    class NewRemindClick implements View.OnClickListener {

        private int index;

        public NewRemindClick() {

        }

        public NewRemindClick(int i) {
            this.index = i;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.newRemindBack:
                    finish();
                    break;
                case R.id.newAt:
                    newRemindPager.setCurrentItem(index);
                    break;
                case R.id.newComment:
                    newRemindPager.setCurrentItem(index);
                    break;
                case R.id.newReply:
                    newRemindPager.setCurrentItem(index);
                    break;
                case R.id.newAttention:
                    newRemindPager.setCurrentItem(index);
                    break;
            }
        }
    }

}
