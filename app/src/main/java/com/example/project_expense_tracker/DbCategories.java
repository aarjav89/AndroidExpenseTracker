package com.example.project_expense_tracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbCategories extends SQLiteOpenHelper {

    public static final String DBName = "exp_tracker.db"; // .db is extension for sqllite
    public static final String TBLName = "categories";
    public static final String Col0 = "ID";
    public static final String Col1 = "category_name";
    public static final String Col2 = "category_type";
    public static final String Col3 = "created_at";
    public static final String Col4 = "modified_at";


    //creating a constructor
    public DbCategories(Context context){
        super(context,DBName,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+ TBLName + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "category_name TEXT NOT NULL," +
                "category_type TEXT," +
                "created_at DEFAULT CURRENT_TIMESTAMP NOT NULL," +
                "modified_at DEFAULT CURRENT_TIMESTAMP NOT NULL)";


        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS "+ TBLName;

        db.execSQL(query);
    }

    public boolean addData(String category_name,String category_type){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues(); // this class will be responsible for encoding your data in key value pair.
        cv.put(Col1,category_name);
        cv.put(Col2,category_type);

        long result = db.insert(TBLName,null,cv);// 1st argument:Table name inwhich data is to be inserted.
        //out of these attributes, do i have a null attribute to pass in any field (id, tasks)? No. so we will pass null as 2nd argument.

        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM "+TBLName;

        Cursor data = db.rawQuery(query,null); // rawQuery takes 2 arguments. 1st is query. 2nd is selectionArguments like where

        return data;
    }

    public boolean seeder(){
        SQLiteDatabase db = this.getWritableDatabase();
        long result;
        ContentValues cv = new ContentValues(); // this class will be responsible for encoding your data in key value pair.
        cv.put(Col1,"Salary");
        cv.put(Col2,"I");
        result = db.insert(TBLName,null,cv);

        cv.put(Col1,"Self-Employment");
        cv.put(Col2,"I");
        result = db.insert(TBLName,null,cv);

        cv.put(Col1,"Taxi");
        cv.put(Col2,"E");
        result = db.insert(TBLName,null,cv);

        cv.put(Col1,"Eating out");
        cv.put(Col2,"E");
        result = db.insert(TBLName,null,cv);

        cv.put(Col1,"Travel");
        cv.put(Col2,"E");
        result = db.insert(TBLName,null,cv);

        cv.put(Col1,"Grocery");
        cv.put(Col2,"E");
        result = db.insert(TBLName,null,cv);

        cv.put(Col1,"Phone Bill");
        cv.put(Col2,"E");
        result = db.insert(TBLName,null,cv);

        cv.put(Col1,"Entertainment");
        cv.put(Col2,"E");
        result = db.insert(TBLName,null,cv);

        cv.put(Col1,"Kids");
        cv.put(Col2,"E");
        result = db.insert(TBLName,null,cv);


        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public int checkSeeder(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+TBLName;
        Cursor data = db.rawQuery(query,null);
        int cnt = data.getCount();

        return cnt;

    }
}
