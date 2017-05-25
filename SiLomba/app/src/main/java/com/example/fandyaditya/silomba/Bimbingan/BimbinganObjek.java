package com.example.fandyaditya.silomba.Bimbingan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.fandyaditya.silomba.R;

/**
 * Created by Fandy Aditya on 4/24/2017.
 */

public class BimbinganObjek {
    private String id;
    private String idTim;
    private String tanggal;
    private String comment;
    private String fileBimbingan;

    public BimbinganObjek(String id,String idTim, String tanggal, String comment,String fileBimbingan) {
        this.id = id;
        this.idTim = idTim;
        this.tanggal = tanggal;
        this.comment = comment;
        this.fileBimbingan = fileBimbingan;
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

    public String getIdTim() {
        return idTim;
    }

    public String getFileBimbingan() {
        return fileBimbingan;
    }
    //    public static class DetailBimbingan extends AppCompatActivity {
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_detail_bimbingan);
//        }
//    }
}
