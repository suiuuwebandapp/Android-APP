package com.minglang.suiuu.activity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.minglang.suiuu.R;
import com.minglang.suiuu.adapter.AttentionPagerAdapter;
import com.minglang.suiuu.fragment.AttentionThemeFragment;
import com.minglang.suiuu.fragment.AttentionUserFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 关注页面
 */
public class AttentionActivity extends FragmentActivity {

    private ImageView AttentionBack;

    private ViewPager attentionPager;

    private TextView attentionThemeTitle, attentionUserTitle;

    private List<Fragment> fragmentList;

    private FragmentManager fm;

    private AttentionThemeFragment attentionThemeFragment;

    private AttentionUserFragment attentionUserFragment;

    private AttentionPagerAdapter attentionPagerAdapter;

    private ImageView attentionSliderView;

    private int currIndex = 1;// 当前页卡编号

    private int sliderViewWidth;//图片宽度

    private int tabWidth;// 每个tab头的宽度

    private int offsetX;//偏移量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention);

        initView();

        ViewAction();

    }

    private void ViewAction() {

        AttentionBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        attentionPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                if (attentionSliderView.getVisibility() == View.INVISIBLE) {
                    attentionSliderView.setVisibility(View.VISIBLE);
                }

                Animation anim = new TranslateAnimation(tabWidth * currIndex + offsetX, tabWidth * i + offsetX, 0, 0);
                currIndex = i;
                anim.setFillAfter(true);
                anim.setDuration(200);
                attentionSliderView.startAnimation(anim);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        attentionThemeTitle.setOnClickListener(new AttentionClick(0));
        attentionUserTitle.setOnClickListener(new AttentionClick(1));

    }

    /**
     * 初始化方法
     */
    private void initView() {

        AttentionBack = (ImageView) findViewById(R.id.AttentionBack);

        attentionPager = (ViewPager) findViewById(R.id.attentionPager);

        attentionSliderView = (ImageView) findViewById(R.id.attention_sliderView);
        attentionSliderView.setVisibility(View.INVISIBLE);

        attentionThemeTitle = (TextView) findViewById(R.id.attention_theme_title);
        attentionUserTitle = (TextView) findViewById(R.id.attention_user_title);

        fragmentList = new ArrayList<>();

        fm = getSupportFragmentManager();

        attentionThemeFragment = AttentionThemeFragment.newInstance("a", "b");
        attentionUserFragment = AttentionUserFragment.newInstance("c", "d");

        fragmentList.add(attentionThemeFragment);
        fragmentList.add(attentionUserFragment);

        attentionPagerAdapter = new AttentionPagerAdapter(fm, fragmentList);

        attentionPager.setAdapter(attentionPagerAdapter);

        initImageView();
    }

    private void initImageView() {
        sliderViewWidth = BitmapFactory.decodeResource(getResources(), R.drawable.slider).getWidth();//获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度

        tabWidth = screenW / 2;
        if (sliderViewWidth > tabWidth) {
            attentionSliderView.getLayoutParams().width = tabWidth;
            sliderViewWidth = tabWidth;
        }

        offsetX = (tabWidth - sliderViewWidth) / 2;
    }

    class AttentionClick implements View.OnClickListener {

        private int index;

        public AttentionClick(int index) {
            this.index = index;
        }

        @Override
        public void onClick(View v) {
            attentionPager.setCurrentItem(index);
        }
    }

}
