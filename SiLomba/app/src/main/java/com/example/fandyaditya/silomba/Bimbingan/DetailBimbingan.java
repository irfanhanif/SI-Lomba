package com.example.fandyaditya.silomba.Bimbingan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.fandyaditya.silomba.R;

public class DetailBimbingan extends AppCompatActivity {
    Button downloadBtn;
    TextView tanggal;
    TextView comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_bimbingan);
        downloadBtn = (Button)findViewById(R.id.detail_bimbingan_downloadBtn);
        tanggal = (TextView)findViewById(R.id.detail_bimbingan_tgl);
        comment = (TextView)findViewById(R.id.detail_bimbingan_comment);
    }
}
