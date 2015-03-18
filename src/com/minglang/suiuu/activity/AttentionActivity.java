package com.minglang.suiuu.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.minglang.suiuu.R;
import com.minglang.suiuu.adapter.AttentionPagerAdapter;
import com.minglang.suiuu.fragment.AttentionThemeFragment;
import com.minglang.suiuu.fragment.AttentionUserFragment;

import java.util.ArrayList;

public class AttentionActivity extends FragmentActivity {

    private ViewPager attentionPager;

    private ArrayList<Fragment> fragmentList;

    private FragmentManager fm;

    private AttentionThemeFragment attentionThemeFragment;

    private AttentionUserFragment attentionUserFragment;

    private AttentionPagerAdapter attentionPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention);

        initView();

    }

    private void ViewAction(){

    }

    private void initView() {
        attentionPager = (ViewPager) findViewById(R.id.attentionPager);

        fragmentList = new ArrayList<Fragment>();

        fm = getSupportFragmentManager();

        attentionThemeFragment = AttentionThemeFragment.newInstance("a", "b");
        attentionUserFragment = AttentionUserFragment.newInstance("c", "d");

        fragmentList.add(attentionThemeFragment);
        fragmentList.add(attentionUserFragment);

        attentionPagerAdapter = new AttentionPagerAdapter(fm, fragmentList);

        attentionPager.setAdapter(attentionPagerAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_attention, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class AttenClick implements View.OnClickListener {

        private int index;

        public AttenClick(int index) {
            this.index = index;
        }

        @Override
        public void onClick(View v) {
            attentionPager.setCurrentItem(index);
        }
    }

}
