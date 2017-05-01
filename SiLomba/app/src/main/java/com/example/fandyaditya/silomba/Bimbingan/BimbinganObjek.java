package com.example.fandyaditya.silomba.Bimbingan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.fandyaditya.silomba.R;

/**
 * Created by Fandy Aditya on 4/24/2017.
 */

public class BimbinganObjek {
    String id;
    String tanggal;
    String comment;

    public BimbinganObjek(String id, String tanggal, String comment) {
        this.id = id;
        this.tanggal = tanggal;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getComment() {
        return comment;
    }

    public static class DetailBimbingan extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_detail_bimbingan);
        }
    }
}
