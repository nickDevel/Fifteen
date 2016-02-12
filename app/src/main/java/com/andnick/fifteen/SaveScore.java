package com.andnick.fifteen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Бондаренко on 06.10.2015.
 */
public class SaveScore {
    DBHelper dbHelper;
    SQLiteDatabase db;
    ContentValues cv;
    Cursor c;
    private static final String TAG = "SSSSSSS";
    public SaveScore(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();

    }
    void addScore(int size, int score){Log.d(TAG, "********0");
        c = db.query("mytable", null, null, null, null, null, null);
        db = dbHelper.getWritableDatabase();
        Log.d(TAG, String.valueOf(getScore()));
        if(c.moveToFirst()) {
            Log.d(TAG, "********1");
            if (findID(size) == 0) {
                Log.d(TAG, "********2");
                cv = new ContentValues();
                cv.put("size", size);
                cv.put("score", score);
                db.insert("mytable", null, cv);

            } else if (Integer.parseInt(getScore(size, score)) > score) {
                Log.d(TAG, "********3");
                cv = new ContentValues();
                db.update("mytable", cv, "id = ?",
                        new String[]{String.valueOf(findID(size))});
                cv.put("score", score);

            }
        }
            else {Log.d(TAG, "********4");

                cv=new ContentValues();
                cv.put("size", String.valueOf(size));
                cv.put("score", String.valueOf(score));
                db.insert("mytable", null, cv);
                c.close();
            }
        c.close();
    }
    int findID(int size){
        db = dbHelper.getWritableDatabase();
        c = db.query("mytable", null, null, null, null, null, null);
        int returnID = 0;
        if(c.moveToFirst()) {
            int colIndex = c.getColumnIndex("size");
            do {
                if (c.getString(colIndex) == String.valueOf(size)) {
                    returnID = c.getInt(c.getColumnIndex("id"));
                    break;
                }
            } while (c.moveToNext());
            }
            return returnID;

    }
    String getScore(int size, int score){
        db = dbHelper.getWritableDatabase();
        c = db.query("mytable", null, null, null, null, null, null);
        String scoreGet="0";
        if(c.moveToFirst()){
        do {
            {if (c.getInt(c.getColumnIndex("id")) == findID(size)) {
                scoreGet = c.getString(c.getColumnIndex("score"));
                break;}
            }
        }while(c.moveToNext());}

        if (scoreGet == "0"){
            scoreGet = String.valueOf(score);
        }
        return  scoreGet;


    }
    String getScore(){
        db = dbHelper.getWritableDatabase();
        c = db.query("mytable", null, null, null, null, null, null);
        String scoreGet=null;
        if(c.moveToFirst()){
            Log.d(TAG, "!!!!!1");
            do {
                Log.d(TAG, "!!!!!2");
                {if (c.getInt(c.getColumnIndex("id")) == findID(BarleyBreak.size)) {
                    Log.d(TAG, "!!!!!!3");
                    scoreGet = c.getString(c.getColumnIndex("score"));
                    break;}
                }
            }while(c.moveToNext());}


        return  scoreGet;


    }

    class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            // конструктор суперкласса
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            // создаем таблицу с полями
            db.execSQL("create table mytable ("+"id integer primary key autoincrement,"
                    + " size text,"+ " score text"+");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

}
