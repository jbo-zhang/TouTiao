package com.example.toutiao.util;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by zhangjinbo on 17-8-30.
 */

public abstract class OnLoadMoreListener extends RecyclerView.OnScrollListener {
    private static final String thiz = OnLoadMoreListener.class.getSimpleName();

    private LinearLayoutManager layoutManager;
    private int itemCount;
    private int lastPosition;
    private int lastItemCount;

    public abstract void onLoadMore();

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if(recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            itemCount = layoutManager.getItemCount();
            lastPosition = layoutManager.findLastCompletelyVisibleItemPosition();
        } else {
            L.d(thiz, "The OnLoadMoreListener only support LinearLayoutManager");
            return;
        }

        if(lastItemCount != itemCount && lastPosition == itemCount - 1) {
            lastItemCount = itemCount;
            this.onLoadMore();
        }

    }
}
