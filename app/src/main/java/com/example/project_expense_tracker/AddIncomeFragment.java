package com.example.project_expense_tracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class AddIncomeFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_income, container, false);


        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

       // Toast.makeText(getContext(),currentDate,Toast.LENGTH_LONG).show();

        TextView tvdate = (TextView) v.findViewById(R.id.tvDate_Income);

        //Log.i("Error msg",currentDate);
        tvdate.setText(currentDate.toString());

        // Code for Populating Drop down of Categories

        Spinner sp = (Spinner) v.findViewById(R.id.spin_income_categ);
        ArrayList<String> categoriesFromDb = new ArrayList<>();
        DbCategories myDb = new DbCategories(getContext());
        Cursor data = myDb.getData("I");


        while(data.moveToNext()){
            categoriesFromDb.add(data.getString(1)); // because 1 is the column of categories
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                categoriesFromDb);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp.setAdapter(spinnerAdapter);

        return v;

    }



}