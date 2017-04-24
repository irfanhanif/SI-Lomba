package com.example.fandyaditya.silomba.PengaturanTim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.fandyaditya.silomba.R;

public class AturTim extends AppCompatActivity {
    EditText namaTim;
    EditText deskripsiTim;
    Button browse;
    Button simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atur_tim);
        namaTim = (EditText)findViewById(R.id.atur_tim_namatim);
        deskripsiTim = (EditText)findViewById(R.id.atur_tim_deskripsi);
        browse = (Button)findViewById(R.id.atur_tim_browse);
        simpan = (Button)findViewById(R.id.atur_tim_simpanbtn);
    }
}
