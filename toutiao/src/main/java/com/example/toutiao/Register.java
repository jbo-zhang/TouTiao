package com.example.toutiao;

import android.support.annotation.NonNull;

import com.example.toutiao.bean.LoadingBean;
import com.example.toutiao.bean.LoadingEndBean;
import com.example.toutiao.bean.news.MultiNewsArticleDataBean;
import com.example.toutiao.binder.LoadingEndViewBinder;
import com.example.toutiao.binder.LoadingViewBinder;
import com.example.toutiao.binder.news.NewsArticleImgViewBinder;
import com.example.toutiao.binder.news.NewsArticleTextViewBinder;
import com.example.toutiao.binder.news.NewsArticleVideoViewBinder;

import me.drakeet.multitype.ClassLinker;
import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by zhangjinbo on 17-8-30.
 */

public class Register {

    public static void registerNewsArticleItem(MultiTypeAdapter adapter) {
        // 一个类型对应多个 ItemViewBinder
        adapter.register(MultiNewsArticleDataBean.class)
                .to(new NewsArticleImgViewBinder(),
                        new NewsArticleVideoViewBinder(),
                        new NewsArticleTextViewBinder())
                .withClassLinker(new ClassLinker<MultiNewsArticleDataBean>() {
                    @NonNull
                    @Override
                    public Class<? extends ItemViewBinder<MultiNewsArticleDataBean, ?>> index(@NonNull MultiNewsArticleDataBean item) {
                        if(item.isHas_video()) {
                            return NewsArticleVideoViewBinder.class;
                        }
                        if(null != item.getImage_list() && item.getImage_list().size() > 0) {
                            return NewsArticleImgViewBinder.class;
                        }
                        return NewsArticleTextViewBinder.class;
                    }
                });
        adapter.register(LoadingBean.class, new LoadingViewBinder());
        adapter.register(LoadingEndBean.class, new LoadingEndViewBinder());
    }
}
