package com.example.toutiao.module.news.article;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;

import com.example.toutiao.Register;
import com.example.toutiao.adapter.DiffCallback;
import com.example.toutiao.bean.LoadingBean;
import com.example.toutiao.module.base.BaseListFragment;
import com.example.toutiao.util.OnLoadMoreListener;

import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

import static android.R.id.list;

/**
 * Created by zhangjinbo on 17-8-29.
 */

public class NewsArticleView extends BaseListFragment<INewsArticle.Presenter> implements INewsArticle.View {
    private static final String TAG = "NewsArticleView";
    private String categoryId;

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public static NewsArticleView newInstance(String categoryId) {
        Bundle bundle = new Bundle();
        bundle.putString(TAG, categoryId);
        NewsArticleView view = new NewsArticleView();
        view.setArguments(bundle);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void initView(View view) {
        super.initView(view);
        adapter = new MultiTypeAdapter(oldItems);
        Register.registerNewsArticleItem(adapter);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new OnLoadMoreListener() {

            @Override
            public void onLoadMore() {
                if(canLoadMore) {
                    canLoadMore = false;
                    presenter.doLoadMoreData();
                }
            }
        });
    }

    @Override
    protected void fetchData() {
        super.fetchData();
        onLoadData();
    }

    @Override
    public void onSetAdapter(List<?> lsit) {
        Items newItems = new Items(list);
        newItems.add(new LoadingBean());
        DiffCallback.notifyDataSetChanged(oldItems, newItems, DiffCallback.MULTI_NEWS, adapter);
        oldItems.clear();
        oldItems.addAll(newItems);
        canLoadMore = true;
    }

    @Override
    public void setPresenter(INewsArticle.Presenter presenter) {
        if (null == presenter) {
            this.presenter = new NewsArticlePresenter(this);
        }
    }

    @Override
    public void onLoadData() {
        onShowLoading();
        presenter.doLoadData(categoryId);
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void initData() {
        categoryId = getArguments().getString(TAG);
    }
}
