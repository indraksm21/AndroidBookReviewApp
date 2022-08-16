package com.example.uas;

public class Objek {
    String id, judul="", pengarang="", deskripsi="", tahun="";
    Objek(String s_id, String s_judul, String s_pengarang, String s_deskripsi, String s_tahun){
        this.id = s_id;
        this.judul = s_judul;
        this.pengarang = s_pengarang;
        this.deskripsi = s_deskripsi;
        this.tahun = s_tahun;
    }

    public String getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public String getPengarang() {
        return pengarang;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getTahun() {
        return tahun;
    }
}
