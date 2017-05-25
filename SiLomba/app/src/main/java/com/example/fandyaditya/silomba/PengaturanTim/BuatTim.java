package com.example.fandyaditya.silomba.PengaturanTim;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fandyaditya.silomba.Konstanta;
import com.example.fandyaditya.silomba.ParseJSON;
import com.example.fandyaditya.silomba.R;
import com.example.fandyaditya.silomba.Session;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Fandy Aditya on 4/24/2017.
 */

public class BuatTim extends AppCompatActivity {

    EditText namaTim;
    EditText maxAnggota;
    EditText deskripsiTim;
    Button browseBtn;
    Button bikinBtn;
    Bundle bundle;
    String fileTimPic;

    String idUser;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_tim);
        namaTim = (EditText)findViewById(R.id.buat_tim_nama);
        maxAnggota = (EditText)findViewById(R.id.buat_tim_max);
        deskripsiTim = (EditText)findViewById(R.id.buat_tim_deskripsi);
        browseBtn = (Button)findViewById(R.id.buat_tim_browse_btn);
        bikinBtn = (Button)findViewById(R.id.buat_tim_buat_btn);

        bundle = getIntent().getExtras();
        Session session = new Session(getBaseContext());
        idUser = session.getPreferences();

        browseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImg();
            }
        });
        bikinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Buat Tim");
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:finish();break;
        }
        return true;
    }

    private void uploadImg(){

    }

    private void insert(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konstanta.ip+"/createteam", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                validate(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<>();
                param.put("nrp",idUser);
                param.put("nama_tim",namaTim.getText().toString());
                param.put("maksimal_anggota",maxAnggota.getText().toString());
                param.put("deskripsi_tim",deskripsiTim.getText().toString());
                param.put("file_fotoprofil_tim","null");
                return param;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void validate(String response){
        ParseJSON pj = new ParseJSON(response);
        String status = pj.statusCodeParse();

        if(status.equals("success")){
            Toast.makeText(getBaseContext(),"Bikin Tim Sukses",Toast.LENGTH_LONG).show();
            finish();
        }
        else{
            Toast.makeText(getBaseContext(),"Gagal Bikin Tim",Toast.LENGTH_LONG).show();
        }
    }
}
