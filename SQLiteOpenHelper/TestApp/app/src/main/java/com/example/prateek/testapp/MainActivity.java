package com.example.prateek.testapp;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.CalendarContract;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class MainActivity extends ActionBarActivity  {


    SQLiteDatabase db;
    DBHelper dbhelper;

    EditText et_name;
    EditText et_email;
    EditText et_comment;

    Context context;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbhelper=new DBHelper(getApplicationContext());
        db=dbhelper.getWritableDatabase();


        et_name=(EditText) findViewById(R.id.et_name);
        et_email=(EditText) findViewById(R.id.et_email);
        et_comment=(EditText) findViewById(R.id.et_comment);


    }

    public void insert_values(View v){
       String name=et_name.getText().toString();
        String comment=et_comment.getText().toString();
        String email=et_email.getText().toString();

        ContentValues cv=new ContentValues();

        cv.put(DBHelper.NAME,name);
        cv.put(DBHelper.COMMENT,comment);
        cv.put(DBHelper.EMAIL,email);

        db.insert(DBHelper.TABLE_NAME, null, cv);

        Toast.makeText(getApplicationContext(),
                "Values inserted", Toast.LENGTH_LONG).show();


    }

    public void display_data(View v){
        String[] columns={DBHelper.NAME,DBHelper.COMMENT,DBHelper.EMAIL};

        Cursor cursor= db.query(DBHelper.TABLE_NAME,columns,null,null,null,null,null);
        cursor.moveToFirst();

        while(cursor.moveToNext()){
            String name=cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.NAME));
            String email=cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.EMAIL));
            String comment=cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COMMENT));

            Toast.makeText(getApplicationContext(),"Name: "+name+"\n Email: "+email+"\n Comment: "+comment, Toast.LENGTH_SHORT).show();

        }
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
