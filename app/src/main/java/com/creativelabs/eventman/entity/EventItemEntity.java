package com.creativelabs.eventman.entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.creativelabs.eventman.classes.EventItem;
import com.creativelabs.eventman.db.EventHelper;

import java.util.ArrayList;
import java.util.List;

public final class EventItemEntity implements BaseColumns {

    // id, name, desc, start date, start time
    private static final String TABLE_NAME = "events";
    private static final String ID = "id";
    private static final String NAME = "event_name";
    private static final String DESCRIPTION = "description";
    private static final String START_DATE = "start_date";
    private static final String START_TIME = "start_time";

    public static final String CREATE_SQL_QUERY = "CREATE TABLE " +
            TABLE_NAME + "(" +
            ID + "INTEGER PRIMARY KEY, " +
            NAME + "TEXT NOT NULL, " +
            DESCRIPTION + "TEXT NULL, " +
            START_DATE + "TEXT NOT NULL, " +
            START_TIME + "TEXT NOT NULL " +
            ")";

    public static final String DELETE_SQL_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;

    // CRUD - Create, Read, Update, Delete

    Context context;
    EventHelper helper;

    public EventItemEntity(Context context) {
        this.context = context;
        this.helper = new EventHelper(context, null);
    }

    public long create(EventItem eventItem) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, eventItem.getEventName());
        values.put(DESCRIPTION, eventItem.getEventDesc());
        values.put(START_DATE, eventItem.getStartDate());
        values.put(START_TIME, eventItem.getStartTime());
        return db.insert(TABLE_NAME, null, values);
    }

    public long update(EventItem eventItem, long id) {
        SQLiteDatabase db = helper.getWritableDatabase();

        String selection = ID + " = ? ";
        String[] selectionArgs = {id + ""};

        ContentValues values = new ContentValues();
        values.put(NAME, eventItem.getEventName());
        values.put(DESCRIPTION, eventItem.getEventDesc());
        values.put(START_DATE, eventItem.getStartDate());
        values.put(START_TIME, eventItem.getStartTime());

        return db.update(TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
    }

    public List<EventItem> getAll() {
        SQLiteDatabase db = helper.getReadableDatabase();

        String[] projection = {
                ID,
                NAME,
                DESCRIPTION,
                START_DATE,
                START_TIME
        };

        Cursor cursor = db.query(TABLE_NAME,
                projection,
                null, null, null, null, null);

        List<EventItem> itemList = new ArrayList<>();

        if (cursor != null && cursor.isBeforeFirst()) {
            while (cursor.moveToNext()) {
                EventItem item = new EventItem();
                item.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID)));
                item.setEventName(cursor.getString(cursor.getColumnIndexOrThrow(NAME)));
                item.setEventDesc(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION)));
                item.setStartDate(cursor.getString(cursor.getColumnIndexOrThrow(START_DATE)));
                item.setStartTime(cursor.getString(cursor.getColumnIndexOrThrow(START_TIME)));
                itemList.add(item);
            }
        }
        return itemList;
    }
}
