package com.example.toutiao.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.toutiao.InitApp;
import com.example.toutiao.database.table.MediaChannelTable;
import com.example.toutiao.database.table.NewsChannelTable;
import com.example.toutiao.database.table.SearchHistoryTable;

/**
 * Created by zhangjinbo on 17-8-29.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String DB_NAME = "Toutiao";
    private static final int DB_VERSION = 5;
    private static final String CLEAR_TABLE_DATA = "delete from ";
    private static final String DROP_TABLE = "drop table if exists ";
    private static DatabaseHelper instance = null;
    private static SQLiteDatabase db = null;

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    private static synchronized DatabaseHelper getInstance() {
        if(instance == null) {
            instance = new DatabaseHelper(InitApp.AppContext, DB_NAME, null, DB_VERSION);
        }
        return instance;
    }

    public static SQLiteDatabase getDatabase() {
        if(db == null) {
            db = getInstance().getWritableDatabase();
        }
        return db;
    }

    public static synchronized void closeDatabase() {
        if(db != null) {
            db.close();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NewsChannelTable.CREATE_TABLE);
        db.execSQL(MediaChannelTable.CREATE_TABLE);
        db.execSQL(SearchHistoryTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch(oldVersion) {
            case 1:
                db.execSQL(MediaChannelTable.CREATE_TABLE);
                break;
            case 2:
                db.execSQL(CLEAR_TABLE_DATA + NewsChannelTable.TABLENAME);
                break;
            case 3:
                ContentValues values = new ContentValues();
                values.put(NewsChannelTable.ID, "");
                values.put(NewsChannelTable.NAME, "推荐");
                values.put(NewsChannelTable.IS_ENABLE, 0);
                values.put(NewsChannelTable.POSITION, 46);
                db.insert(NewsChannelTable.TABLENAME, null, values);
                break;
            case 4:
                db.execSQL(SearchHistoryTable.CREATE_TABLE);
                break;
        }
    }
}
