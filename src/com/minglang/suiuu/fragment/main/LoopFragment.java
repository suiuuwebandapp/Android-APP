package com.minglang.suiuu.fragment.main;

import com.minglang.suiuu.R;
import com.minglang.suiuu.adapter.LoopFragmentPagerAdapter;
import com.minglang.suiuu.fragment.loop.AreaFragment;
import com.minglang.suiuu.fragment.loop.ThemeFragment;


import android.annotation.SuppressLint;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 圈子页面
 */
public class LoopFragment extends Fragment {

    /**
     * tab头对象
     */
    private TextView title0, title1;

    /**
     * 滑块
     */
    private ImageView sliderView;

    private ViewPager loopViewPager;

    private FragmentManager fm;

    /**
     * 主题页面
     */
    private ThemeFragment themeFragment;

    /**
     * 地区页面
     */
    private AreaFragment areaFragment;

    private List<Fragment> fragments;

    private LoopFragmentPagerAdapter lfpAdapter;

    private DisplayMetrics dm;

    private int screenW;

    private int currIndex = 1;// 当前页卡编号

    private int sliderViewWidth;//图片宽度

    private int tabWidth;// 每个tab头的宽度

    private int offsetX;//偏移量

    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_loop, null);

        initView(rootView);

        ViewAction();

        return rootView;
    }


    private void ViewAction() {

        title0.setOnClickListener(new TitleOnClick(0));
        title1.setOnClickListener(new TitleOnClick(1));

        loopViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {

                switch (i){
                    case 0:
                        title0.setTextColor(getResources().getColor(R.color.slider_line_color));
                        title1.setTextColor(getResources().getColor(R.color.textColor));
                        break;
                    case 1:
                        title0.setTextColor(getResources().getColor(R.color.textColor));
                        title1.setTextColor(getResources().getColor(R.color.slider_line_color));
                        break;
                }

                Animation anim = new TranslateAnimation(tabWidth * currIndex + offsetX, tabWidth * i + offsetX, 0, 0);
                currIndex = i;
                anim.setFillAfter(true);
                anim.setDuration(200);
                sliderView.startAnimation(anim);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    /**
     * 初始化方法
     *
     * @param rootView Fragment根View
     */
    private void initView(View rootView) {

        dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

        screenW = dm.widthPixels;// 获取设备宽度
        tabWidth = screenW / 2;

        title0 = (TextView) rootView.findViewById(R.id.theme_title);
        title1 = (TextView) rootView.findViewById(R.id.area_title);

        sliderView = (ImageView) rootView.findViewById(R.id.slideerView);
//        sliderView.setVisibility(View.INVISIBLE);
        ViewGroup.LayoutParams sliderParams = sliderView.getLayoutParams();
        sliderParams.width = tabWidth;
        sliderView.setLayoutParams(sliderParams);

        loopViewPager = (ViewPager) rootView.findViewById(R.id.loopViewPager);

        themeFragment = new ThemeFragment();
        areaFragment = new AreaFragment();

        fm = getFragmentManager();

        fragments = new ArrayList<>();
        fragments.add(themeFragment);
        fragments.add(areaFragment);

        lfpAdapter = new LoopFragmentPagerAdapter(fm, fragments);

        loopViewPager.setAdapter(lfpAdapter);

        initImageView();
    }

    private void initImageView() {

        sliderViewWidth = BitmapFactory.decodeResource(getResources(), R.drawable.slider).getWidth();//获取图片宽度

        if (sliderViewWidth > tabWidth) {
            sliderView.getLayoutParams().width = tabWidth;
            sliderViewWidth = tabWidth;
        }

        offsetX = (tabWidth - sliderViewWidth) / 2;

    }


    class TitleOnClick implements View.OnClickListener {

        private int index;

        public TitleOnClick(int index) {
            this.index = index;
        }

        @Override
        public void onClick(View v) {
            loopViewPager.setCurrentItem(index);
        }
    }

}
