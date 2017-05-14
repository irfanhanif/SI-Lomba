package com.example.fandyaditya.silomba.Bimbingan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class BikinBimbingan extends AppCompatActivity {

    EditText tanggalBimbingan;
    EditText comment;
    Button upload;
    Button simpan;
    Bundle bundle;

    String idTim;
    String idDosbing;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bikin_bimbingan);
        
        tanggalBimbingan = (EditText)findViewById(R.id.bikin_bimbingan_tgl);
        comment = (EditText)findViewById(R.id.bikin_bimbingan_comment);
        upload = (Button)findViewById(R.id.bikin_bimbingan_uploadbtn);
        simpan = (Button)findViewById(R.id.bikin_bimbingan_simpanbtn);
        bundle = getIntent().getExtras();

        idTim = bundle.getString("idTim");
        idDosbing = bundle.getString("idDosbing");
        simpan.setOnClickListener(op);
        upload.setOnClickListener(op);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Bikin Bimbingan");
    }

    @Override
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
                case R.id.bikin_bimbingan_uploadbtn : uploadFile();break;
                case R.id.bikin_bimbingan_simpanbtn : insert();break;
            }
        }
    };

    private void uploadFile(){

    }
    private void insert(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "someurl.com", new Response.Listener<String>() {
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
                param.put(idTim,"idTim");
                param.put(idDosbing,"idDosbing");
                param.put(tanggalBimbingan.getText().toString(),"tglBimbingan");
                param.put(comment.getText().toString(),"commentBimbingan");
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
