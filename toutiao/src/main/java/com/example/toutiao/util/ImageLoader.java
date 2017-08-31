package com.example.toutiao.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by zhangjinbo on 17-8-30.
 */

public class ImageLoader {
    public static void loadCenterCrop(Context context, String url, ImageView view, int defaultResId) {
        if (SettingUtil.getInstance().getIsNoPhotoMode() && NetWorkUtil.isNetworkConnected(context)) {
            view.setImageResource(defaultResId);
        } else {
            RequestOptions options = new RequestOptions().centerCrop();
            Glide.with(context)
                    .load(url)
                    .transition(new DrawableTransitionOptions().crossFade())
                    .apply(options)
                    .into(view);
        }
    }

    /**
     * 带加载异常图片
     */
    public static void loadCenterCrop(Context context, String url, ImageView view, int defaultResId, int errorResId) {
        if (SettingUtil.getInstance().getIsNoPhotoMode() && NetWorkUtil.isMobileConnected(context)) {
            view.setImageResource(defaultResId);
        } else {
            //Glide 4.0写法
            RequestOptions options = new RequestOptions().centerCrop().error(errorResId);
            Glide.with(context)
                    .load(url)
                    .transition(new DrawableTransitionOptions().crossFade())
                    .apply(options)
                    .into(view);
        }
    }

}
