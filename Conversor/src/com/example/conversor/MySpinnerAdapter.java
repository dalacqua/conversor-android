package com.example.conversor;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MySpinnerAdapter extends ArrayAdapter<Valor>{

    private Context context;
    private Valor[] myObjs;

    public MySpinnerAdapter(Context context, int textViewResourceId,
    		Valor[] myObjs) {
        super(context, textViewResourceId, myObjs);
        this.context = context;
        this.myObjs = myObjs;
    }

    public int getCount(){
       return myObjs.length;
    }

    public Valor getItem(int position){
       return myObjs[position];
    }

    public long getItemId(int position){
       return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView label = new TextView(context);
        label.setText(myObjs[position].getMoeda());
        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView,
            ViewGroup parent) {
        TextView label = new TextView(context);
        label.setText(myObjs[position].getMoeda());
        return label;
    }
}