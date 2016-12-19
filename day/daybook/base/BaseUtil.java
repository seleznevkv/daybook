package com.elysey.daybook.base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.elysey.daybook.model.Note;

import java.util.ArrayList;

public class BaseUtil {

    final public static String NAME_BASE = "notes";
    final public static String NOTE_ID = "noteID";
    final public static String NOTE_TITLE = "noteTitle";
    final public static String NOTE_TEXT = "noteText";
    final public static String NOTE_TIME= "noteTime";
    final public static String NOTE_DATE = "noteDate";


    public static long addBase(Context context, String nameBase, ContentValues cv) {
        DataAgregator sql = new DataAgregator(context);
        SQLiteDatabase db = sql.getReadableDatabase();
        long id = sql.addDataBase(db, nameBase, cv);
        db.close();
        sql.close();
        return id;

    }

    public static void clearBase(Context context, String nameBase) {
        DataAgregator sql = new DataAgregator(context);
        SQLiteDatabase db = sql.getReadableDatabase();
        sql.clearData(db, nameBase);
        db.close();
        sql.close();
    }

    public static long updateBase(Context context, String nameBase, String tableID, ContentValues cv, int idUpdate) {
        DataAgregator sql = new DataAgregator(context);
        SQLiteDatabase db = sql.getReadableDatabase();
        int updCount = db.update(nameBase, cv, tableID + " = ?", new String[]{idUpdate + ""});
        db.close();
        sql.close();
        return idUpdate;

    }

    public static ArrayList<Note> getNotes(Context context, String baseName) {
        DataAgregator sql = new DataAgregator(context);
        SQLiteDatabase db = sql.getReadableDatabase();
        Cursor c = sql.getDataBase(db, baseName);

        ArrayList<Note> events = new ArrayList<>();

        if (c.moveToFirst()) {
            do {
                Note note = new Note(
                        c.getInt(c.getColumnIndex(BaseUtil.NOTE_ID)),
                        c.getString(c.getColumnIndex(BaseUtil.NOTE_TITLE)),
                        c.getString(c.getColumnIndex(BaseUtil.NOTE_TEXT)),
                        c.getString(c.getColumnIndex(BaseUtil.NOTE_TIME)),
                        c.getString(c.getColumnIndex(BaseUtil.NOTE_DATE)));

                events.add(note);

            } while (c.moveToNext());
        }

        c.close();
        db.close();
        sql.close();
        return events;
    }

    public static Note getNote(Context context, String id) {
        DataAgregator sql = new DataAgregator(context);
        SQLiteDatabase db = sql.getReadableDatabase();
        Cursor c = sql.getRow(db, id);
        Note note = null;
        if (c.moveToFirst()) {
            note = new Note(
                    c.getInt(c.getColumnIndex(BaseUtil.NOTE_ID)),
                    c.getString(c.getColumnIndex(BaseUtil.NOTE_TITLE)),
                    c.getString(c.getColumnIndex(BaseUtil.NOTE_TEXT)),
                    c.getString(c.getColumnIndex(BaseUtil.NOTE_TIME)),
                    c.getString(c.getColumnIndex(BaseUtil.NOTE_DATE)));
        }

        c.close();
        db.close();
        sql.close();
        return note;
    }

    public static void deleteRow(Context context, String id) {
        DataAgregator sql = new DataAgregator(context);
        SQLiteDatabase db = sql.getReadableDatabase();
        sql.deleteRow(db, id);
        db.close();
        sql.close();
    }
}
