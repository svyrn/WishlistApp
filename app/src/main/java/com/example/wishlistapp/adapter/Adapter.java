package com.example.wishlistapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wishlistapp.R;
import com.example.wishlistapp.model.Data;

import java.util.List;
import java.util.zip.Inflater;

public class Adapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Data> list;

    public Adapter(Activity activity, List<Data> list){
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater== null){
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (view==null & inflater != null){
            view=inflater.inflate(R.layout.list_barang,null);
        }
        if (view != null) {
            TextView nama = view.findViewById(R.id.textnama);
            TextView jumlah = view.findViewById(R.id.textjumlah);
            TextView ket = view.findViewById(R.id.textket);
            TextView link = view.findViewById(R.id.textlink);
            Data data = list.get(i);
            nama.setText(data.getNama());
            jumlah.setText(data.getJumlah());
            ket.setText(data.getKet());
            link.setText(data.getLink());
        }
        return view;
    }
}
