package com.example.fandyaditya.silomba.PengaturanTim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.example.fandyaditya.silomba.R;

/**
 * Created by Fandy Aditya on 4/24/2017.
 */

public class BuatTim extends AppCompatActivity {

    EditText namaTim;
    EditText maxAnggota;
    EditText deskripsiTim;
    Button browseBtn;
    Button bikinBtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_tim);
        namaTim = (EditText)findViewById(R.id.buat_tim_nama);
        maxAnggota = (EditText)findViewById(R.id.buat_tim_max);
        deskripsiTim = (EditText)findViewById(R.id.buat_tim_deskripsi);
        browseBtn = (Button)findViewById(R.id.buat_tim_browse_btn);
        bikinBtn = (Button)findViewById(R.id.buat_tim_buat_btn);
    }
}
