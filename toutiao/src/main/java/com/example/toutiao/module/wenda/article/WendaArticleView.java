package com.example.toutiao.module.wenda.article;

import android.support.v4.app.Fragment;

import com.example.toutiao.module.base.BaseListFragment;

import java.util.List;

/**
 * Created by zhangjinbo on 17-8-31.
 */

public class WendaArticleView extends BaseListFragment{
    @Override
    public void onSetAdapter(List lsit) {

    }

    @Override
    public void setPresenter(Object presenter) {

    }

    @Override
    protected void initData() {

    }

    public static Fragment newInstance() {
        return new WendaArticleView();
    }
}
