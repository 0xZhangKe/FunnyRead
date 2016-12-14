package com.zhangke.funnyread.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.zhangke.funnyread.ZhiHu.entity.ZhiHuDiaryEntity;
import com.zhangke.funnyread.common.Column;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangKe at 2016/12/14
 */
public class ZhiHuDBHelper {
    private static ZhiHuDBHelper mInstance = new ZhiHuDBHelper();

    public static ZhiHuDBHelper getInstance() {
        return mInstance;
    }

    private ZhiHuDBHelper() {
    }

    public List<ZhiHuDiaryEntity.Stories> getZhiHuDiaryBefore(Context context, String date){
        List<ZhiHuDiaryEntity.Stories> stories = new ArrayList<>();
        DatabaseHelper databaseHelper = new DatabaseHelper(context, Column.DB_VERSION);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(Column.DB_ZHIHU_TABLE_NAME,
                new String[]{"zhihu_id","ga_prefix","type","images","date","title"},"date=?",new String[]{date},
                null,null,null,null);
        while(cursor.moveToNext()){
            ZhiHuDiaryEntity.Stories story = new ZhiHuDiaryEntity.Stories();
            story.setId(cursor.getInt(0));
            story.setGa_prefix(cursor.getString(1));
            story.setType(cursor.getInt(2));

            List<String> images = new ArrayList<>();
            images.add(cursor.getString(3));
            story.setImages(images);
            story.setDate(cursor.getString(4));
            story.setTitle(cursor.getString(5));
            stories.add(story);
        }
        databaseHelper.close();
        sqLiteDatabase.close();
        return stories;
    }

    public void saveZhiHuDiaryBefore(Context context, List<ZhiHuDiaryEntity.Stories> storiesList, String date){
        DatabaseHelper databaseHelper = new DatabaseHelper(context, Column.DB_VERSION);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        try {
            for (ZhiHuDiaryEntity.Stories story : storiesList) {
                ContentValues values = new ContentValues();
                values.put("zhihu_id", story.getId());
                values.put("ga_prefix", story.getGa_prefix());
                values.put("type", story.getType());
                values.put("images", story.getImages().size() > 0 ? story.getImages().get(0) : "");
                values.put("date", date);
                values.put("title", story.getTitle());
                Log.e("TAG", "colum: " + sqLiteDatabase.insert(Column.DB_ZHIHU_TABLE_NAME, null, values));
            }
        } catch (Exception e){
            Log.e("TAG",e.toString());
            e.printStackTrace();
        }
        databaseHelper.close();
        sqLiteDatabase.close();
    }
}
