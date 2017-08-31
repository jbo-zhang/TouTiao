package com.example.toutiao.module.news.article;

import com.example.toutiao.bean.news.MultiNewsArticleDataBean;
import com.example.toutiao.module.base.IBaseListView;
import com.example.toutiao.module.base.IBasePresenter;

import java.util.List;

/**
 * Created by zhangjinbo on 17-8-29.
 */

public interface INewsArticle {
    interface View extends IBaseListView<Presenter> {
        /**
         * 请求数据
         */
        void onLoadData();

        /**
         * 刷新
         */
        void onRefresh();
    }

    interface Presenter extends IBasePresenter{
        /**
         * 请求数据
         */
        void doLoadData(String... category);

        /**
         * 再次请求数据
         */
        void doLoadMoreData();

        /**
         * 设置适配器
         */
        void doSetAdapter(List<MultiNewsArticleDataBean> dataBean);

        /**
         * 加载完毕
         */
        void doShowNoMore();
    }



}
