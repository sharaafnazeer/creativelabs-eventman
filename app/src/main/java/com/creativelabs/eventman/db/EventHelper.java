package com.creativelabs.eventman.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.creativelabs.eventman.entity.EventItemEntity;
import com.creativelabs.eventman.utils.Constants;

public class EventHelper extends SQLiteOpenHelper {


    private SQLiteDatabase database;

    public EventHelper(@Nullable Context context, @Nullable SQLiteDatabase.CursorFactory factory) {
        super(context, Constants.DB_NAME, factory, Constants.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("CREATE_QUERY", EventItemEntity.CREATE_SQL_QUERY);
        db.execSQL(EventItemEntity.CREATE_SQL_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL(EventItemEntity.DELETE_SQL_QUERY);
            db.execSQL(EventItemEntity.CREATE_SQL_QUERY);
        }
    }
}
