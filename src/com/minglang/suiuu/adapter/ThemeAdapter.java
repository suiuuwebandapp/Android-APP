package com.minglang.suiuu.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.minglang.suiuu.entity.ThemeInfo;

import java.util.List;

/**
 * Created by Administrator on 2015/4/21.
 */
public class ThemeAdapter extends BaseAdapter {

    private Context context;

    private List<ThemeInfo> list;

    public ThemeAdapter(Context context, List<ThemeInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
