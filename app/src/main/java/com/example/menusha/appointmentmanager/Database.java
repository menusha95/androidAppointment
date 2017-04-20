package com.example.menusha.appointmentmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.EventLogTags;

import java.sql.SQLClientInfoException;

/**
 * Created by menusha on 4/7/17.
 */

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="aplistse.db";
    public static final String TABLE_NAME="aplistse_data";
    public static final String col1="DATES";
    public static final String col2="TIME";
    public static final String col3="TITLE";
    public static final String col4="DESCRIP";



    public Database(Context context){
        super(context,DATABASE_NAME,null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String tableCreate = "CREATE TABLE "+TABLE_NAME+" (DATES TEXT PRIMARY KEY, "+"TIME TEXT, "+"TITLE TEXT, "+"DESCRIP TEXT) ";
        db.execSQL(tableCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS "+TABLE_NAME);

    }
    public boolean addInfo(String dates, String time, String title, String desc ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col1,dates);
        contentValues.put(col2,time );
        contentValues.put(col3,title );
        contentValues.put(col4,desc );

        long dbResult = db.insert(TABLE_NAME,null,contentValues);

        if(dbResult== 1){
            return false;
        }else{
            return true;

        }
    }

    public Cursor getList(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return data;
    }


    public Cursor getTitle(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT "+col3+" FROM "+TABLE_NAME+" WHERE " + col3 + " = '" + name + "'";
        Cursor data = db.rawQuery(query,null);
        return data;

    }
    //to update databse
    public void updateDB(String newName, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + col3 +
                " = '" + newName + "' WHERE " + col3 + " = '" + name + "'";
        db.execSQL(query);
    }
    public void deleteDB(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + col3 + " = '" + name + "'";
        db.execSQL(query);
    }

}

