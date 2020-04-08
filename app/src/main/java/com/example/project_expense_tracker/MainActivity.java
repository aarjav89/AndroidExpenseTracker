package com.example.project_expense_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ActionBar ab = getSupportActionBar();

        ab.setTitle("Expense Tracker");

        //adding default data to Categories table, if not present
//        DbCategories dbc = new DbCategories(this);
//        int check = dbc.checkSeeder();
//        if(check==0){
//           dbc.seeder();
//        }
//
//        //adding default data to Accounts table, if not present
//        DbAccounts dba = new DbAccounts(this);
//        check = dba.checkSeeder();
//        if(check == 0){
//            dba.seeder();
//        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int clicked = item.getItemId();

        Fragment fr;
        if(clicked == R.id.btn_spending){
            fr = new HomeFragment();
        }
        else if(clicked == R.id.btn_categories){
            fr = new CategoriesFragment();
        }
        else if(clicked == R.id.btn_transactions){
            fr = new TransactionsFragment();
        }

        else {
            fr = new AccountsFragment();
        }

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();



        ft.remove(new CategoriesFragment());
        ft.remove(new HomeFragment());
        ft.remove(new TransactionsFragment());
        ft.remove(new AccountsFragment());

        ft.replace(R.id.fragment,fr);
        ft.commit();

        return super.onOptionsItemSelected(item);
    }

    public void showFrag(View v){
        Fragment fr;
        int btn_clicked = v.getId();

        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());



        if(btn_clicked == R.id.btnAddIncome){
            fr = new AddIncomeFragment();
        }
        else{
            fr = new AddExpenseFragment();
        }

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();


        ft.remove(new CategoriesFragment());
        ft.remove(new HomeFragment());
        ft.remove(new TransactionsFragment());
        ft.remove(new AccountsFragment());

        ft.replace(R.id.fragment,fr);
        ft.commit();
    }

    public void addIncome(View v){
        // Log.i("Something","Something else");
        EditText notes = (EditText) findViewById(R.id.ETIncome_notes);
        EditText amount  = (EditText) findViewById(R.id.ETIncome_amount);

        if((amount.getText().toString().length() == 0) || (notes.getText().toString().length()==0)){
            Toast.makeText(this,"Please enter all fields",Toast.LENGTH_LONG).show();
            return;
        }


        DbTransactions myDB = new DbTransactions(this);
        boolean result = myDB.addData("I", notes.getText().toString(), amount.getText().toString(),1,1);

        if(result==true){
            Toast.makeText(this,"Transaction Added",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"Transaction not Added",Toast.LENGTH_LONG).show();

        }

        Fragment fr = new HomeFragment();
       FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.remove(new CategoriesFragment());
        ft.remove(new HomeFragment());
        ft.remove(new TransactionsFragment());
        ft.remove(new AccountsFragment());
        ft.remove(new AddIncomeFragment());
        ft.remove(new AddExpenseFragment());

        ft.replace(R.id.fragment,fr);
        ft.commit();

    }

    public void addExpense(View v){
        // Log.i("Something","Something else");
        EditText notes = (EditText) findViewById(R.id.ETExpense_notes);
        EditText amount  = (EditText) findViewById(R.id.ETExpense_amt);

        if((amount.getText().toString().length() == 0) || (notes.getText().toString().length()==0)){
            Toast.makeText(this,"Please enter all fields",Toast.LENGTH_LONG).show();
            return;
        }


        DbTransactions myDB = new DbTransactions(this);
        boolean result = myDB.addData("E", notes.getText().toString(), amount.getText().toString(),1,1);

        if(result==true){
            Toast.makeText(this,"Transaction Added",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"Transaction not Added",Toast.LENGTH_LONG).show();

        }

        Fragment fr = new HomeFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.remove(new CategoriesFragment());
        ft.remove(new HomeFragment());
        ft.remove(new TransactionsFragment());
        ft.remove(new AccountsFragment());
        ft.remove(new AddIncomeFragment());
        ft.remove(new AddExpenseFragment());

        ft.replace(R.id.fragment,fr);
        ft.commit();

    }
}
