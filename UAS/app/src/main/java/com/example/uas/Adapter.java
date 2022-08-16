package com.example.uas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class Adapter extends BaseAdapter {

    LayoutInflater layoutInflater;
    Context context;
    ArrayList<Objek> model;
    Adapter(Context c_context, ArrayList<Objek> c_model) {
        this.context = c_context;
        this.model = c_model;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return model.size();
    }

    @Override
    public Object getItem(int index) {
        return model.get(index);
    }

    @Override
    public long getItemId(int index) {
        return index;
    }

    TextView judul, tahun;

    @Override
    public View getView(int index, View convertView, ViewGroup parent) {
        View view1 = layoutInflater.inflate(R.layout.list_data, parent, false);
        judul = view1.findViewById(R.id.judul);
        tahun = view1.findViewById(R.id.tahun);

        judul.setText(model.get(index).getJudul());
        tahun.setText(model.get(index).getTahun());
        return view1;
    }
}
