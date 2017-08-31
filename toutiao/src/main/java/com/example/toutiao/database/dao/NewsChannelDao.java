package com.example.toutiao.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.toutiao.InitApp;
import com.example.toutiao.R;
import com.example.toutiao.bean.news.NewsChannelBean;
import com.example.toutiao.database.DatabaseHelper;
import com.example.toutiao.database.table.NewsChannelTable;
import com.example.toutiao.util.L;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangjinbo on 17-8-29.
 */

public class NewsChannelDao {

    private static final String thiz = NewsChannelDao.class.getSimpleName();

    private SQLiteDatabase db;

    public NewsChannelDao() {
        this.db = DatabaseHelper.getDatabase();
    }

    public void addInitData() {
        String[] categoryId = InitApp.AppContext.getResources().getStringArray(R.array.mobile_news_id);
        String[] categoryName = InitApp.AppContext.getResources().getStringArray(R.array.mobile_news_name);
        for (int i=0; i< 8; i++) {
            add(categoryId[i], categoryName[i], 1, i);
        }

        for (int i = 8; i < categoryId.length; i++) {
            add(categoryId[i], categoryName[i], 0, i);
        }
    }

    private boolean add(String channelId, String channelName, int isEnable, int position) {
        ContentValues values = new ContentValues();
        values.put(NewsChannelTable.ID, channelId);
        values.put(NewsChannelTable.NAME, channelName);
        values.put(NewsChannelTable.IS_ENABLE, isEnable);
        values.put(NewsChannelTable.POSITION, position);
        long result = db.insert(NewsChannelTable.TABLENAME, null, values);
        return result != -1;
    }

    public List<NewsChannelBean> query(int isEnable) {
        Cursor cursor = db.query(NewsChannelTable.TABLENAME, null,
                NewsChannelTable.IS_ENABLE + "=?", new String[]{isEnable + ""}, null, null, null);

        L.d(thiz, "cursor count: " + cursor.getCount());

        List<NewsChannelBean> list = new ArrayList<NewsChannelBean>();
        while(cursor.moveToNext()) {
            NewsChannelBean bean = new NewsChannelBean();
            bean.setChannelId(cursor.getString(NewsChannelTable.ID_ID));
            bean.setChannelName(cursor.getString(NewsChannelTable.ID_NAME));
            bean.setIsEnable(cursor.getInt(NewsChannelTable.ID_ISENABLE));
            bean.setPosition(cursor.getInt(NewsChannelTable.ID_POSITION));
            list.add(bean);
        }
        cursor.close();
        return list;
    }

    public List<NewsChannelBean> queryAll() {
        Cursor cursor = db.query(NewsChannelTable.TABLENAME, null, null, null, null, null, null);
        List<NewsChannelBean> list = new ArrayList<NewsChannelBean>();
        while(cursor.moveToNext()) {
            NewsChannelBean bean = new NewsChannelBean();
            bean.setChannelId(cursor.getString(NewsChannelTable.ID_ID));
            bean.setChannelName(cursor.getString(NewsChannelTable.ID_NAME));
            bean.setIsEnable(cursor.getInt(NewsChannelTable.ID_ISENABLE));
            bean.setPosition(cursor.getInt(NewsChannelTable.ID_POSITION));
        }
        cursor.close();
        return list;
    }


    public boolean removeAll() {
        int result = db.delete(NewsChannelTable.TABLENAME, null, null);
        return result != -1;
    }

}
