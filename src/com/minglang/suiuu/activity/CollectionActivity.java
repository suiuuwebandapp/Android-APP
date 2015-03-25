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
import com.minglang.suiuu.adapter.CollectionAdapter;
import com.minglang.suiuu.fragment.collection.CollectionLoopFragment;
import com.minglang.suiuu.fragment.collection.CollectionRouteFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 收藏页面
 */
public class CollectionActivity extends FragmentActivity {

    /**
     * 返回
     */
    private ImageView collectionBack;

    /**
     * 搜索
     */
    private ImageView collectionSearch;

    /**
     * 圈子Tab头
     */
    private TextView collectionLoop;

    /**
     * 路线Tab头
     */
    private TextView collectionRoute;

    /**
     * 滑块
     */
    private ImageView collectionSlider;

    private ViewPager collectionPager;

    private List<Fragment> collectionList;

    private CollectionLoopFragment collectionLoopFragment;

    private CollectionRouteFragment collectionRouteFragment;

    private FragmentManager fm;

    private CollectionAdapter collectionAdapter;

    private int currIndex = 1;// 当前页卡编号

    private int sliderViewWidth;//图片宽度

    private int tabWidth;// 每个tab头的宽度

    private int offsetX;//偏移量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        initView();

        ViewAction();

    }

    private void ViewAction() {

        collectionBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        collectionSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        collectionLoop.setOnClickListener(new CollectionClick(0));
        collectionRoute.setOnClickListener(new CollectionClick(1));

        collectionPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                if (collectionSlider.getVisibility() == View.INVISIBLE) {
                    collectionSlider.setVisibility(View.VISIBLE);
                }

                Animation anim = new TranslateAnimation(tabWidth * currIndex + offsetX, tabWidth * i + offsetX, 0, 0);
                currIndex = i;
                anim.setFillAfter(true);
                anim.setDuration(200);
                collectionSlider.startAnimation(anim);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    /**
     * 初始化方法
     */
    private void initView() {

        collectionBack = (ImageView) findViewById(R.id.collectionBack);
        collectionSearch = (ImageView) findViewById(R.id.collectionSearch);

        collectionLoop = (TextView) findViewById(R.id.collectionLoop);
        collectionRoute = (TextView) findViewById(R.id.collectionRoute);

        collectionSlider = (ImageView) findViewById(R.id.collectionSlider);
        collectionSlider.setVisibility(View.INVISIBLE);

        collectionPager = (ViewPager) findViewById(R.id.collectionPager);

        collectionList = new ArrayList<Fragment>();

        collectionLoopFragment = CollectionLoopFragment.newInstance("a", "b");
        collectionRouteFragment = CollectionRouteFragment.newInstance("c", "d");

        collectionList.add(collectionLoopFragment);
        collectionList.add(collectionRouteFragment);

        fm = getSupportFragmentManager();

        collectionAdapter = new CollectionAdapter(fm, collectionList);
        collectionPager.setAdapter(collectionAdapter);

        initImageView();
    }

    private void initImageView() {
        sliderViewWidth = BitmapFactory.decodeResource(getResources(), R.drawable.slider).getWidth();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        tabWidth = screenW / 2;
        if (sliderViewWidth > tabWidth) {
            collectionSlider.getLayoutParams().width = tabWidth;
            sliderViewWidth = tabWidth;
        }

        offsetX = (tabWidth - sliderViewWidth) / 2;
    }

    class CollectionClick implements View.OnClickListener {

        private int index;

        public CollectionClick(int index) {
            this.index = index;
        }

        @Override
        public void onClick(View v) {
            collectionPager.setCurrentItem(index);
        }
    }

}
