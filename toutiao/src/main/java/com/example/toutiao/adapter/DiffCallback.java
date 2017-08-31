package com.example.toutiao.adapter;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by zhangjinbo on 17-8-29.
 */

public class DiffCallback extends DiffUtil.Callback{
    public static final int JOKE = 1;
    public static final int PHOTO = 2;
    public static final int NEWS_COMMENT = 5;
    public static final int JOKE_COMMENT = 6;
    public static final int MULTI_NEWS = 7;
    public static final int WENDA_ARTICLE = 8;
    public static final int WENDA_CONTENT = 9;
    public static final int SEARCH = 10;
    public static final int MULTI_MEDIA = 11;
    public static final int MEDIA_WENDA = 12;

    private List oldList, newList;
    private int type;

    public DiffCallback(List oldList, List newList, int type) {
        this.oldList = oldList;
        this.newList = newList;
        this.type = type;
    }


    public static void notifyDataSetChanged(List oldList, List newList, int type, RecyclerView.Adapter adapter) {
        DiffCallback diffCallback = new DiffCallback(oldList, newList, type);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(diffCallback, true);
        result.dispatchUpdatesTo(adapter);
    }

    @Override
    public int getOldListSize() {
        return 0;
    }

    @Override
    public int getNewListSize() {
        return 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }
}
