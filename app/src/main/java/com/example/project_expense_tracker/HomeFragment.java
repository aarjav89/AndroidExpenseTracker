package com.example.project_expense_tracker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        int income_total =0 , expense_total = 0, balance = 0;
        DbTransactions myDb;
        TextView tvincome = (TextView) v.findViewById(R.id.tvIncomeFigure);
        TextView tvexpense = (TextView) v.findViewById(R.id.tvExpenseFigure);
        TextView tvbalance = (TextView) v.findViewById(R.id.tvBalanceFigure);

             myDb = new DbTransactions(getContext());
             income_total = myDb.getTotal("I");
             expense_total = myDb.getTotal("E");
             balance = income_total - expense_total;


        Log.i("i_total",Integer.toString(income_total));
        Log.i("e_total",Integer.toString(expense_total));
        Log.i("balance",Integer.toString(balance));



        tvincome.setText(String.valueOf(income_total));
        tvexpense.setText(String.valueOf(expense_total));
        tvbalance.setText(String.valueOf(balance));
        return v;
    }


}
