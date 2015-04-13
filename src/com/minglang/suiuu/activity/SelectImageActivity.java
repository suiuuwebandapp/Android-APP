package com.minglang.suiuu.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.minglang.suiuu.R;
import com.minglang.suiuu.adapter.SelectImageAdapter;
import com.minglang.suiuu.customview.ListImageDirPopupWindow;
import com.minglang.suiuu.entity.ImageFolder;

import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class SelectImageActivity extends Activity {

    /**
     * 扫描完成
     */
    private static final int SCANNING_COMPLETE = 1;

    /**
     * 返回键
     */
    private ImageView back;

    /**
     * 完成
     */
    private TextView complete;

    /**
     * 选择图片
     */
    private GridView selectImage;

    /**
     * 所有图片
     */
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

                    initPopupWindow();

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
    private List<ImageFolder> mImageFolders = new ArrayList<>();

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
    private List<String> mImages;

    private SelectImageAdapter selectPictureAdapter;

    private ListImageDirPopupWindow mListImageDirPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_image);

        initView();

        ViewAction();

        ObtainImage();

    }

    /**
     * 初始化PopupWindow
     */
    private void initPopupWindow() {
        mListImageDirPopupWindow = new ListImageDirPopupWindow(ViewGroup.LayoutParams.MATCH_PARENT,
                mScreenHeight / 10 * 7,mImageFolders,
                LayoutInflater.from(this).inflate(R.layout.check_img_list_dir, null));

        mListImageDirPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                // 设置背景颜色变暗
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().setAttributes(lp);
            }
        });

        mListImageDirPopupWindow.setOnImageDirSelected(new ListImageDirPopupWindow.OnImageDirSelected() {
            @Override
            public void selected(ImageFolder folder) {

                mImgDir = new File(folder.getDir());

                mImages = Arrays.asList(mImgDir.list(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String filename) {
                        if (filename.endsWith(".jpg") || filename.endsWith(".png")
                                || filename.endsWith(".jpeg")) {
                            return true;
                        }

                        return false;
                    }
                }));

                /**
                 * 可以看到文件夹的路径和图片的路径分开保存，极大的减少了内存的消耗；
                 */
                selectPictureAdapter = new SelectImageAdapter(getApplicationContext(), mImages,
                        R.layout.item_select_image, mImgDir.getAbsolutePath(),
                        complete);

                selectImage.setAdapter(selectPictureAdapter);
                // mAdapter.notifyDataSetChanged();
                allPicture.setText(folder.getName());
                mListImageDirPopupWindow.dismiss();
            }
        });
    }

    /**
     * 数据绑定到GridView
     */
    private void ImageDataToGirdView() {
        if (mImgDir == null) {
            Toast.makeText(this, "暂无图片！", Toast.LENGTH_SHORT).show();
            return;
        }

        mImages = Arrays.asList(mImgDir.list());

        String[] str = mImgDir.toString().split("/");

        allPicture.setText("/" + str[str.length - 1]);

        selectPictureAdapter = new SelectImageAdapter(this, mImages, R.layout.item_select_image,
                mImgDir.getAbsolutePath(), complete);
        selectImage.setAdapter(selectPictureAdapter);
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
                ContentResolver mContentResolver = SelectImageActivity.this.getContentResolver();

                // 只查询jpeg和png的图片
                Cursor mCursor = mContentResolver.query(mImageUri, null,
                        MediaStore.Images.Media.MIME_TYPE + "=? or "
                        + MediaStore.Images.Media.MIME_TYPE + "=?",
                        new String[]{"image/jpeg", "image/png"},
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
                            if (filename.endsWith(".jpg") || filename.endsWith(".png")
                                    || filename.endsWith(".jpeg")) {
                                return true;
                            }
                            return false;
                        }
                    }).length;

                    imageFolder.setCount(picSize);
                    mImageFolders.add(imageFolder);


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

    private static final int TAKE_PICTURE = 520;

    /**
     * 按钮点击事件处理
     */
    private void ViewAction() {
        Click click = new Click();

        back.setOnClickListener(click);
        complete.setOnClickListener(click);
        allPicture.setOnClickListener(click);

    }

    /**
     * 初始化方法
     */
    private void initView() {

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        mScreenHeight = dm.heightPixels;

        back = (ImageView) findViewById(R.id.selectImageBack);
        complete = (TextView) findViewById(R.id.selectImageComplete);

        selectImage = (GridView) findViewById(R.id.selectImage);

        allPicture = (TextView) findViewById(R.id.selectImageAll);
    }

    private String cameraPath = null;

    /**
     * 用于拍照时获取输出的Uri
     *
     * @version 1.0
     * @author zyh
     * @return
     */
    protected Uri getOutputMediaFileUri() {
        File mediaStorageDir = new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Night");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
        cameraPath = mediaFile.getAbsolutePath();
        return Uri.fromFile(mediaFile);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Log.i("TAG",data.getData().toString());
        }

    }

    class Click implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.selectPictureBack:
                    finish();
                    break;

                case R.id.selectImageComplete:

                    break;

                case R.id.selectPictureAll:
                    mListImageDirPopupWindow
                            .setAnimationStyle(R.style.anim_popup_dir);
                    mListImageDirPopupWindow.showAsDropDown(allPicture, 0, 0);

                    // 设置背景颜色变暗
                    WindowManager.LayoutParams lp = getWindow().getAttributes();
                    lp.alpha = .3f;
                    getWindow().setAttributes(lp);
                    break;
            }
        }
    }

}
