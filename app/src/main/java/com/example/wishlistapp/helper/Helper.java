package com.example.wishlistapp.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class Helper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "stok";

    public Helper (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_TABLE = "CREATE TABLE barang (Id integer PRIMARY KEY autoincrement,nama TEXT NOT NULL,jumlah TEXT NOT NULL,ket TEXT NOT NULL,link TEXT NOT NULL)";
                sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS barang");
        onCreate(sqLiteDatabase);
    }

    public ArrayList<HashMap<String, String>> getall(){
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        String QUERY = "SELECT * FROM barang";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(QUERY, null);
        if (cursor.moveToFirst()){
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("nama", cursor.getString(1));
                map.put("jumlah", cursor.getString(2));
                map.put("ket", cursor.getString(3));
                map.put("link", cursor.getString(4));
                list.add(map);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public void insert(String nama, String jumlah, String ket, String link){
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "INSERT INTO barang (nama,jumlah,ket,link) VALUES('"+nama+"','"+jumlah+"','"+ket+"','"+link+"')";
        database.execSQL(QUERY);
    }

    public void update(int id, String nama, String jumlah, String ket, String link){
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "UPDATE barang SET nama= '"+nama+"', jumlah= '"+jumlah+"', ket= '"+ket+"', link= '"+link+"' WHERE id="+id;
        database.execSQL(QUERY);
    }

    public void delete(int id){
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "DELETE from barang WHERE id="+id;
        database.execSQL(QUERY);
    }
}
