package com.hongyangdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.hongyangdemo.R;
import com.hongyangdemo.others.ImageLoader;

import java.util.List;

/**
 * author： xiongdejin
 * date: 2017/8/31
 * describe:
 */

public class MyAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mData;
    private String mDirPath;
    private LayoutInflater mInflater;
    private ImageLoader mImageLoader;

    public MyAdapter(Context context, List<String> mData, String dirPath) {
        this.mContext = context;
        this.mData = mData;
        this.mDirPath = dirPath;
        mInflater = LayoutInflater.from(mContext);
        mImageLoader = ImageLoader.getInstance();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.grid_item, parent, false);
            holder.mImageView = (ImageView) convertView.findViewById(R.id.id_item_image);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mImageView.setImageResource(R.drawable.friends_sends_pictures_no);
        //使用Imageloader去加载图片
        mImageLoader.loadImage(mDirPath + "/" + mData.get(position), holder.mImageView);
        return convertView;
    }

    private final class ViewHolder {
        ImageView mImageView;
    }
}
