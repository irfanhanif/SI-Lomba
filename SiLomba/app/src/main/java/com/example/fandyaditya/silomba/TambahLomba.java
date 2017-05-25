package com.example.fandyaditya.silomba;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TambahLomba extends AppCompatActivity {

    Button browse;
    Button submit;
    EditText nama;
    EditText penyelenggara;
    EditText hadiah;
    EditText kategori;
    EditText syarat;
    EditText deskripsi;
    String fileProfPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_lomba);

        browse = (Button)findViewById(R.id.tambah_lomba_browsebtn);
        submit = (Button)findViewById(R.id.tambah_lomba_submitbtn);
        nama = (EditText) findViewById(R.id.tambah_lomba_nama);
        penyelenggara = (EditText)findViewById(R.id.tambah_lomba_penyelenggara);
        hadiah = (EditText)findViewById(R.id.tambah_lomba_hadiah);
        kategori = (EditText)findViewById(R.id.tambah_lomba_kategori);
        syarat = (EditText)findViewById(R.id.tambah_lomba_syarat);
        deskripsi = (EditText)findViewById(R.id.tambah_lomba_deskripsi);
        submit.setOnClickListener(op);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Tambah Lomba");
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:finish();break;
        }
        return true;
    }
    View.OnClickListener op = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tambah_lomba_submitbtn:insertLomba();finish();break;
            }
        }
    };
    private void insertLomba(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konstanta.ip + "/lombabaru", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                fetchData(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getBaseContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<>();
                param.put("nama_lomba",nama.getText().toString());
                param.put("penyelenggara",penyelenggara.getText().toString());
                param.put("kategori",kategori.getText().toString());
                param.put("hadiah",hadiah.getText().toString());
                param.put("syarat",syarat.getText().toString());
                param.put("deskripsi_lomba",deskripsi.getText().toString());
                return param;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void fetchData(String response){
        ParseJSON pj = new ParseJSON(response);
        String status = pj.statusCodeParse();
        if(status.equals("success")){
            Toast.makeText(getBaseContext(),"Input lomba berhasil!!",Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            Toast.makeText(getBaseContext(),"Input lomba gagal!!, periksa kembali inputan",Toast.LENGTH_SHORT).show();
        }
    }
}
