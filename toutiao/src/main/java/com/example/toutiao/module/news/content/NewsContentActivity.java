package com.example.toutiao.module.news.content;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.example.toutiao.InitApp;
import com.example.toutiao.R;
import com.example.toutiao.bean.news.MultiNewsArticleDataBean;
import com.example.toutiao.module.base.BaseActivity;

/**
 * Created by zhangjinbo on 17-8-31.
 */

public class NewsContentActivity extends BaseActivity {

    private static final String TAG = "NewsContentActivity";
    private static final String IMG = "img";

    public static void launch(MultiNewsArticleDataBean bean) {
        InitApp.AppContext.startActivity(new Intent(InitApp.AppContext, NewsContentActivity.class)
                .putExtra(TAG, bean).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    public static void launch(MultiNewsArticleDataBean bean, String imgUrl) {
        InitApp.AppContext.startActivity(new Intent(InitApp.AppContext, NewsContentActivity.class)
                .putExtra(TAG, bean).putExtra(IMG, imgUrl).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.container);
        Intent intent = getIntent();
    }
}
