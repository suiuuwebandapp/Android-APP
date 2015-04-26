package com.minglang.suiuu.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.minglang.suiuu.R;
import com.minglang.suiuu.customview.CircleImageView;
import com.minglang.suiuu.entity.LoopDetails;
import com.minglang.suiuu.entity.LoopDetailsData;
import com.minglang.suiuu.utils.ViewHolder;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.List;

/**
 * 圈子详情页数据适配器
 * <p/>
 * Created by Administrator on 2015/4/22.
 */
public class LoopDetailsAdapter extends BaseAdapter {

    private Context context;

    private LoopDetails loopDetails;

    private List<LoopDetailsData> list;

    private ImageLoader loader;

    private DisplayImageOptions displayImageOptions1, displayImageOptions2;

    public LoopDetailsAdapter(Context context, LoopDetails loopDetails, List<LoopDetailsData> loopDetailsDataList) {
        this.context = context;
        this.loopDetails = loopDetails;
        this.list = loopDetailsDataList;

        loader = ImageLoader.getInstance();
        loader.init(ImageLoaderConfiguration.createDefault(context));

        displayImageOptions1 = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.default_other_user_loop_image)
                .showImageForEmptyUri(R.drawable.default_other_user_loop_image).showImageOnFail(R.drawable.default_other_user_loop_image)
                .cacheInMemory(true).cacheOnDisk(true).considerExifParams(true)
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED).bitmapConfig(Bitmap.Config.RGB_565).build();

        displayImageOptions2 = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.user_head_image_bg)
                .showImageForEmptyUri(R.drawable.user_head_image_bg).showImageOnFail(R.drawable.user_head_image_bg)
                .cacheInMemory(true).cacheOnDisk(true).considerExifParams(true)
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED).bitmapConfig(Bitmap.Config.RGB_565).build();
    }

    @Override
    public int getCount() {
        if (list != null && list.size() > 0) {
            return list.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        if (list != null && list.size() > 0) {
            return list.get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LoopDetailsData loopDetailsData = list.get(position);

        ViewHolder holder = ViewHolder.get(context, convertView, parent, R.layout.item_loop_details, position);

        ImageView mainImageView = holder.getView(R.id.item_loop_details_image);
        CircleImageView headImage = holder.getView(R.id.item_loop_details_head_image);
        int height = BitmapFactory.decodeResource(context.getResources(), R.drawable.user_head_image_bg).getHeight();

        TextView userName = holder.getView(R.id.item_loop_details_user_name);

        TextView title = holder.getView(R.id.item_loop_details_title);
        TextView praise = holder.getView(R.id.item_loop_details_praise);
        TextView comments = holder.getView(R.id.item_loop_details_comments);

//        Bitmap bitmap = loader.loadImageSync(loopDetailsData.getaImg(), displayImageOptions1);
//        mainImageView.setBackgroundDrawable(new BitmapDrawable(bitmap));
        //加载主图片
        loader.displayImage(loopDetailsData.getaImg(), mainImageView, displayImageOptions1);

        RelativeLayout.LayoutParams mainParams = new RelativeLayout.LayoutParams(mainImageView.getLayoutParams());
        mainParams.setMargins(0, 0, 0, height / 2);
        mainImageView.setLayoutParams(mainParams);

        //加载头像
        loader.displayImage(loopDetailsData.getHeadImg(), headImage, displayImageOptions2);

        //加载用户名
        String str_userName = loopDetailsData.getNickname();
        userName.setText(str_userName);

        //加载标题
        String str_title = loopDetailsData.getaTitle();
        title.setText(str_title);

        //加载评论数
        String comments_str = loopDetailsData.getaCmtCount();
        comments.setText(comments_str);

        //加载被赞数
        String praise_str = loopDetailsData.getaSupportCount();
        praise.setText(praise_str);

        return holder.getConvertView();
    }
}
