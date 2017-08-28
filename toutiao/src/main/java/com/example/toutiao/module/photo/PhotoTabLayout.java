package com.example.toutiao.module.photo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.toutiao.R;

/**
 * Created by zhangjinbo on 17-8-28.
 */

public class PhotoTabLayout extends Fragment {
    private static PhotoTabLayout instance = null;

    public static PhotoTabLayout getInstance() {
        if(instance == null) {
            instance = new PhotoTabLayout();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_tab, container, false);
        initView(view);
        initData();
        return view;

    }

    private void initView(View view) {

    }

    private void initData() {

    }

}
