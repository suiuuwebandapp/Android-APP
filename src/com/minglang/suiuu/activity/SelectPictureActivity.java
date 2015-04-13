package com.minglang.suiuu.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.minglang.suiuu.R;
import com.minglang.suiuu.adapter.SelectPictureAdapter;
import com.minglang.suiuu.customview.BasePopupWindowForListView;
import com.minglang.suiuu.entity.ImageFolder;
import com.minglang.suiuu.entity.ImageItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectPictureActivity extends Activity {

    private static final String TAG = SelectPictureActivity.class.getSimpleName();

    /**
     * 扫描完成
     */
    private static final int SCANNING_COMPLETE = 1;

    private ImageLoader loader;
    private DisplayImageOptions options;

    private ContentResolver mContentResolver;

    private ImageFolder imageAll, currentImageFolder;

    /**
     * 临时的辅助类，用于防止同一个文件夹的多次扫描
     */
    private Map<String, Integer> tmpDir = new HashMap<>();
    private List<ImageFolder> mDirPaths = new ArrayList<>();

    /**
     * 已选择的图片
     */
    private List<String> selectedPicture = new ArrayList<>();

    private Context context = this;

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

    private SelectPictureAdapter selectPictureAdapter;

    private ListPictureDirPopupWindow listPictureDirPopupWindow;

    public SelectPictureActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_picture);

        initView();

        initPopupWindow();

        ObtainThumbnail();

        selectPictureAdapter = new SelectPictureAdapter(context, currentImageFolder.images,
                currentImageFolder, loader, options, selectedPicture, complete);
        selectImage.setAdapter(selectPictureAdapter);

        ViewAction();

    }

    private void ViewAction() {
        allPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /**
     * 获取缩略图
     */
    private void ObtainThumbnail() {
        Cursor mCursor = mContentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.ImageColumns.DATA}, "", null,
                MediaStore.MediaColumns.DATE_ADDED + " DESC");

        if (mCursor.moveToFirst()) {
            int _date = mCursor.getColumnIndex(MediaStore.Images.Media.DATA);
            do {
                // 获取图片的路径
                String path = mCursor.getString(_date);

                imageAll.images.add(new ImageItem(path));

                // 获取该图片的父路径名
                File parentFile = new File(path).getParentFile();

                if (parentFile == null) {
                    continue;
                }

                ImageFolder imageFolder = null;

                String dirPath = parentFile.getAbsolutePath();

                if (!tmpDir.containsKey(dirPath)) {
                    // 初始化imageFolder
                    imageFolder = new ImageFolder();
                    imageFolder.setDir(dirPath);
                    imageFolder.setFirstImagePath(path);
                    mDirPaths.add(imageFolder);
                    tmpDir.put(dirPath, mDirPaths.indexOf(imageFolder));
                } else {
                    imageFolder = mDirPaths.get(tmpDir.get(dirPath));
                }

                imageFolder.images.add(new ImageItem(path));

            } while (mCursor.moveToNext());
            mCursor.close();

            for (int i = 0; i < mDirPaths.size(); i++) {
                ImageFolder folder = mDirPaths.get(i);
                Log.d(TAG, i + "-----" + folder.getName() + "---" + folder.images.size());
            }
            tmpDir = null;
        }

    }

    private void initPopupWindow() {

        View view = LayoutInflater.from(context).inflate(R.layout.check_img_list_dir, null);

        listPictureDirPopupWindow = new ListPictureDirPopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
                mScreenHeight / 10 * 7, true, mDirPaths);
    }

    /**
     * 初始化方法
     */
    private void initView() {

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        mScreenHeight = dm.heightPixels;

        mContentResolver = getContentResolver();
        loader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.ic_launcher)
                .showImageForEmptyUri(R.drawable.ic_launcher).showImageOnFail(R.drawable.ic_launcher)
                .cacheInMemory(true).cacheOnDisk(true).considerExifParams(true)
                .imageScaleType(ImageScaleType.EXACTLY).bitmapConfig(Bitmap.Config.RGB_565).build();

        imageAll = new ImageFolder();
        imageAll.setDir("/所有图片");
        currentImageFolder = imageAll;
        mDirPaths.add(imageAll);

        back = (ImageView) findViewById(R.id.selectPictureBack);
        complete = (TextView) findViewById(R.id.selectPictureComplete);

        selectImage = (GridView) findViewById(R.id.selectPicture);
        allPicture = (TextView) findViewById(R.id.selectPictureAll);

    }

    class ListPictureDirPopupWindow extends BasePopupWindowForListView<ImageFolder> {

        private ListView mListDir;
        //写ListView的Adapter

        public ListPictureDirPopupWindow(View contentView, int width, int height,
                                         boolean focusable, List<ImageFolder> data) {
            super(contentView, width, height, focusable, data);
        }

        public ListView getListView() {
            return mListDir;
        }

        @Override
        protected void beforeInitWeNeedSomeParams(Object... params) {

        }

        @Override
        public void initViews() {
            mListDir = (ListView) findViewById(R.id.id_list_dir);
        }

        @Override
        public void initEvents() {
            mListDir.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            });
        }

        @Override
        public void init() {

        }
    }

}
