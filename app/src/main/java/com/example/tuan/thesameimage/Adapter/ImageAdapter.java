package com.example.tuan.thesameimage.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.tuan.thesameimage.R;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<Integer> idImageList;

    public ImageAdapter(Context context, int layout, ArrayList<Integer> idImageList) {
        this.context = context;
        this.layout = layout;
        this.idImageList = idImageList;
    }

    @Override
    public int getCount() {
        return idImageList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder {
        ImageView image;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        // in the first generation convertView == null
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout, null);

            viewHolder.image = convertView.findViewById(R.id.item_grid_image);
            convertView.setTag(viewHolder);
        }
        else  {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.image.setImageResource(idImageList.get(position));

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.grid_image);
        convertView.startAnimation(animation);
        return convertView;
    }
}
