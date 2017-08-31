package com.example.toutiao.binder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.toutiao.R;
import com.example.toutiao.bean.LoadingEndBean;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by zhangjinbo on 17-8-31.
 */

public class LoadingEndViewBinder extends ItemViewBinder<LoadingEndBean, LoadingEndViewBinder.ViewHolder> {
    @NonNull
    @Override
    protected LoadingEndViewBinder.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_loading_end, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull LoadingEndViewBinder.ViewHolder holder, @NonNull LoadingEndBean item) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
