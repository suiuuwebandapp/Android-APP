package com.minglang.suiuu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.minglang.suiuu.R;
import com.minglang.suiuu.customview.NoScrollBarView;

/**
 * 具体某个地区/主题下的某个帖子
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
     * 文章内容
     */
    private TextView articleContent;

    /**
     * 展示图片的GridView
     */
    private NoScrollBarView noScrollBarView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loop_article);

        initView();

    }

    private void ViewAction() {

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

        articleContent = (TextView) findViewById(R.id.loop_article_content);
        noScrollBarView = (NoScrollBarView) findViewById(R.id.loop_article_grid);
        showComments = (TextView) findViewById(R.id.loop_article_show_comment);

        share = (TextView) findViewById(R.id.loop_article_share);
        comments = (TextView) findViewById(R.id.loop_article_comments);
    }

}
