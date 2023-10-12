package com.example.lab5_ex2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "lab6Ex3";
    private static final String TABLE_NAME = "sukien";
    //columns name
    public static final String KEY_ID = "_id";
    public static final String KEY_TEN = "ten";
    public static final String KEY_PHONG = "phong";
    public static final String KEY_NGAY = "ngay";
    public static final String KEY_GIO = "gio";
    public static final String KEY_TRANGTHAI = "trangthai";
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            KEY_ID + " INTEGER PRIMARY KEY, " + KEY_TEN + " TEXT, " + KEY_PHONG + " TEXT, " +
            KEY_NGAY + " TEXT, " + KEY_GIO + " TEXT, " + KEY_TRANGTHAI + " TEXT );";
    public MySQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String drop_sukien_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        sqLiteDatabase.execSQL(drop_sukien_table);

        onCreate(sqLiteDatabase);
    }

    public ArrayList<SuKien> getAllSuKien(){
        ArrayList<SuKien> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        while(cursor.moveToNext()){
            String ten = cursor.getString(cursor.getColumnIndex(KEY_TEN));
            String phong = cursor.getString(cursor.getColumnIndex(KEY_PHONG));
            String ngay = cursor.getString(cursor.getColumnIndex(KEY_NGAY));
            String gio = cursor.getString(cursor.getColumnIndex(KEY_GIO));
            String trangthai = cursor.getString(cursor.getColumnIndex(KEY_TRANGTHAI));
            list.add(new SuKien(ten,phong,ngay,gio,trangthai));
        }
        db.close();
        return list;
    }
    public void addSuKien(SuKien suKien){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_GIO, suKien.getGio());
        values.put(KEY_TEN, suKien.getTen());
        values.put(KEY_NGAY, suKien.getNgay());
        values.put(KEY_PHONG, suKien.getPhong());
        values.put(KEY_TRANGTHAI, suKien.getTrangthai());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }
}
