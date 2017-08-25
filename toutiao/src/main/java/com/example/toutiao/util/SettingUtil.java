package com.example.toutiao.util;

import com.example.toutiao.InitApp;
import com.example.toutiao.R;

/**
 * Created by zhangjinbo on 17-8-25.
 */

public class SettingUtil {

    public static SettingUtil getInstance() {
        return SettingUtilInstance.instance;
    }

    private static final class SettingUtilInstance {
        private static final SettingUtil instance = new SettingUtil();
    }

    /**
     * 获取主题颜色
     */
    public int getColor() {
        int defaultColor = InitApp.AppContext.getResources().getColor(R.color.colorPrimary);

        //TODO

        return defaultColor;
    }

}
