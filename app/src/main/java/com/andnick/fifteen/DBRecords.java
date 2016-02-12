package com.andnick.fifteen;

/**
 * Created by Бондаренко on 01.10.2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBRecords{
    DBHelper dbHelper;
    SQLiteDatabase db;
    ContentValues cv;
    Cursor c;
    String scoreDB;
    String gameSize;
    private static final String TAG = "DB ";

    public DBRecords(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
        cv = new ContentValues();
    }

    String getScore(){
        db = dbHelper.getWritableDatabase();
        c = db.query("mytable", null, null, null, null, null, null);
        if (c.moveToFirst()) {
            // определяем номера столбцов по имени в выборке
            int scoreColIndex = c.getColumnIndex("score1");
            scoreDB =c.getString(scoreColIndex);
            Log.d(TAG, scoreDB);
            // получаем значения по номерам столбцов и пишем все в лог
            // переход на следующую строку
            // а если следующей нет (текущая - последняя), то false - выходим из цикла
        } else{
            scoreDB = null;
        }
        c.close();
        return scoreDB;
    }

    void addScore(String score) {
        c = db.query("mytable", null, null, null, null, null, null);
        if (getScore()==null) {
            cv = new ContentValues();
            cv.put("size", getSize());
            cv.put("score", score);
            db.insert("mytable", null, cv);
            }
            else {
            if (Integer.parseInt(score) < Integer.parseInt(getScore())) {
                deleteScore();
                cv = new ContentValues();
                cv.put("size", getSize());
                cv.put("score", score);
                db.insert("mytable", null, cv);
                }
            // вставляем запись и получаем ее ID
            Log.d(TAG, "Exist score " + getScore() + "New score " + score);
            dbHelper.close();
        }
    }

    void deleteScore(){
    db.delete("mytable", null, null);
    }
    String getSize(){
        gameSize=String.valueOf(GameAct.size);
        return gameSize;
    }

    class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            // конструктор суперкласса
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            // создаем таблицу с полями
            db.execSQL("create table mytable ("
                    + "size"+"score text"+");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
