package com.example.toutiao.module.news.article;


import android.text.TextUtils;

import com.example.toutiao.ErrorAction;
import com.example.toutiao.RetrofitFactory;
import com.example.toutiao.api.IMobileNewsApi;
import com.example.toutiao.bean.news.MultiNewsArticleBean;
import com.example.toutiao.bean.news.MultiNewsArticleDataBean;
import com.example.toutiao.util.TimeUnit;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zhangjinbo on 17-8-29.
 */

class NewsArticlePresenter implements INewsArticle.Presenter {

    private List<MultiNewsArticleDataBean> dataList = new ArrayList<>();

    private Gson gson = new Gson();
    private Random random = new Random();

    private INewsArticle.View view;
    private String time;
    private String category;


    public NewsArticlePresenter(INewsArticle.View view) {
        this.view = view;
        this.time = TimeUnit.getCurrentTimeStamp();
    }

    @Override
    public void doRefresh() {

    }


    @Override
    public void doLoadData(String... category) {
        if (this.category == null) {
            this.category = category[0];
        }

        //释放内存
        if (dataList.size() > 150) {
            dataList.clear();
        }

        getRandom()
                .subscribeOn(Schedulers.io())
                .switchMap(new Function<MultiNewsArticleBean, ObservableSource<MultiNewsArticleDataBean>>() {
                    @Override
                    public ObservableSource<MultiNewsArticleDataBean> apply(@NonNull MultiNewsArticleBean multiNewsArticleBean) throws Exception {
                        List<MultiNewsArticleDataBean> dataList = new ArrayList<>();
                        for (MultiNewsArticleBean.DataBean dataBean : multiNewsArticleBean.getData()) {
                            dataList.add(gson.fromJson(dataBean.getContent(), MultiNewsArticleDataBean.class));
                        }
                        return Observable.fromIterable(dataList);
                    }
                })
                .filter(new Predicate<MultiNewsArticleDataBean>() {

                    @Override
                    public boolean test(@NonNull MultiNewsArticleDataBean dataBean) throws Exception {
                        time = dataBean.getBehot_time();
                        if (TextUtils.isEmpty(dataBean.getSource())) {
                            return false;
                        }

                        try {
                            //过滤头条问答新闻
                            if (dataBean.getSource().contains("头条问答")
                                    || dataBean.getTag().contains("ad")
                                    || dataBean.getSource().contains("悟空问答")) {
                                return false;
                            }
                            //过滤头条问答新闻
                            if (dataBean.getRead_count() == 0 || TextUtils.isEmpty(dataBean.getMedia_name())) {
                                String title = dataBean.getTitle();
                                if (title.lastIndexOf("？") == title.length() - 1) {
                                    return false;
                                }
                            }
                        } catch (NullPointerException e) {
                            ErrorAction.print(e);
                        }
                        //过滤重复新闻（与上次刷新的数据比较）
                        for(MultiNewsArticleDataBean bean : dataList) {
                            if(bean.getTitle().equals(dataBean.getTitle())) {
                                return false;
                            }
                        }
                        return true;
                    }
                })
                .toList()
                .map(new Function<List<MultiNewsArticleDataBean>, List<MultiNewsArticleDataBean>>() {
                    @Override
                    public List<MultiNewsArticleDataBean> apply(@NonNull List<MultiNewsArticleDataBean> list) throws Exception {
                        // 过滤重复新闻(与本次刷新的数据比较,因为使用了2个请求,数据会有重复)
                        for (int i = 0; i < list.size(); i++) {
                            for(int j = list.size() - 1; j > i; j--) {
                                if(list.get(j).getTitle().equals(list.get(i).getTitle())) {
                                    list.remove(j);
                                }
                            }
                        }
                        return list;
                    }
                })
                .compose(view.<List<MultiNewsArticleDataBean>>bindToLife())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<MultiNewsArticleDataBean>>() {
                    @Override
                    public void accept(@NonNull List<MultiNewsArticleDataBean> list) throws Exception {
                        if (null != list && list.size() > 0) {
                            doSetAdapter(list);
                        } else {
                            doShowNoMore();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        doShowNetError();
                        ErrorAction.print(throwable);
                    }
                });

    }

    @Override
    public void doLoadMoreData() {
        doLoadData();
    }

    @Override
    public void doSetAdapter(List<MultiNewsArticleDataBean> list) {
        dataList.addAll(list);
        view.onSetAdapter(dataList);
        view.onHideLoading();
    }

    @Override
    public void doShowNoMore() {
        view.onHideLoading();
        view.onShowNoMore();
    }

    @Override
    public void doShowNetError() {
        view.onHideLoading();
        view.onShowNetError();
    }


    public Observable<MultiNewsArticleBean> getRandom() {
        int i = random.nextInt(10);
        if (i % 2 == 0) {
            Observable<MultiNewsArticleBean> ob1 = RetrofitFactory.getRetrofit().create(IMobileNewsApi.class)
                    .getNewsArticle(this.category, this.time);
            return ob1;
        } else {
            Observable<MultiNewsArticleBean> ob2 = RetrofitFactory.getRetrofit().create(IMobileNewsApi.class)
                    .getNewsArticle2(this.category, this.time);
            return ob2;
        }
    }
}
