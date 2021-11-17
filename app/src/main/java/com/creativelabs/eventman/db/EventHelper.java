package com.creativelabs.eventman.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.creativelabs.eventman.entity.EventItemEntity;

public class EventHelper extends SQLiteOpenHelper {

    public final static int VERSION = 1;
    public final static String DB_NAME = "events.db";
    private SQLiteDatabase database;

    public EventHelper(@Nullable Context context, @Nullable SQLiteDatabase.CursorFactory factory) {
        super(context, DB_NAME, factory, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
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
