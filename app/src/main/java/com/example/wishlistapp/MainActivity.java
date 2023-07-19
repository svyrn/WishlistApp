package com.example.wishlistapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.wishlistapp.adapter.Adapter;
import com.example.wishlistapp.helper.Helper;
import com.example.wishlistapp.model.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    AlertDialog.Builder dialog;
    List<Data> list = new ArrayList<>();
    Adapter adapter;
    Helper db = new Helper(this);
    Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new Helper(getApplicationContext());
        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
        listView = findViewById(R.id.list_item);
        adapter = new Adapter(MainActivity.this, list);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String id = list.get(i).getId();
                final String nama = list.get(i).getNama();
                final String jumlah = list.get(i).getJumlah();
                final String ket = list.get(i).getKet();
                final String link = list.get(i).getLink();
                final CharSequence[] dialogItem = {"Edit","Hapus"};
                dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                            Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                                intent.putExtra("id",id);
                                intent.putExtra("nama",nama);
                                intent.putExtra("jumlah",jumlah);
                                intent.putExtra("ket",ket);
                                intent.putExtra("link",link);
                                startActivity(intent);
                                break;
                            case 1:
                            db.delete(Integer.parseInt(id));
                            list.clear();
                                getData();
                            break;
                        }
                    }
                }).show();
                return false;
            }
        });

    }

    private void getData(){
        ArrayList<HashMap<String, String>> rows =db.getall();
        for (int i=0; i<rows.size(); i++){
            String id = rows.get(i).get("id");
            String nama = rows.get(i).get("nama");
            String jumlah = rows.get(i).get("jumlah");
            String ket = rows.get(i).get("ket");
            String link = rows.get(i).get("link");

            Data data = new Data();
            data.setId(id);
            data.setNama(nama);
            data.setJumlah(jumlah);
            data.setKet(ket);
            data.setLink(link);
            list.add(data);
        }
        adapter.notifyDataSetChanged();
    }
    @Override
    protected void onResume(){
        super.onResume();
        list.clear();
        getData();
    }


}