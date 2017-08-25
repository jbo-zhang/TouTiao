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
import com.example.toutiao.util.SettingUtil;

/**
 * Created by zhangjinbo on 17-8-25.
 */

public class NewsTabLayout extends Fragment {

    private ViewPager viewPager;
    private LinearLayout linearLayout;


    private static NewsTabLayout instance = null;
    public static NewsTabLayout getInstance() {
        if(instance == null) {
            instance = new NewsTabLayout();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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

    private void initData() {
    }

}
