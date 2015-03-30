package com.minglang.suiuu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.minglang.suiuu.R;
import com.minglang.suiuu.customview.FloatingActionButton;
import com.minglang.suiuu.customview.FloatingActionMenu;
import com.minglang.suiuu.customview.SubActionButton;
import com.minglang.suiuu.utils.SlideInAnimationHandler;

import java.util.List;

public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        ImageView showDrawable = new ImageView(this);
        showDrawable.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_settings));

        FloatingActionButton darkButton = new FloatingActionButton.Builder(this)
//                .setTheme(FloatingActionButton.THEME_DARK)
                .setContentView(showDrawable)
                .setPosition(FloatingActionButton.POSITION_BOTTOM_CENTER)
                .build();

        SubActionButton.Builder rLSubBuilder = new SubActionButton.Builder(this)
                .setTheme(SubActionButton.THEME_DARK);
        ImageView rlIcon1 = new ImageView(this);
        ImageView rlIcon2 = new ImageView(this);
        ImageView rlIcon3 = new ImageView(this);
        ImageView rlIcon4 = new ImageView(this);
        ImageView rlIcon5 = new ImageView(this);

        rlIcon1.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_chat));
        rlIcon2.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_camera));
        rlIcon3.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_video));
        rlIcon4.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_place));
        rlIcon5.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_headphones));

        // Set 4 SubActionButtons
        FloatingActionMenu centerBottomMenu = new FloatingActionMenu.Builder(this)
                .setStartAngle(0)
                .setEndAngle(-180)
//                .setAnimationHandler(new SlideInAnimationHandler())

                .addSubActionView(rLSubBuilder.setContentView(rlIcon1).build())
                .addSubActionView(rLSubBuilder.setContentView(rlIcon2).build())
                .addSubActionView(rLSubBuilder.setContentView(rlIcon3).build())
//                .addSubActionView(rLSubBuilder.setContentView(rlIcon4).build())
//                .addSubActionView(rLSubBuilder.setContentView(rlIcon5).build())
                .attachTo(darkButton)
                .build();

        centerBottomMenu.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
            @Override
            public void onMenuOpened(FloatingActionMenu menu) {
                Log.i(TestActivity.class.getSimpleName(), "Open");
            }

            @Override
            public void onMenuClosed(FloatingActionMenu menu) {
                Log.i(TestActivity.class.getSimpleName(), "Close");
            }
        });

        rlIcon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TestActivity.class.getSimpleName(),"0");
            }
        });

        rlIcon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TestActivity.class.getSimpleName(),"1");
            }
        });

        rlIcon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TestActivity.class.getSimpleName(),"2");
            }
        });

    }


}