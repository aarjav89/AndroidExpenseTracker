package com.example.project_expense_tracker;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class AddExpenseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_expense, container, false);

        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        TextView tvdate = (TextView) v.findViewById(R.id.tvExpense_date);

        tvdate.setText(currentDate.toString());


        // Code for Populating Drop down of Categories

        Spinner sp = (Spinner) v.findViewById(R.id.spin_expense_categ);
        ArrayList<String> categoriesFromDb = new ArrayList<>();
        DbCategories myDb = new DbCategories(getContext());
        Cursor data = myDb.getData("E");

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
