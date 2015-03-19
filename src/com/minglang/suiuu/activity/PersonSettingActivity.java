package com.minglang.suiuu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.minglang.suiuu.R;

public class PersonSettingActivity extends Activity {

    private ImageView personalSettingBack;

    private LinearLayout personalSettingHeadLayout;

    private ImageView personalSettingHeadImage;

    private EditText editNickName;

    private EditText editLocation;

    private EditText editTrade;

    private EditText editSign;

    private String str_NickName;

    private String str_Location;

    private String str_Trade;

    private String str_Sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_setting);

        initView();

        ViewAction();

    }

    private void ViewAction() {

        personalSettingBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        personalSettingHeadLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void initView() {
        personalSettingBack = (ImageView) findViewById(R.id.personalSettingBack);

        personalSettingHeadLayout = (LinearLayout) findViewById(R.id.personalSettingHeadLayout);

        personalSettingHeadImage = (ImageView) findViewById(R.id.personalSettingHeadImage);

        editNickName = (EditText) findViewById(R.id.editNickName);
        editLocation = (EditText) findViewById(R.id.editLocation);
        editTrade = (EditText) findViewById(R.id.editTrade);
        editSign = (EditText) findViewById(R.id.editSign);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_person_setting, menu);
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
}
