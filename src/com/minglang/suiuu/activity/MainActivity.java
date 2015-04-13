package com.minglang.suiuu.activity;


import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.minglang.suiuu.R;
import com.minglang.suiuu.adapter.MainSliderAdapter;
import com.minglang.suiuu.fragment.main.ConversationFragment;
import com.minglang.suiuu.fragment.main.LoopFragment;
import com.minglang.suiuu.fragment.main.MainFragment;
import com.minglang.suiuu.fragment.main.RouteFragment;
import com.minglang.suiuu.utils.SystemBarTintManager;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 应用程序主界面
 */
public class MainActivity extends FragmentActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String[] TITLE = {"收藏", "关注", "消息", "粉丝", "设置", "退出"};

    private DrawerLayout mDrawerLayout;

    private RelativeLayout slideLayout;

    /**
     * 标题
     */
    private TextView titleInfo;

    private ImageView drawerSwitch;

    /**
     * 点击修改昵称
     */
    private TextView nickNameView;

    /**
     * 点击修改头像
     */
    private ImageView headImage;

    /**
     * 各个Tab页
     */
    private LinearLayout tab1, tab2, tab3, tab4;

    /**
     * 跳转发送新帖子
     */
    private ImageButton sendMsg;

    private FragmentManager fm;

    /**
     * 主页页面
     */
    private MainFragment mainFragment;

    /**
     * 圈子页面
     */
    private LoopFragment loopFragment;

    /**
     * 路线页面
     */
    private RouteFragment routeFragment;

    /**
     * 会话页面
     */
    private ConversationFragment conversationFragment;

    private ListView mListView;

    /**
     * 屏幕宽度
     */
    private int screenWidth;

    /**
     * 状态栏控制器
     */
    private SystemBarTintManager mTintManager;

    /**
     * 状态栏高度
     */
    private int statusBarHeight;

    /**
     * 虚拟按键高度
     */
    private int navigationBarHeight;

    /**
     * 系统版本是否高于4.4
     */
    private boolean isKITKAT;

    /**
     * 是否有虚拟按键
     */
    private boolean isNavigationBar;

    /**
     * 随问Button
     */
    private ImageView ask;

    /**
     * 随拍Button
     */
    private ImageView pic;

    /**
     * 随记Button
     */
    private ImageView record;

    private boolean isMainIcon = false;

    private AnimationSet animationSetHide, animationSetShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
        setContentView(R.layout.activity_main);

        initView();

        ViewAction();

    }

    /**
     * 控件行为
     */
    private void ViewAction() {

        OnClickListener onClickListener = new OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.drawerSwitch:
                        if (mDrawerLayout.isDrawerVisible(slideLayout)) {
                            mDrawerLayout.closeDrawer(slideLayout);
                        } else {
                            mDrawerLayout.openDrawer(slideLayout);
                        }
                        break;

                    case R.id.headImage:
                        break;

                    case R.id.nickName:
                        Intent nickIntent = new Intent(MainActivity.this, PersonalActivity.class);
                        startActivity(nickIntent);
                        mDrawerLayout.closeDrawer(slideLayout);
                        break;

                    case R.id.tab1:
                        titleInfo.setText(getResources().getString(R.string.title1));
                        LoadMainFragment();
                        break;

                    case R.id.tab2:
                        titleInfo.setText(getResources().getString(R.string.title2));
                        LoadLoopFragment();
                        break;

                    case R.id.tab3:
                        titleInfo.setText(getResources().getString(R.string.title3));
                        LoadRouteFragment();
                        break;

                    case R.id.tab4:
                        titleInfo.setText(getResources().getString(R.string.title4));
                        LoadConversationFragment();
                        break;

                    case R.id.sendNewMessage:
                        if (isMainIcon) {
                            ask.startAnimation(animationSetHide);
                            pic.startAnimation(animationSetHide);
                            record.startAnimation(animationSetHide);

                            Log.i(TAG, "icon hide");

                        } else {
                            ask.startAnimation(animationSetShow);
                            pic.startAnimation(animationSetShow);
                            record.startAnimation(animationSetShow);

                            Log.i(TAG, "icon show");

                        }
                        break;
                }
            }
        };

        drawerSwitch.setOnClickListener(onClickListener);

        nickNameView.setOnClickListener(onClickListener);

        headImage.setOnClickListener(onClickListener);

        tab1.setOnClickListener(onClickListener);
        tab2.setOnClickListener(onClickListener);
        tab3.setOnClickListener(onClickListener);
        tab4.setOnClickListener(onClickListener);

        sendMsg.setOnClickListener(onClickListener);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDrawerLayout.closeDrawer(slideLayout);
                switch (position) {
                    //收藏
                    case 0:
                        Intent intent0 = new Intent(MainActivity.this, CollectionActivity.class);
                        startActivity(intent0);
                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        break;
                    //关注
                    case 1:
                        Intent intent1 = new Intent(MainActivity.this, AttentionActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        break;
                    //新提醒
                    case 2:
                        Intent intent2 = new Intent(MainActivity.this, NewRemindActivity.class);
                        startActivity(intent2);
                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        break;
                    //粉丝
                    case 3:
                        Intent intent3 = new Intent(MainActivity.this, FansActivity.class);
                        startActivity(intent3);
                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        break;
                    //设置
                    case 4:
                        Intent intent4 = new Intent(MainActivity.this, SettingActivity.class);
                        startActivity(intent4);
                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        break;
                    //退出
                    case 5:
                        finish();
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        break;

                }
            }
        });


        ask.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,SelectImageActivity.class);
                startActivity(intent);
            }
        });

        pic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "pic");
            }
        });

        record.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "record");
            }
        });

    }

    /**
     * 加载主页页面
     */
    private void LoadMainFragment() {
        FragmentTransaction ft = fm.beginTransaction();
        if (loopFragment != null) {
            if (loopFragment.isAdded()) {
                ft.hide(loopFragment);
            }
        }
        if (routeFragment != null) {
            if (routeFragment.isAdded()) {
                ft.hide(routeFragment);
            }
        }
        if (conversationFragment != null) {
            if (conversationFragment.isAdded()) {
                ft.hide(conversationFragment);
            }
        }
        if (mainFragment == null) {
            mainFragment = new MainFragment();
        }
        if (mainFragment.isAdded()) {
            ft.show(mainFragment);
        } else {
            ft.add(R.id.showLayout, mainFragment);
        }
        ft.commit();
    }

    /**
     * 加载圈子页面
     */
    private void LoadLoopFragment() {
        FragmentTransaction ft = fm.beginTransaction();
        if (mainFragment != null) {
            if (mainFragment.isAdded()) {
                ft.hide(mainFragment);
            }
        }
        if (routeFragment != null) {
            if (routeFragment.isAdded()) {
                ft.hide(routeFragment);
            }
        }
        if (conversationFragment != null) {
            if (conversationFragment.isAdded()) {
                ft.hide(conversationFragment);
            }
        }
        if (loopFragment == null) {
            loopFragment = new LoopFragment();
        }
        if (loopFragment.isAdded()) {
            ft.show(loopFragment);
        } else {
            ft.add(R.id.showLayout, loopFragment);
        }
        ft.commit();
    }

    /**
     * 加载路线页面
     */
    private void LoadRouteFragment() {
        FragmentTransaction ft = fm.beginTransaction();
        if (mainFragment != null) {
            if (mainFragment.isAdded()) {
                ft.hide(mainFragment);
            }
        }
        if (loopFragment != null) {
            if (loopFragment.isAdded()) {
                ft.hide(loopFragment);
            }
        }
        if (conversationFragment != null) {
            if (conversationFragment.isAdded()) {
                ft.hide(conversationFragment);
            }
        }
        if (routeFragment == null) {
            routeFragment = new RouteFragment();
        }
        if (routeFragment.isAdded()) {
            ft.show(routeFragment);
        } else {
            ft.add(R.id.showLayout, routeFragment);
        }
        ft.commit();
    }

    /**
     * 加载会话页面
     */
    private void LoadConversationFragment() {
        FragmentTransaction ft = fm.beginTransaction();
        if (mainFragment != null) {
            if (mainFragment.isAdded()) {
                ft.hide(mainFragment);
            }
        }
        if (loopFragment != null) {
            if (loopFragment.isAdded()) {
                ft.hide(loopFragment);
            }
        }
        if (routeFragment != null) {
            if (routeFragment.isAdded()) {
                ft.hide(routeFragment);
            }
        }
        if (conversationFragment == null) {
            conversationFragment = new ConversationFragment();
        }
        if (conversationFragment.isAdded()) {
            ft.show(conversationFragment);
        } else {
            ft.add(R.id.showLayout, conversationFragment);
        }
        ft.commit();
    }

    /**
     * 加载初始默认页面
     */
    private void LoadDefaultFragment() {
        if (mainFragment == null) {
            mainFragment = new MainFragment();
        }
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.showLayout, mainFragment);
        ft.commit();
    }

    /**
     * 初始化得到各种Bar高度
     */
    private void initNumber() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        /**
         屏幕高度
         */
        int screenHeight = dm.heightPixels;

        Log.i(TAG, "屏幕宽度:" + String.valueOf(screenWidth));
        Log.i(TAG, "屏幕高度:" + String.valueOf(screenHeight));

        mTintManager = new SystemBarTintManager(this);

        SystemBarTintManager.SystemBarConfig systemBarConfig = mTintManager.getConfig();

        /**
         虚拟按键高度
         */
        navigationBarHeight = systemBarConfig.getNavigationBarHeight();
        Log.i(TAG, "NavigationBarHeight:" + String.valueOf(navigationBarHeight));

        /**
         虚拟按键宽度(?)
         */
        int navigationBarWidth = systemBarConfig.getNavigationBarWidth();
        Log.i(TAG, "NavigationBarWidth:" + String.valueOf(navigationBarWidth));

        statusBarHeight = systemBarConfig.getStatusBarHeight();
        Log.i(TAG, "statusBarHeight:" + String.valueOf(statusBarHeight));

        isKITKAT = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        isNavigationBar = systemBarConfig.hasNavigtionBar();
    }

    /**
     * 初始化方法
     */
    private void initView() {

        initNumber();

        /****************设置状态栏颜色*************/

        mTintManager.setStatusBarTintEnabled(true);
        mTintManager.setNavigationBarTintEnabled(false);
        mTintManager.setTintColor(getResources().getColor(R.color.tr_black));

        /***************Activity可控制View设置padding****************/
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerLayout.setFocusableInTouchMode(true);

        RelativeLayout mainShowLayout = (RelativeLayout) findViewById(R.id.mainShowLayout);
//        LinearLayout tabSelect = (LinearLayout) findViewById(R.id.tabSelect);

        if (isNavigationBar) {
            if (isKITKAT) {
                mDrawerLayout.setPadding(0, statusBarHeight, 0, navigationBarHeight);
                mainShowLayout.setPadding(0, 0, 0, navigationBarHeight);

//                RelativeLayout.LayoutParams tabSelectParams = new RelativeLayout.LayoutParams(tabSelect.getLayoutParams());
//                screenHeight - navigationBarHeight - statusBarHeight
//                tabSelectParams.setMargins(0, screenHeight - navigationBarHeight - statusBarHeight*2, 0, 0);
//                tabSelect.setLayoutParams(tabSelectParams);

                Log.i(TAG, "4.4以上，有虚拟按键");

            }
//            else {
//                mDrawerLayout.setPadding(0, 0, 0, navigationBarHeight);
//                RelativeLayout.LayoutParams tabSelectParams = new RelativeLayout.LayoutParams(tabSelect.getLayoutParams());
//                tabSelectParams.setMargins(0, screenHeight - navigationBarHeight - statusBarHeight, 0, 0);
//                tabSelect.setLayoutParams(tabSelectParams);
                Log.i(TAG, "4.4以下，有虚拟按键");
//            }
        } else {
            if (isKITKAT) {
                mDrawerLayout.setPadding(0, statusBarHeight, 0, 0);
                Log.i(TAG, "4.4以上，无虚拟按键");
            } else {
                //Nothing
                Log.i(TAG, "4.4以下，无虚拟按键");
            }
        }

        if (isKITKAT) {
            /**************HeadLayout设置Margins*****************/
            Log.i(TAG, "版本测试3333");
            RelativeLayout titleLayout = (RelativeLayout) findViewById(R.id.titleLayout);
            RelativeLayout.LayoutParams titleLayoutParams = new RelativeLayout.LayoutParams(titleLayout.getLayoutParams());
            titleLayoutParams.setMargins(0, statusBarHeight, 0, 0);
            titleLayout.setLayoutParams(titleLayoutParams);
        } else {
            Log.i(TAG, "版本测试2233");
        }

        /*************设置侧滑菜单Params**********************/
        slideLayout = (RelativeLayout) findViewById(R.id.slideLayout);
        //版本高于4.4，设置侧滑菜单的padding
        if (isKITKAT) {
            slideLayout.setPadding(0, statusBarHeight, 0, 0);
        }

        ViewGroup.LayoutParams params = slideLayout.getLayoutParams();

        params.width = screenWidth / 4 * 3;
        slideLayout.setLayoutParams(params);

        titleInfo = (TextView) findViewById(R.id.titleInfo);

        drawerSwitch = (ImageView) findViewById(R.id.drawerSwitch);

        nickNameView = (TextView) findViewById(R.id.nickName);

        headImage = (ImageView) findViewById(R.id.headImage);

        tab1 = (LinearLayout) findViewById(R.id.tab1);
        tab2 = (LinearLayout) findViewById(R.id.tab2);
        tab3 = (LinearLayout) findViewById(R.id.tab3);
        tab4 = (LinearLayout) findViewById(R.id.tab4);

        sendMsg = (ImageButton) findViewById(R.id.sendNewMessage);

        mListView = (ListView) findViewById(R.id.drawerList);

        ask = (ImageView) findViewById(R.id.main_ask);
        pic = (ImageView) findViewById(R.id.main_pic);
        record = (ImageView) findViewById(R.id.main_record);

        List<String> stringList = new ArrayList<>();

        Collections.addAll(stringList, TITLE);

        MainSliderAdapter mainSliderAdapter = new MainSliderAdapter(this, stringList);

        mListView.setAdapter(mainSliderAdapter);

        fm = getSupportFragmentManager();

        mainFragment = new MainFragment();

        LoadDefaultFragment();

        initAnimation();
    }

    /**
     * 初始化动画
     */
    private void initAnimation() {

        Animation animHide = new AlphaAnimation(1.0f, 0.0f);
        Animation animShow = new AlphaAnimation(0.0f, 1.0f);

        Animation animNarrow = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, Animation.RELATIVE_TO_PARENT, Animation.RELATIVE_TO_PARENT);
        Animation animBoost = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_PARENT, Animation.RELATIVE_TO_PARENT);

        animationSetHide = new AnimationSet(true);
        animationSetHide.setDuration(500);
        animationSetHide.setAnimationListener(new MyAnimationListener());

        animationSetShow = new AnimationSet(true);
        animationSetShow.setDuration(500);
        animationSetShow.setAnimationListener(new MyAnimationListener());

        animationSetHide.addAnimation(animHide);
        animationSetHide.addAnimation(animNarrow);

        animationSetShow.addAnimation(animShow);
        animationSetShow.addAnimation(animBoost);
    }

    class MyAnimationListener implements Animation.AnimationListener {
        @Override
        public void onAnimationRepeat(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if (isMainIcon) {
                ask.setVisibility(View.INVISIBLE);
                pic.setVisibility(View.INVISIBLE);
                record.setVisibility(View.INVISIBLE);
                isMainIcon = false;
            } else {
                ask.setVisibility(View.VISIBLE);
                pic.setVisibility(View.VISIBLE);
                record.setVisibility(View.VISIBLE);
                isMainIcon = true;
            }
        }

        @Override
        public void onAnimationStart(Animation animation) {

        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        if (mDrawerLayout.isDrawerVisible(slideLayout)) {
            mDrawerLayout.closeDrawer(slideLayout);
        } else {
            finish();
        }
    }

}