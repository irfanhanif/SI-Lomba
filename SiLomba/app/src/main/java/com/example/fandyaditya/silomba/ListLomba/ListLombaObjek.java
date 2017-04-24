package com.example.fandyaditya.silomba.ListLomba;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.fandyaditya.silomba.R;

/**
 * Created by Fandy Aditya on 4/24/2017.
 */

public class ListLombaObjek {
    private String id;
    private String img;
    private String nama;
    private String penyelenggara;
    private String hadiah;

    public ListLombaObjek(String id, String img, String nama, String penyelenggara, String hadiah) {
        this.id = id;
        this.img = img;
        this.nama = nama;
        this.penyelenggara = penyelenggara;
        this.hadiah = hadiah;
    }

    public String getId() {
        return id;
    }

    public String getImg() {
        return img;
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

    public static class BuatTim extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_buat_tim);
        }
    }

    public static class DetailLomba extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_detail_lomba);
        }
    }
}
