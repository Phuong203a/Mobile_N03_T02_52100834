package com.example.bai3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.google.android.material.tabs.TabLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class DbHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "eventManager";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "events";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PLACE = "place";
    private static final String KEY_DATETIME = "datetime";

    public DbHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_students_table = String.format(
                        "CREATE TABLE %s(" +
                        "%s INTEGER PRIMARY KEY, " +
                        "%s TEXT, " +
                        "%s TEXT, " +
                        "%s TEXT)",
                        TABLE_NAME, KEY_ID, KEY_NAME, KEY_PLACE, KEY_DATETIME
        );
        db.execSQL(create_students_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String drop_students_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(drop_students_table);

        onCreate(db);
    }

    public void addItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, item.getName());
        values.put(KEY_PLACE, item.getPlace());
        values.put(KEY_DATETIME, item.getDateTime());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Item> getAllItems() {
        List<Item> itemList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String place = cursor.getString(2);
            String datetime = cursor.getString(3);

            Calendar calendar = Calendar.getInstance();
            try {
                calendar.setTime(sdf.parse(datetime));
            }
            catch (ParseException p) {
                calendar.setTime(new Date(2001, 1, 7));
            }

            Item item = new Item(name, place, calendar);
            item.setId(id);

            itemList.add(item);
            cursor.moveToNext();
        }
        return itemList;
    }

    public void deleteItem(int itemID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?", new String[] { String.valueOf(itemID) });
        db.close();
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME);
        db.close();
    }
}
