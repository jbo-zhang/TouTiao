package com.example.toutiao.module.base;

import java.util.List;

/**
 * Created by zhangjinbo on 17-8-29.
 */

public interface IBaseListView<T> extends IBaseView<T> {
    /**
     * 设置适配器
     */
    void onSetAdapter(List<?> lsit);

    /**
     * 加载完毕
     */
    void onShowNoMore();
}
