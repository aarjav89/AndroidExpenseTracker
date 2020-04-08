package com.example.project_expense_tracker;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class TransactionsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_transactions, container, false);

        DbTransactions myDb = new DbTransactions(getContext());
        ArrayList<String> transactionsFromDb = new ArrayList<>();
        Cursor data = myDb.getData();

        while(data.moveToNext()){
            transactionsFromDb.add(data.getString(2)); // because 2 is the column of transaction_notes
        }

        ListView lv = (ListView) v.findViewById(R.id.LVTransactions);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                transactionsFromDb);

        lv.setAdapter(listViewAdapter);

        return v;
    }
}
