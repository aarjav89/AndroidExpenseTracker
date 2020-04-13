package com.example.project_expense_tracker;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class AccountsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_accounts, container, false);

        DbAccounts myDb = new DbAccounts(getContext());
        ArrayList<String> accountsFromDb = new ArrayList<>();
        Cursor data = myDb.getData();
        Log.i("cursor data from frag",data.toString());
        while(data.moveToNext()){
            accountsFromDb.add(data.getString(1)); // because 1 is the column of accounts_name
        }

        ListView lv = (ListView) v.findViewById(R.id.LVAccounts);
        Log.i("Accounts",accountsFromDb.toString());
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                accountsFromDb);

        lv.setAdapter(listViewAdapter);

        return v;

    }
}
