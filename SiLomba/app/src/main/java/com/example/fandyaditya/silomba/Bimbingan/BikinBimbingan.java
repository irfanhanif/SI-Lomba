package com.example.fandyaditya.silomba.Bimbingan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fandyaditya.silomba.R;

public class BikinBimbingan extends AppCompatActivity {

    EditText tanggalBimbingan;
    EditText comment;
    Button upload;
    Button simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bikin_bimbingan);
        
        tanggalBimbingan = (EditText)findViewById(R.id.bikin_bimbingan_tgl);
        comment = (EditText)findViewById(R.id.bikin_bimbingan_comment);
        upload = (Button)findViewById(R.id.bikin_bimbingan_uploadbtn);
        simpan = (Button)findViewById(R.id.bikin_bimbingan_simpanbtn);

    }
}
