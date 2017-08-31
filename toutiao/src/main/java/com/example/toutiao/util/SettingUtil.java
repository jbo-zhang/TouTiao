package com.example.toutiao.util;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;

import com.example.toutiao.InitApp;
import com.example.toutiao.R;

import static android.R.attr.textSize;

/**
 * Created by zhangjinbo on 17-8-25.
 */

public class SettingUtil {

    private SharedPreferences setting = PreferenceManager.getDefaultSharedPreferences(InitApp.AppContext);

    public static SettingUtil getInstance() {
        return SettingUtilInstance.instance;
    }


    /**
     * 获取主题颜色
     */
    public int getColor() {
        int defaultColor = InitApp.AppContext.getResources().getColor(R.color.colorPrimary);
        int color = setting.getInt("color", defaultColor);
        if ((color != 0) && Color.alpha(color) != 255) {
            return defaultColor;
        }

        return color;
    }

    /**
     * 获取图标值
     * @return
     */
    public int getCustomIconValue() {
        String s = setting.getString("custom_icon", "0");
        return Integer.parseInt(s);
    }

    /**
     * 获取滑动返回值
     * @return
     */
    public int getSlidable() {
        String s = setting.getString("slidable", "1");
        return Integer.parseInt(s);
    }

    /**
     * 获取是否开启导航栏上色
     * @return
     */
    public boolean getNavBar() {
        return setting.getBoolean("nav_bar", false);
    }

    /**
     * 获取是否开启无图模式
     */
    public boolean getIsNoPhotoMode() {
        return setting.getBoolean("switch_noPhotoMode", false) && NetWorkUtil.isMobileConnected(InitApp.AppContext);
    }

    /**
     * 获取字体大小
     */
    public int getTextSize() {
        return setting.getInt("textsize", 16);
    }


    private static final class SettingUtilInstance {
        private static final SettingUtil instance = new SettingUtil();
    }

}
