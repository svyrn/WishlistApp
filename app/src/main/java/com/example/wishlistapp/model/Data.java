package com.example.wishlistapp.model;

public class Data {
    private String id,nama,jumlah,ket,link;
    public Data(){

    }

    public Data(String id,String nama,String jumlah,String ket,String link){
        this.id = id;
        this.nama = nama;
        this.jumlah = jumlah;
        this.ket = ket;
        this.link = link;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getKet() {
        return ket;
    }

    public void setKet(String ket) {
        this.ket = ket;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
