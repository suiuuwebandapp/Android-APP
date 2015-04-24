package com.minglang.suiuu.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.minglang.suiuu.R;
import com.minglang.suiuu.adapter.LoopArticleImageAdapter;
import com.minglang.suiuu.customview.NoScrollBarGridView;

/**
 * 具体某个地区/主题下的某个帖子
 * <p/>
 * 网络部分未完成
 */
public class LoopArticleActivity extends Activity {

    /**
     * 返回键
     */
    private ImageView back;

    /**
     * 点赞
     */
    private TextView praise;

    /**
     * 收藏
     */
    private TextView collection;

    /**
     * 位置信息
     */
    private TextView locationName;

    /**
     * 用户头像
     */
    private ImageView headImage;

    /**
     * 用户名
     */
    private TextView userName;

    /**
     * 用户位置
     */
    private TextView userLocation;

    /**
     * 修改
     */
    private TextView editor;

    /**
     * 删除
     */
    private TextView delete;

    /**
     * 文章内容
     */
    private TextView articleContent;

    /**
     * 展示图片的GridView
     */
    private NoScrollBarGridView noScrollBarGridView;

    /**
     * 展示评论
     */
    private TextView showComments;

    /**
     * 分享
     */
    private TextView share;

    /**
     * 评论
     */
    private TextView comments;

    /**
     * 图片适配器
     */
    private LoopArticleImageAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loop_article);

        initView();

        ViewAction();

    }

    private void ViewAction() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        praise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        editor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(LoopArticleActivity.this).setTitle(getResources().
                        getString(R.string.sure_delete))
                        .setNegativeButton(getResources().getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton(getResources().getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create().show();
            }
        });

        noScrollBarGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        showComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /**
     * 初始化方法
     */
    private void initView() {
        back = (ImageView) findViewById(R.id.loop_article_back);
        praise = (TextView) findViewById(R.id.loop_article_praise);
        collection = (TextView) findViewById(R.id.loop_article_collection);

        locationName = (TextView) findViewById(R.id.loop_article_location);

        headImage = (ImageView) findViewById(R.id.loop_article_user_head_image);
        userName = (TextView) findViewById(R.id.loop_article_user_name);
        userLocation = (TextView) findViewById(R.id.loop_article_user_location);

        editor = (TextView) findViewById(R.id.loop_article_editor);
        delete = (TextView) findViewById(R.id.loop_article_delete);

        articleContent = (TextView) findViewById(R.id.loop_article_content);
        noScrollBarGridView = (NoScrollBarGridView) findViewById(R.id.loop_article_grid);
        showComments = (TextView) findViewById(R.id.loop_article_show_comment);

        share = (TextView) findViewById(R.id.loop_article_share);
        comments = (TextView) findViewById(R.id.loop_article_comments);
    }

}
