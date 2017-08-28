package com.example.toutiao.module.media.channel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.toutiao.R;
import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * Created by zhangjinbo on 17-8-28.
 */

public class MediaChannelView extends RxFragment {
    private static MediaChannelView instance = null;

    public static MediaChannelView getInstance() {
        if(instance == null) {
            instance = new MediaChannelView();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_media, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {

    }

    private void initData() {

    }
}
