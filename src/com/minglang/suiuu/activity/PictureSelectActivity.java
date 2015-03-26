package com.minglang.suiuu.activity;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.minglang.suiuu.R;
import com.minglang.suiuu.utils.Utils;

public class PictureSelectActivity extends Activity {

    private static final int SELECT_OK = 100;

    private TextView BackView;

    private TextView ConfirmView;

    private GridView selectView;

    private TextView AllPicture;

    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_select);

        initView();

        ViewAction();

    }

    private void ViewAction() {
        BackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ConfirmView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        AllPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private PopupWindow CreatePopupWindow() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.pop_select_list, null);
        PopupWindow popwindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, Utils.dip2px(PictureSelectActivity.this, 400), true);
        popwindow.setOutsideTouchable(true);
        popwindow.setBackgroundDrawable(new BitmapDrawable());

        popwindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams ll = getWindow().getAttributes();
                ll.alpha = 1f;
                getWindow().setAttributes(ll);
            }
        });

        ListView mListView = (ListView) view.findViewById(R.id.pictureSelectList);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        return popwindow;
    }

    private void initView() {
        BackView = (TextView) findViewById(R.id.pictureSelectBack);
        ConfirmView = (TextView) findViewById(R.id.pictureSelectConfirm);

        selectView = (GridView) findViewById(R.id.pictureSelectGrid);

        AllPicture = (TextView) findViewById(R.id.pictureSelectAllPicture);
    }

}
