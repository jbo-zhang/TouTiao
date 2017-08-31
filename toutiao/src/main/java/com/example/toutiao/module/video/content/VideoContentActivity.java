package com.example.toutiao.module.video.content;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.example.toutiao.InitApp;
import com.example.toutiao.R;
import com.example.toutiao.bean.news.MultiNewsArticleDataBean;
import com.example.toutiao.module.base.BaseActivity;

/**
 * Created by zhangjinbo on 17-8-31.
 */

public class VideoContentActivity extends BaseActivity {
    public static final String TAG = "VideoContentActivity";
    public static void launch(MultiNewsArticleDataBean bean) {
        InitApp.AppContext.startActivity(new Intent(InitApp.AppContext, VideoContentActivity.class)
                .putExtra(VideoContentActivity.TAG, bean)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.fragment_video_content_new);
    }

}
