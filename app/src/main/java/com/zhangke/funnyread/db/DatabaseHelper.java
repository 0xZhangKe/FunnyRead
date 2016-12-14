package com.zhangke.funnyread.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import com.zhangke.funnyread.common.Column;

/**
 * Created by ZhangKe at 2016/12/14
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context, int version){
        super(context, Column.DB_NAME, null, version);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists "+ Column.DB_ZHIHU_TABLE_NAME+"("
                + "id integer primary key autoincrement,"
                + "zhihu_id integer not null,"
                + "ga_prefix text,"
                + "type integer,"
                + "images text,"
                + "date text,"
                + "title text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
