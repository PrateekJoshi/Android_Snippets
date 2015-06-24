package com.example.prateek.testapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

/**
 * Created by prateek on 6/24/2015.
 */
public class DBHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME="my_database";
    public static final String TABLE_NAME="my_table";
    public static final String ID="id";
    public static final String NAME="name";
    public static final String COMMENT="comment";
    public static final String EMAIL="email";
    public static final String TIME="time";
    public static final int VERSION=1;


    private final String create_db="create table if not exists "+TABLE_NAME+" ( "
            +ID+" integer primary key autoincrement, "
            +NAME+" text, "
            +COMMENT+" text, "
            +EMAIL+" text, "
            +TIME+" text ); ";





    public DBHelper(Context context) {
        super(context, DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_db);

    }


    public void onUpgrade(SQLiteDatabase db,int from,int to){
        db.execSQL("drop table "+TABLE_NAME);

    }
}
