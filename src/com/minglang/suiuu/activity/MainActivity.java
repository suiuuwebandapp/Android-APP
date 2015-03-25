package com.minglang.suiuu.activity;


import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.minglang.suiuu.R;
import com.minglang.suiuu.fragment.main.ConversationFragment;
import com.minglang.suiuu.fragment.main.LoopFragment;
import com.minglang.suiuu.fragment.main.MainFragment;
import com.minglang.suiuu.fragment.main.RouteFragment;


/**
 * 应用程序主界面
 */
public class MainActivity extends FragmentActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String[] TITLE = {"收藏", "关注", "新提醒", "粉丝", "设置", "退出"};

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
    private Button sendMsg;

    private OnClickListener click;

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

//    private Utils utils = null;


    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        ViewAction();

    }

    /**
     * 控件行为
     */
    private void ViewAction() {

        click = new OnClickListener() {

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
//                        utils.selectPicture(MainActivity.this);
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
                        break;
                }
            }
        };

        drawerSwitch.setOnClickListener(click);

        nickNameView.setOnClickListener(click);

        headImage.setOnClickListener(click);

        tab1.setOnClickListener(click);
        tab2.setOnClickListener(click);
        tab3.setOnClickListener(click);
        tab4.setOnClickListener(click);

        sendMsg.setOnClickListener(click);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDrawerLayout.closeDrawer(slideLayout);
                switch (position) {
                    //收藏
                    case 0:
                        Intent intent0 = new Intent(MainActivity.this, CollectionActivity.class);
                        startActivity(intent0);
                        mDrawerLayout.closeDrawer(slideLayout);
                        break;
                    //关注
                    case 1:
                        Intent intent1 = new Intent(MainActivity.this, AttentionActivity.class);
                        startActivity(intent1);
                        mDrawerLayout.closeDrawer(slideLayout);
                        break;
                    //新提醒
                    case 2:
                        Intent intent2 = new Intent(MainActivity.this, NewRemindActivity.class);
                        startActivity(intent2);
                        break;
                    //粉丝
                    case 3:
                        Intent intent3 = new Intent(MainActivity.this, FansActivity.class);
                        startActivity(intent3);
                        mDrawerLayout.closeDrawer(slideLayout);
                        break;
                    //设置
                    case 4:
                        Intent intent4 = new Intent(MainActivity.this, SettingActivity.class);
                        startActivity(intent4);
                        break;
                    //退出
                    case 5:
                        finish();
                        break;

                }
            }
        });

    }

//    private Uri uri = null;
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode != Activity.RESULT_OK) {
//            return;
//        }
//
//        if (null == data) {
//            return;
//        }
//
//        if (requestCode == AppConstant.KITKAT_LESS) {
//            uri = data.getData();
//            utils.cropPicture(MainActivity.this, uri);
//            Log.i(TAG, "Uri:" + uri.toString());
//
//        } else if (requestCode == AppConstant.KITKAT_ABOVE) {
//            uri = data.getData();
//            String imagePath = utils.getPath(MainActivity.this, uri);
//            utils.cropPicture(MainActivity.this, Uri.fromFile(new File(imagePath)));
//            Log.i(TAG, "Uri:" + uri.toString());
//
//        } else if (requestCode == AppConstant.INTENT_CROP) {
//            Bitmap bitmap = data.getParcelableExtra("data");
//            headImage.setImageBitmap(bitmap);
//        }
//
//    }

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
     * 初始化方法
     */
    private void initView() {

        titleInfo = (TextView) findViewById(R.id.titleInfo);

        drawerSwitch = (ImageView) findViewById(R.id.drawerSwitch);

        nickNameView = (TextView) findViewById(R.id.nickName);

        headImage = (ImageView) findViewById(R.id.headImage);

        tab1 = (LinearLayout) findViewById(R.id.tab1);
        tab2 = (LinearLayout) findViewById(R.id.tab2);
        tab3 = (LinearLayout) findViewById(R.id.tab3);
        tab4 = (LinearLayout) findViewById(R.id.tab4);

        sendMsg = (Button) findViewById(R.id.sendNewMessage);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerLayout.setFocusableInTouchMode(true);

        slideLayout = (RelativeLayout) findViewById(R.id.slideLayout);

        mListView = (ListView) findViewById(R.id.drawerList);
        mListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, TITLE));

        fm = getSupportFragmentManager();

        mainFragment = new MainFragment();

        LoadDefaultFragment();

//        utils = Utils.getInstance();
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
