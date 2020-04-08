package com.example.project_expense_tracker;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class CategoriesFragment extends Fragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_categories, container, false);

        DbTransactions myDb = new DbTransactions(getContext());
        ArrayList<String> categoriesFromDb = new ArrayList<>();
        Cursor data = myDb.getData();

        while(data.moveToNext()){
            categoriesFromDb.add(data.getString(2)); // because 2 is the column of transaction_notes
        }

        ListView lv = (ListView) v.findViewById(R.id.LVCategories);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                categoriesFromDb);

        lv.setAdapter(listViewAdapter);

        return v;
    }
}
