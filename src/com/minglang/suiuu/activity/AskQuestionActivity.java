package com.minglang.suiuu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.minglang.suiuu.R;
import com.minglang.suiuu.adapter.ShowGVPictureAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2015/4/23.
 */
public class AskQuestionActivity extends Activity {
    private GridView gv_show_picture;
    private List<String> listPicture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_question);
        initView();
        gv_show_picture.setAdapter(new ShowGVPictureAdapter(this,listPicture));
        gv_show_picture.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

              if(position == (listPicture.size()==0?listPicture.size():listPicture.size()+1)) {
                    Intent intent =new Intent(AskQuestionActivity.this,SelectPictureActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    public void initView() {
        listPicture = new ArrayList<>();
        gv_show_picture = (GridView) findViewById(R.id.gv_show_picture);
    }
}
