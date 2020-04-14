package com.example.project_expense_tracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;

public class DbTransactions extends SQLiteOpenHelper {

    public static final String DBName = "tracker.db"; // .db is extension for sqllite
    public static final String TBLName = "transactions";
    public static final String Col0 = "ID";
    public static final String Col1 = "transaction_type";
    public static final String Col2 = "transaction_notes";
    public static final String Col3 = "transaction_amt";
    public static final String Col4 = "category_id";
    public static final String Col5 = "account_id";
    public static final String Col6 = "created_at";
    public static final String Col7 = "modified_at";

    //creating a constructor
    public DbTransactions(Context context){
        super(context,DBName,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS "+ TBLName + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                    "transaction_type TEXT NOT NULL," +
                                                    "transaction_notes TEXT," +
                                                    "transaction_amt REAL NOT NULL," +
                                                    "category_id REAL NOT NULL," +
                                                    "account_id REAL NOT NULL," +
                                                    "created_at DEFAULT CURRENT_TIMESTAMP NOT NULL," +
                                                    "modified_at DEFAULT CURRENT_TIMESTAMP NOT NULL)";


        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS "+ TBLName;

        db.execSQL(query);
    }

    public boolean addData(String transaction_type,String transaction_notes, String transaction_amt,Integer category_id, Integer account_id, String date){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues(); // this class will be responsible for encoding your data in key value pair.
        cv.put(Col1,transaction_type);
        cv.put(Col2,transaction_notes);
        cv.put(Col3,transaction_amt);
        cv.put(Col4,category_id);
        cv.put(Col5,account_id);
        cv.put(Col6,date);

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

        String query = "CREATE TABLE IF NOT EXISTS "+ TBLName + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "transaction_type TEXT NOT NULL," +
                "transaction_notes TEXT," +
                "transaction_amt REAL NOT NULL," +
                "category_id REAL NOT NULL," +
                "account_id REAL NOT NULL," +
                "created_at DEFAULT CURRENT_TIMESTAMP NOT NULL," +
                "modified_at DEFAULT CURRENT_TIMESTAMP NOT NULL)";


        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);


        String query1 = "SELECT * FROM "+TBLName;

//        String query2 = "SELECT * FROM "+ TBLName + " WHERE strftime('%Y',created_at) = strftime('%Y',date('now')) AND  strftime('%m',created_at) = strftime('%m',date('now'))";
//        Log.i("Query 2 is :",query2);
//        Cursor data1 = db.rawQuery(query2,null);
//        Log.i("1st record :", data1.getString(2));


        Cursor data = db.rawQuery(query1,null); // rawQuery takes 2 arguments. 1st is query. 2nd is selectionArguments like where

        return data;
    }

    public int getTotal(String IE){

        String query = "CREATE TABLE IF NOT EXISTS "+ TBLName + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "transaction_type TEXT NOT NULL," +
                "transaction_notes TEXT," +
                "transaction_amt REAL NOT NULL," +
                "category_id REAL NOT NULL," +
                "account_id REAL NOT NULL," +
                "created_at DEFAULT CURRENT_TIMESTAMP NOT NULL," +
                "modified_at DEFAULT CURRENT_TIMESTAMP NOT NULL)";

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);

        int total = 0;


        String query1 = "SELECT transaction_amt FROM "+TBLName + " WHERE transaction_type='" +IE+"'";

        Cursor data = db.rawQuery(query1,null); // rawQuery takes 2 arguments. 1st is query. 2nd is selectionArguments like where

        while(data.moveToNext()){
            total += Integer.parseInt(data.getString(0)); // because 0 is the column of transaction_amt
        }
        return total;
    }
}
