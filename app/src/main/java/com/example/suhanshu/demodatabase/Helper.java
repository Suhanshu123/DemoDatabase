package com.example.suhanshu.demodatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Helper extends SQLiteOpenHelper {
    public final static String DATABASE_NAME = "student.db";
    public final static String TABLE_NAME = "STUDENT";
    public final static String COL_1 = "ID";
    public final static String COL_2 = "NAME";
    public final static String COL_3 = "SURNAME";
    public final static String COL_4 = "MARKS";

    public interface ONhelo {
        void print();
    }


    public Helper(Context context) {
        super(context, DATABASE_NAME, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SURNAME TEXT, MARKS INTEGER )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Student");
        onCreate(db);

    }

    public boolean insertData(String name, String surname, int marks) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, surname);
        contentValues.put(COL_4, marks);

        long l = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if (l == -1) return false;
        else return true;


    }

    public Cursor getData() {

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursor;

    }

    public boolean update(int id,String name,String surname,int marks){

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);


        int i=database.update(TABLE_NAME,contentValues,"ID = "+id,null);

        if(i>0)
            return true;
        else return false;



    }


}
