package com.example.toutiao.module.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.toutiao.R;
import com.example.toutiao.RxBus;
import com.example.toutiao.adapter.base.BasePagerAdapter;
import com.example.toutiao.bean.news.NewsChannelBean;
import com.example.toutiao.database.dao.NewsChannelDao;
import com.example.toutiao.module.joke.content.JokeContentView;
import com.example.toutiao.module.news.article.NewsArticleView;
import com.example.toutiao.module.wenda.article.WendaArticleView;
import com.example.toutiao.util.L;
import com.example.toutiao.util.SettingUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by zhangjinbo on 17-8-25.
 */

public class NewsTabLayout extends Fragment {

    private static final String thiz = NewsTabLayout.class.getSimpleName();

    private ViewPager viewPager;
    private LinearLayout linearLayout;

    private NewsChannelDao dao = new NewsChannelDao();

    private List<Fragment> fragmentList;


    private static NewsTabLayout instance = null;
    private ArrayList<String> titleList;
    private BasePagerAdapter adapter;
    private Observable<Boolean> observable;

    public static NewsTabLayout getInstance() {
        if (instance == null) {
            instance = new NewsTabLayout();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        L.d(thiz, "onCreateView()!");

        View view = inflater.inflate(R.layout.fragment_news_tab, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        TabLayout tab_layout = view.findViewById(R.id.tab_layout_news);
        viewPager = view.findViewById(R.id.view_pager_news);

        tab_layout.setupWithViewPager(viewPager);
        tab_layout.setTabMode(TabLayout.MODE_SCROLLABLE);

        ImageView add_channel_iv = view.findViewById(R.id.add_channel_iv);
        add_channel_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        linearLayout = view.findViewById(R.id.header_layout);
        linearLayout.setBackgroundColor(SettingUtil.getInstance().getColor());
    }

    @Override
    public void onResume() {
        super.onResume();
        linearLayout.setBackgroundColor(SettingUtil.getInstance().getColor());
    }

    private void initData() {
        initTabs();
        adapter = new BasePagerAdapter(getChildFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(15);

        observable = RxBus.getInstance().register(NewsTabLayout.thiz);
        observable.subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean isRefresh) throws Exception {
                if(isRefresh) {
                    initTabs();
                    adapter.recreateItems(fragmentList, titleList);
                }
            }
        });
    }

    private void initTabs() {
        List<NewsChannelBean> channelList = dao.query(1);

        fragmentList = new ArrayList<Fragment>();
        titleList = new ArrayList<String>();

        if (channelList.size() == 0) {
            dao.addInitData();
            channelList = dao.query(1);
        }
        L.d(thiz, "channel list size: " + channelList.size());
        for (NewsChannelBean bean : channelList) {
            if (bean.getChannelId().equals("essay_joke")) {
                L.d(thiz, "essay joke");
                fragmentList.add(JokeContentView.newInstance());
            } else if (bean.getChannelId().equals("question_and_answer")) {
                L.d(thiz, "question and answer");
                fragmentList.add(WendaArticleView.newInstance());
            } else {
                L.d(thiz, "add NewsArticleView");
                fragmentList.add(NewsArticleView.newInstance(bean.getChannelId()));
            }
            titleList.add(bean.getChannelName());
        }
    }


    @Override
    public void onDestroy() {
        RxBus.getInstance().unregister(NewsTabLayout.thiz, observable);
        if(instance != null) {
            instance = null;
        }
        super.onDestroy();
    }
}
