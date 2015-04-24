package com.minglang.suiuu.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.lidroid.xutils.BitmapUtils;
import com.minglang.suiuu.R;

import java.util.List;

/**
 * Created by Administrator on 2015/4/23.
 */
public class ShowGVPictureAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    private ImageView iv_picture;

    public ShowGVPictureAdapter(Context context,List<String> list) {
        super();
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size()+1;
    }

    @Override
    public Object getItem(int position) {

        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BitmapUtils bitmapUtils = new BitmapUtils(context);
        View view = View.inflate(context, R.layout.adapter_show_picture, null);
        iv_picture = (ImageView)view.findViewById(R.id.iv_picture);
        Log.i("suiuu","lai到这儿了吗");
        if(position>=list.size()) {
            iv_picture.setImageResource(R.drawable.icon_suiuu2);
        }else {
            bitmapUtils.display(iv_picture, list.get(position));
        }
        return view;
    }
}
