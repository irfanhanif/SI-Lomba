package com.example.fandyaditya.silomba.ListLomba;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.fandyaditya.silomba.R;

/**
 * Created by Fandy Aditya on 4/24/2017.
 */

public class ListLombaObjek {
    private String id;
    private String nama;
    private String penyelenggara;
    private String kategori;
    private String syarat;
    private String deskripsiLomba;
    private String hadiah;

    public ListLombaObjek(String id, String nama, String penyelenggara, String kategori, String syarat, String deskripsiLomba, String hadiah) {
        this.id = id;
        this.nama = nama;
        this.penyelenggara = penyelenggara;
        this.kategori = kategori;
        this.syarat = syarat;
        this.deskripsiLomba = deskripsiLomba;
        this.hadiah = hadiah;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getPenyelenggara() {
        return penyelenggara;
    }

    public String getHadiah() {
        return hadiah;
    }

    public String getKategori() {
        return kategori;
    }

    public String getSyarat() {
        return syarat;
    }

    public String getDeskripsiLomba() {
        return deskripsiLomba;
    }
}
