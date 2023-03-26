package com.example.autofix;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelperOld extends SQLiteOpenHelper {
    final static String DATABASE_NAME = "AutoFix.db";
    final static int DATABASE_VERSION =1;
    final static String TABLE1_NAME = "ServiceProvider_table";
    final static String T1COL1 = "ProviderID";
    final static String T1COL2 = "ServProviderName";
    final static String T1COL3 = "PhoneNumber";
    final static String T1COL4 = "City";
    final static String T1COL5 = "ManagerID";

    final static  String TABLE2_NAME = "Users_table";
    final static String T2COL1 = "Username";
    final static String T2COL2 = "Password";
    final static String T2COL3 = "FullName";
    final static String T2COL4 = "CellPhoneNumb";
    final static String T2COL5 = "Email";
    final static String T2COL6 = "UserType";


    public DatabaseHelperOld(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE1_NAME + "(" +
                T1COL1 + " INTEGER PRIMARY KEY," + T1COL2 + " TEXT," +
                T1COL3 + " TEXT," + T1COL4 + " TEXT," + T1COL5 + " INTEGER)";
        sqLiteDatabase.execSQL(query);
        query = "CREATE TABLE " + TABLE2_NAME + "(" + T2COL1 + " TEXT," +
                T2COL2 + " TEXT," + T2COL3 + " TEXT," + T2COL4 + " TEXT," +
                T2COL5 + " TEXT," + T2COL6 + " TEXT)";
        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE1_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean addServProvData(String provName, String phone, String city){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T1COL2, provName);
        values.put(T1COL3,phone);
        values.put(T1COL4,city);
        long l = sqLiteDatabase.insert(TABLE1_NAME,null, values);
        if(l>0)
            return true;
        else
            return false;
    }

    public Cursor selectData(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT ServProviderName,PhoneNumber, City from " + TABLE1_NAME;
        return sqLiteDatabase.rawQuery(query,null);
    }
}
