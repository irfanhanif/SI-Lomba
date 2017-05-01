package com.example.fandyaditya.silomba.PengaturanTim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.example.fandyaditya.silomba.ParseJSON;
import com.example.fandyaditya.silomba.R;

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
        idUser = bundle.getString("idUser");

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
    }

    private void uploadImg(){

    }

    private void insert(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "somuerl.com", new Response.Listener<String>() {
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
                param.put("idUser",idUser);
                param.put("namaTim",namaTim.getText().toString());
                param.put("maxAnggota",maxAnggota.getText().toString());
                param.put("deskripsiTim",deskripsiTim.getText().toString());
                param.put("fileTimPic",fileTimPic);
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
            Toast.makeText(getBaseContext(),"Bikin Bimbingan Sukses",Toast.LENGTH_LONG).show();
            finish();
        }
        else{
            Toast.makeText(getBaseContext(),"Gagal Bikin Bimbingan",Toast.LENGTH_LONG).show();
        }
    }
}
