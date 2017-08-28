package com.example.toutiao.util;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;

import com.example.toutiao.InitApp;
import com.example.toutiao.R;

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


    private static final class SettingUtilInstance {
        private static final SettingUtil instance = new SettingUtil();
    }

}
