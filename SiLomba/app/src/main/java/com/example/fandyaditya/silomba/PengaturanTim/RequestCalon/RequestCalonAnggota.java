package com.example.fandyaditya.silomba.PengaturanTim.RequestCalon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.fandyaditya.silomba.R;

public class RequestCalonAnggota extends AppCompatActivity {

    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_calon_anggota);
        rv = (RecyclerView)findViewById(R.id.request_calon_rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }
}
