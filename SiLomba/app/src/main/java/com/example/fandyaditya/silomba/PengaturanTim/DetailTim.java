package com.example.fandyaditya.silomba.PengaturanTim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.fandyaditya.silomba.ParseJSON;
import com.example.fandyaditya.silomba.PengaturanTim.AturTim;
import com.example.fandyaditya.silomba.PengaturanTim.RequestCalon.RequestCalonAnggota;
import com.example.fandyaditya.silomba.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailTim extends AppCompatActivity {

    ImageView timImg;
    TextView namaTim;
    TextView deskripsiTim;
    ImageView anggota1Img;
    ImageView anggota2Img;
    ImageView anggota3Img;
    ImageView anggota4Img;
    ImageView anggota5Img;
    ImageView lombaImg;
    Button aturTim;
    Button requestCalon;
    Bundle bundle;

    String idTim;
    String namaTimVal;
    String deskripsiTimVal;
    String maxAnggota;
    String profPic;
    String tipeTim;

    View garis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tim);

        timImg = (ImageView) findViewById(R.id.detail_tim_img);
        namaTim = (TextView) findViewById(R.id.detail_tim_nama);
        deskripsiTim = (TextView) findViewById(R.id.detail_tim_deskripsi);
        anggota1Img = (ImageView) findViewById(R.id.detail_tim_anggota_1);
        anggota2Img = (ImageView) findViewById(R.id.detail_tim_anggota_2);
        anggota3Img = (ImageView) findViewById(R.id.detail_tim_anggota_3);
        anggota4Img = (ImageView) findViewById(R.id.detail_tim_anggota_4);
        anggota5Img = (ImageView) findViewById(R.id.detail_tim_anggota_5);
        lombaImg = (ImageView) findViewById(R.id.detail_tim_lomba_img);
        aturTim = (Button) findViewById(R.id.detail_atur_tim_btn);
        requestCalon = (Button) findViewById(R.id.detail_request_calonbtn);

        bundle = getIntent().getExtras();
        idTim = bundle.getString("idTim");
        namaTimVal = bundle.getString("namaTim");
        maxAnggota = bundle.getString("maxAnggota");
        deskripsiTimVal = bundle.getString("deskripsiTim");
        profPic = bundle.getString("profPic");
        tipeTim = bundle.getString("tipeTim");
        garis = findViewById(R.id.detail_tim_garis);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail Tim");

        aturTim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIntent(AturTim.class);
            }
        });
        requestCalon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIntent(RequestCalonAnggota.class);
            }
        });
        setView();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:finish();break;
        }
        return true;
    }
    private void setView(){
        namaTim.setText(namaTimVal);
        deskripsiTim.setText(deskripsiTimVal);
        int maxAnggotaTrue = Integer.parseInt(maxAnggota);

        if(maxAnggotaTrue==1){
            anggota1Img.setImageResource(R.color.ijoAnggota);
            anggota2Img.setImageResource(R.color.merahAnggota);
            anggota3Img.setImageResource(R.color.merahAnggota);
            anggota4Img.setImageResource(R.color.merahAnggota);
            anggota5Img.setImageResource(R.color.merahAnggota);
        }
        else if(maxAnggotaTrue==2){
            anggota1Img.setImageResource(R.color.ijoAnggota);
            anggota2Img.setImageResource(R.color.ijoAnggota);
            anggota3Img.setImageResource(R.color.merahAnggota);
            anggota4Img.setImageResource(R.color.merahAnggota);
            anggota5Img.setImageResource(R.color.merahAnggota);
        }
        else if(maxAnggotaTrue==3){
            anggota1Img.setImageResource(R.color.ijoAnggota);
            anggota2Img.setImageResource(R.color.ijoAnggota);
            anggota3Img.setImageResource(R.color.ijoAnggota);
            anggota4Img.setImageResource(R.color.merahAnggota);
            anggota5Img.setImageResource(R.color.merahAnggota);
        }
        else if(maxAnggotaTrue==4){
            anggota1Img.setImageResource(R.color.ijoAnggota);
            anggota2Img.setImageResource(R.color.ijoAnggota);
            anggota3Img.setImageResource(R.color.ijoAnggota);
            anggota4Img.setImageResource(R.color.ijoAnggota);
            anggota5Img.setImageResource(R.color.merahAnggota);
        }
        else {
            anggota1Img.setImageResource(R.color.ijoAnggota);
            anggota2Img.setImageResource(R.color.ijoAnggota);
            anggota3Img.setImageResource(R.color.ijoAnggota);
            anggota4Img.setImageResource(R.color.ijoAnggota);
            anggota5Img.setImageResource(R.color.ijoAnggota);
        }

        if(tipeTim.equals("anggota")){
            aturTim.setVisibility(View.GONE);
            requestCalon.setVisibility(View.GONE);
            garis.setVisibility(View.GONE);
        }
    }

    private void openIntent(Class x){
        Intent myIntent = new Intent(getBaseContext(), x);
        myIntent.putExtras(bundle);
        startActivity(myIntent);
    }
}
