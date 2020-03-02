package com.example.finaltry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBname = "UserInfo.db";
    public static final int DB_version = 2;

    public static final String User_Table = "Users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FNAME = "FName";
    public static final String COLUMN_LNAME = "LName";
    public static final String COLUMN_USERNAME = "Username";
    public static final String COLUMN_EMAIL = "Email";
    public static final String COLUMN_PASSWORD = "Password";
    public static final String COLUMN_PHONENUMBER = "Phonenumber";
    public static final String COLUMN_GENDER = "Gender";
    public static final String COLUMN_TIMESTAMP="TimeStamp";


    public DBHelper(@Nullable Context context) {
        super(context, DBname, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + User_Table + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_FNAME + " TEXT,"
                + COLUMN_LNAME + " TEXT," + COLUMN_USERNAME + " TEXT," + COLUMN_EMAIL + " TEXT," + COLUMN_PASSWORD + " TEXT," + COLUMN_PHONENUMBER + " TEXT," + COLUMN_GENDER + " TEXT,"+COLUMN_TIMESTAMP+" TIMESTAMP DEFAULT CURRENT_TIMESTAMP"+" );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + User_Table);
        onCreate(db);
    }

    public boolean insertData(String fName, String lName, String username, String email, String password, String phonenumber, String gender) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FNAME, fName);
        values.put(COLUMN_LNAME, lName);
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_PHONENUMBER, phonenumber);
        values.put(COLUMN_GENDER, gender);
        long result = db.insert(User_Table, null, values);
        if (result == -1) {
            return false;
        }
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res =db.rawQuery("Select * from " + User_Table, null,null);
        return res;
    }

    public boolean updateData(String id, String fname, String lname, String username, String email, String password, String phonenumber, String gender) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, id);
        values.put(COLUMN_FNAME, fname);
        values.put(COLUMN_LNAME, lname);
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_PHONENUMBER, phonenumber);
        values.put(COLUMN_GENDER, gender);
        db.update(User_Table, values, "id=?", new String[]{id});
        return true;

    }

    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(User_Table, "id=?", new String[]{id});
    }


}