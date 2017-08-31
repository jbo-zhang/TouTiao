package com.example.toutiao.module.base;

/**
 * Created by zhangjinbo on 17-8-29.
 */

public interface IBasePresenter {
    /**
     * 刷新数据
     */
    void doRefresh();

    /**
     * 显示网络错误
     */
    void doShowNetError();
}
