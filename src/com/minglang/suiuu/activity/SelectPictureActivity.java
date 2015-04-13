package com.minglang.suiuu.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.minglang.suiuu.R;
import com.minglang.suiuu.entity.ImageFolder;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SelectPictureActivity extends Activity {

    /**
     * 扫描完成
     */
    private static final int SCANNING_COMPLETE = 1;

    private ImageView back;

    private TextView complete;

    private GridView selectImage;

    private TextView allPicture;

    private ProgressDialog progressDialog;

    /**
     * 屏幕高度
     */
    private int mScreenHeight;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            switch (msg.what) {
                case SCANNING_COMPLETE:
                    progressDialog.dismiss();

                    ImageDataToGirdView();

                    break;
            }

            return false;
        }
    });

    /**
     * 临时的辅助类，用于防止同一个文件夹的多次扫描
     */
    private HashSet<String> mDirPaths = new HashSet<>();

    /**
     * 扫描拿到所有的图片文件夹
     */
    private List<ImageFolder> mImageFloders = new ArrayList<>();

    /**
     * 存储文件夹中的图片数量
     */
    private int mPicsSize;

    /**
     * 图片数量最多的文件夹
     */
    private File mImgDir;

    /**
     * 所有的图片
     */
    private List<String> mImgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_picture);

        initView();

        ViewAction();

        ObtainImage();

    }

    /**
     * 数据绑定到GridView
     */
    private void ImageDataToGirdView() {

    }

    /**
     * 获取图片
     */
    private void ObtainImage() {

        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "暂无外部存储", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog = ProgressDialog.show(this, null, "正在加载，请稍候...");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String firstImage = null;

                Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver mContentResolver = SelectPictureActivity.this.getContentResolver();

                // 只查询jpeg和png的图片
                Cursor mCursor = mContentResolver.query(mImageUri, null, MediaStore.Images.Media.MIME_TYPE + "=? or "
                                + MediaStore.Images.Media.MIME_TYPE + "=?", new String[]{"image/jpeg", "image/png"},
                        MediaStore.Images.Media.DATE_MODIFIED);

                while (mCursor.moveToNext()) {
                    // 获取图片的路径
                    String path = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA));

                    //拿到第一张图片路径
                    if (firstImage == null) {
                        firstImage = path;
                    }

                    // 获取该图片的父路径名
                    File parentFile = new File(path).getParentFile();
                    if (parentFile == null) {
                        continue;
                    }

                    //图片父路径
                    String dirPath = parentFile.getAbsolutePath();
                    ImageFolder imageFolder;

                    if (mDirPaths.contains(dirPath)) {
                        continue;
                    } else {
                        mDirPaths.add(dirPath);
                        // 初始化imageFolder
                        imageFolder = new ImageFolder();
                        imageFolder.setDir(dirPath);
                        imageFolder.setFirstImagePath(path);
                    }

                    int picSize = parentFile.list(new FilenameFilter() {
                        @Override
                        public boolean accept(File dir, String filename) {
                            if (filename.endsWith(".jpg") || filename.endsWith(".png") || filename.endsWith(".jpeg")) {
                                return true;
                            }
                            return false;
                        }
                    }).length;

                    imageFolder.setCount(picSize);
                    mImageFloders.add(imageFolder);


                    if (picSize > mPicsSize) {
                        mPicsSize = picSize;
                        mImgDir = parentFile;
                    }

                }
                mCursor.close();

                // 扫描完成，辅助的HashSet也就可以释放内存了
                mDirPaths = null;

                handler.sendEmptyMessage(SCANNING_COMPLETE);
            }
        });
        thread.start();
    }

    /**
     * 按钮点击事件处理
     */
    private void ViewAction() {
        Click click = new Click();

        back.setOnClickListener(click);
        complete.setOnClickListener(click);
        allPicture.setOnClickListener(click);

        selectImage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    /**
     * 初始化方法
     */
    private void initView() {

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        mScreenHeight = dm.heightPixels;

        back = (ImageView) findViewById(R.id.selectPictureBack);
        complete = (TextView) findViewById(R.id.selectPictureComplete);

        selectImage = (GridView) findViewById(R.id.selectImage);

        allPicture = (TextView) findViewById(R.id.selectPictureAll);
    }

    class Click implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.selectPictureBack:
                    break;
                case R.id.selectPictureComplete:
                    break;
                case R.id.selectPictureAll:
                    break;
            }
        }
    }

}
