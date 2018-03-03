package com.example.lenovo_pc.toeicvocal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo-PC on 3/1/2018.
 */

public class DatabaseManager {
    private static final String TABLE_TOPIC = "tbl_topic";
    private static final String TABLE_WORD = "tbl_word";
    private SQLiteDatabase sqLiteDatabase;
    private AssetsHelper assetsHelper;
    private static DatabaseManager databaseManager;

    public static DatabaseManager getInstance(Context context) {
        if (databaseManager == null) {
            databaseManager = new DatabaseManager(context);
        }
        return databaseManager;
    }

    public DatabaseManager(Context context) {
        assetsHelper = new AssetsHelper(context);
    }

    public List<TopicModel> getListTopic() {
        sqLiteDatabase = assetsHelper.getReadableDatabase();
        List<TopicModel> topicModels = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_TOPIC, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String imageUrl = cursor.getString(3);
            String category = cursor.getString(4);
            String color = cursor.getString(5);
            String lastTime = cursor.getString(6);

            TopicModel topicModel = new TopicModel(id, name, imageUrl, category, color, lastTime);
            topicModels.add(topicModel);
            cursor.moveToNext();
        }
        Log.d("abc", "getListTopic: " + topicModels);
        return topicModels;
    }
}
