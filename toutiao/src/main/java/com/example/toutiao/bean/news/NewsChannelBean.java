package com.example.toutiao.bean.news;

import android.support.annotation.NonNull;

/**
 * Created by zhangjinbo on 17-8-29.
 */

public class NewsChannelBean implements Comparable<NewsChannelBean> {
    private String channelId;
    private String channelName;
    private int isEnable;
    private int position;

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public void setIsEnable(int isEnable) {
        this.isEnable = isEnable;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getChannelId() {
        return channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public int getIsEnable() {
        return isEnable;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }

        NewsChannelBean bean = (NewsChannelBean) o;

        if(isEnable != bean.isEnable) {
            return false;
        }
        if(position != bean.position) {
            return false;
        }
        if(channelId != null ? channelId.equals(bean.channelId) : bean.channelId != null) {
            return false;
        }

        return channelName != null ? channelName.equals(bean.channelName) : bean.channelName == null;
    }

    @Override
    public int hashCode() {
        int result = channelId != null ? channelId.hashCode() : 0;
        result = 31 * result + (channelName != null ? channelName.hashCode() : 0);
        result = 31 * result + isEnable;
        result = 31 * result + position;
        return result;

    }

    @Override
    public int compareTo(@NonNull NewsChannelBean o) {
        return this.position - o.getPosition();
    }
}
