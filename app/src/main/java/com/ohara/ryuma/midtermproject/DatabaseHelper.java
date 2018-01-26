package com.ohara.ryuma.midtermproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ryuma on 2018-01-22.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Folder.db";
    public static final String TABLE_FOLDER = "Folder_table";
    public static final String FOLDER_COL_1 = "ID";
    public static final String FOLDER_COL_2 = "NAME";

    public static final String TABLE_FLASHCARD = "Flashcard_table";
    public static final String FLASHCARD_COL_1 = "ID";
    public static final String FLASHCARD_COL_2 = "FRONT";
    public static final String FLASHCARD_COL_3 = "BACK";
    public static final String FLASHCARD_COL_4 = "FOLDER_ID";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_FOLDER + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT)");
        db.execSQL("create table " + TABLE_FLASHCARD + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, FRONT TEXT, BACK TEXT, FOLDER_ID INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOLDER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FLASHCARD);
        onCreate(db);
    }

    public boolean insertFolderData(String name) {
        SQLiteDatabase db = this.getWritableDatabase(); // null
        ContentValues contentValues = new ContentValues();
        contentValues.put(FOLDER_COL_2, name);
        long result = db.insert(TABLE_FOLDER,null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertFlashCardData(String front, String back, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FLASHCARD_COL_2, front);
        contentValues.put(FLASHCARD_COL_3, back);
        contentValues.put(FLASHCARD_COL_4, id);
        //db.update(TABLE_FOLDER, contentValues, "ID = ?", new String[] {id});
        long result = db.insert(TABLE_FLASHCARD,null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    public Cursor getAllFolderData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_FOLDER, null);
        return res;
    }

    public Cursor getAllFlashCardData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_FLASHCARD, null);
        return res;
    }

    public Cursor getAllFlashCardDataWithID(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_FLASHCARD + " WHERE FOLDER_ID = " + id + ";", null); //debug
        return res;
    }

    public boolean updateFolderData(String id, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FOLDER_COL_1, id);
        contentValues.put(FOLDER_COL_2, name);
        db.update(TABLE_FOLDER, contentValues, "ID = ?", new String[] {id});
        return true;
    }

    public boolean updateFlashCardData(String id, String front, String back) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FLASHCARD_COL_1, id);
        contentValues.put(FLASHCARD_COL_2, front);
        contentValues.put(FLASHCARD_COL_3, back);
        db.update(TABLE_FLASHCARD, contentValues, "ID = ?", new String[] {id});
        return true;
    }

    public Integer deleteFolderData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_FOLDER, "ID = ?", new String[] {id});
    }

    public Integer deleteFlashCardData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_FLASHCARD, "ID = ?", new String[] {id});
    }


}
