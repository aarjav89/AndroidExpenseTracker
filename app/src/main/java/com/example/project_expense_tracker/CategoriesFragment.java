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
        return inflater.inflate(R.layout.fragment_categories, container, false);

        ListView lv = (ListView) getView().findViewById(R.id.LVCategories);

        ArrayList<String> categoriesFromDb = new ArrayList<>();

        DbCategories myDb = new DbCategories(getContext());

        Cursor data = myDb.getData();

        while(data.moveToNext()){
            categoriesFromDb.add(data.getString(1));
        }

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.ca);

        //PROBLEM IN BELOW LINE. HOW TO USE ARRAY ADAPTER BIND INSIDE FRAGMENT
        ArrayAdapter bind = new ArrayAdapter(getActivity(),R.layout.categories_template,categoriesFromDb);

    }
}
