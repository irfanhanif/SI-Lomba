package com.example.fandyaditya.silomba.Bimbingan.Dosen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.fandyaditya.silomba.R;

public class ListDosen extends AppCompatActivity {

    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_dosen);

        rv = (RecyclerView)findViewById(R.id.list_dosen_rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }
}
