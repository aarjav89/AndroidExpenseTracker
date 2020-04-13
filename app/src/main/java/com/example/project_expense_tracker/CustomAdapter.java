package com.example.project_expense_tracker;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<String> trans = new ArrayList<>();
    ArrayList<String> amt = new ArrayList<>();
    ArrayList<String> date = new ArrayList<>();
    ArrayList<String> type = new ArrayList<> ();

    public CustomAdapter(Context context, ArrayList<String> transactions, ArrayList<String> amount, ArrayList<String> dates, ArrayList<String> type){
        this.trans = transactions;
        this.amt = amount;
        this.date = dates;
        this.type = type;
        this.context = context;
        this.inflater = inflater.from(context); // from our Transactions fragment, bind the data to the layout created.
    }

    @Override
    public int getCount() {
        return trans.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        // at a time one instance of custom adapter, is 1 row of the arrayList of transaction or amount or date
        // index of that array list is defined as position in the function argument.
        view = inflater.from(context).inflate(R.layout.transactions_template,null); // here null is that we dont have a parent layout.

        TextView tv_trans_note = (TextView) view.findViewById(R.id.tvTransNote_template);
        TextView tv_trans_amt = (TextView) view.findViewById(R.id.tvTransAmt_template);
        TextView tv_trans_date = (TextView) view.findViewById(R.id.tvTransDate_template);


        if(type.get(position) == "I")
            tv_trans_note.setTextColor(Color.GREEN);

        else
            tv_trans_note.setTextColor(Color.RED);

        tv_trans_note.setText(trans.get(position)); // setting the value from arraylist to the textview of template
        tv_trans_amt.setText(amt.get(position));
        tv_trans_date.setText(date.get(position));

        return view;

    }
}
