package com.elysey.daybook.base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataAgregator extends SQLiteOpenHelper {
    public DataAgregator(Context context, String name, CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    private static final String CREATE_TABLE_NOTES = "CREATE TABLE " + BaseUtil.NAME_BASE+ "(" +
            BaseUtil.NOTE_ID + " INTEGER PRIMARY KEY, " +
            BaseUtil.NOTE_TITLE + " TEXT, " +
            BaseUtil.NOTE_TEXT + " TEXT, " +
            BaseUtil.NOTE_TIME + " TEXT, " +
            BaseUtil.NOTE_DATE + " TEXT);";

    public static final String DATABASE_NAME = "agregator.db";
    private static final int DATABASE_VERSION = 1;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_NOTES);
    }

    public DataAgregator(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public long addDataBase(SQLiteDatabase db, String nameBase, ContentValues contentValues) {
        return db.insert(nameBase, null, contentValues);
    }

    public Cursor getDataBase(SQLiteDatabase db, String baseName) {
        Cursor c = db.query(baseName, null, null, null, null, null, null);
        return c;
    }

    public void clearData(SQLiteDatabase db, String baseName) {
        db.delete(baseName, null, null);

    }

    public Cursor getRow(SQLiteDatabase db,  String whereArg) {
        Cursor c = db.rawQuery("SELECT "
                + "*"
                + " FROM "
                + BaseUtil.NAME_BASE
                + " WHERE "
                + BaseUtil.NOTE_ID
                + "="
                + whereArg, null);
        return c;
    }

    public void deleteRow(SQLiteDatabase db,  String whereArg) {
        db.delete (BaseUtil.NAME_BASE, BaseUtil.NOTE_ID+"=" + whereArg, null);
    }


}
