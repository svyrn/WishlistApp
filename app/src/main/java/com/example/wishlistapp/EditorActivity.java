package com.example.wishlistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wishlistapp.helper.Helper;

public class EditorActivity extends AppCompatActivity {

    private EditText editnama,editjumlah,editket,editlink;
    private Button btnsimpan;
    private Helper db = new Helper(this);
    private String id,nama,jumlah,ket,link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        editnama = findViewById(R.id.editnama);
        editjumlah = findViewById(R.id.editjumlah);
        editket = findViewById(R.id.editket);
        editlink = findViewById(R.id.editlink);
        btnsimpan = findViewById(R.id.btnsimpan);

        id = getIntent().getStringExtra("id");
        nama = getIntent().getStringExtra("nama");
        jumlah = getIntent().getStringExtra("jumlah");
        ket = getIntent().getStringExtra("ket");
        link = getIntent().getStringExtra("link");

        if (id == null || id.equals("")){
            setTitle("Tambah Barang");
        }else{
            setTitle("Edit Barang");
            editnama.setText(nama);
            editjumlah.setText(jumlah);
            editket.setText(ket);
            editlink.setText(link);
        }
        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if (id == null || id.equals("")){
                        save();
                    }else{
                        edit();
                    }
                }catch (Exception e){
                    Log.e("Saving", e.getMessage());
                }
            }
        });
    }

    private void save(){
        if (String.valueOf(editnama.getText()).equals("") || String.valueOf(editjumlah.getText()).equals("")
                || String.valueOf(editket.getText()).equals("") || String.valueOf(editlink.getText()).equals("")){
            Toast.makeText(getApplicationContext(), "Mohon isi semua kolom", Toast.LENGTH_SHORT).show();
        }else{
            db.insert(editnama.getText().toString(),editjumlah.getText().toString(),editket.getText().toString(),editlink.getText().toString());
            finish();
        }
    }

    private void edit(){
        if (String.valueOf(editnama.getText()).equals("") || String.valueOf(editjumlah.getText()).equals("")
                || String.valueOf(editket.getText()).equals("") || String.valueOf(editlink.getText()).equals("")){
            Toast.makeText(getApplicationContext(), "Mohon isi semua kolom", Toast.LENGTH_SHORT).show();
        }else{
            db.update(Integer.parseInt(id),editnama.getText().toString(),editjumlah.getText().toString(),editket.getText().toString(),editlink.getText().toString());
            finish();
        }
    }


}