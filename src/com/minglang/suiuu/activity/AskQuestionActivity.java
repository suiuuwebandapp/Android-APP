package com.minglang.suiuu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import com.minglang.suiuu.R;
import com.minglang.suiuu.adapter.ShowGVPictureAdapter;
import com.minglang.suiuu.chat.activity.ShowBigImage;

import java.util.ArrayList;


/**
 * Created by Administrator on 2015/4/23.
 */
public class AskQuestionActivity extends Activity {
    private GridView gv_show_picture;
    private ArrayList<String> listPicture;
    private ImageView iv_top_back;
    private EditText et_search_question;
    private EditText et_question_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_question);
        int record = getIntent().getIntExtra("record", 0);
        initView();
        if (record == 1) {
            et_search_question.setHint(R.string.image_theme);
            et_question_description.setHint(R.string.activity_description);
        }
        gv_show_picture.setAdapter(new ShowGVPictureAdapter(this, listPicture));
        gv_show_picture.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == listPicture.size()) {
                    Intent intent = new Intent(AskQuestionActivity.this, SelectPictureActivity.class);
                    startActivityForResult(intent, 1);
                } else {
                    Intent showPicture = new Intent(AskQuestionActivity.this, ShowBigImage.class);
                    showPicture.putExtra("path", listPicture.get(position));
                    startActivity(showPicture);
                }
            }
        });
        iv_top_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void initView() {
        listPicture = new ArrayList<>();
        gv_show_picture = (GridView) findViewById(R.id.gv_show_picture);
        iv_top_back = (ImageView) findViewById(R.id.iv_top_back);
        et_search_question = (EditText) findViewById(R.id.search_question);
        et_question_description = (EditText) findViewById(R.id.et_question_description);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == 9) {
            listPicture = data.getStringArrayListExtra("pictureMessage");
            for (String string : listPicture) {
                Log.i("suiuu", string);
            }
            gv_show_picture.setAdapter(new ShowGVPictureAdapter(this, listPicture));
        }

    }
}
