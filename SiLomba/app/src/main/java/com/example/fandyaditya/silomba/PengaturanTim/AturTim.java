package com.example.fandyaditya.silomba.PengaturanTim;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fandyaditya.silomba.Konstanta;
import com.example.fandyaditya.silomba.ParseJSON;
import com.example.fandyaditya.silomba.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AturTim extends AppCompatActivity {
    EditText namaTim;
    EditText deskripsiTim;
    Button browse;
    Button simpan;
    Bundle bundle;
    String picTim;

    String idTim;
    String namaTimVal;
    String deskripsiTimVal;
    String maxAnggota;
    String profPic;
    String tipeTim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atur_tim);
        namaTim = (EditText)findViewById(R.id.atur_tim_namatim);
        deskripsiTim = (EditText)findViewById(R.id.atur_tim_deskripsi);
        browse = (Button)findViewById(R.id.atur_tim_browse);
        simpan = (Button)findViewById(R.id.atur_tim_simpanbtn);

        bundle = getIntent().getExtras();

        idTim = bundle.getString("idTim");
        namaTimVal = bundle.getString("namaTim");
        maxAnggota = bundle.getString("maxAnggota");
        deskripsiTimVal = bundle.getString("deskripsiTim");
        profPic = bundle.getString("profPic");
        tipeTim = bundle.getString("tipeTim");

        browse.setOnClickListener(op);
        simpan.setOnClickListener(op);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Atur Tim");

//        getData();
        setView();

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
                case R.id.atur_tim_simpanbtn:update();break;
                case R.id.atur_tim_browse:upload();break;
            }
        }
    };

    private void setView(){
        namaTim.setText(namaTimVal);
        deskripsiTim.setText(deskripsiTimVal);
    }

    private void update(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konstanta.ip+"/editteam", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                fetchData(response,"post");
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
                param.put("nama_tim",namaTim.getText().toString());
                param.put("deskripsi_tim",deskripsiTim.getText().toString());
                param.put("maksimal_anggota",maxAnggota);
                param.put("file_fotoprofil_tim","null");
                param.put("id_tim",idTim);
                return param;
            }
        };
        RequestQueue newReq = Volley.newRequestQueue(this);
        newReq.add(stringRequest);
    }
    private void upload(){

    }

    private void getData(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "someurl.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                fetchData(response,"get");
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
                param.put("idTim",idTim);
                return param;
            }

        };
        RequestQueue newReq = Volley.newRequestQueue(this);
        newReq.add(stringRequest);
    }
    private void fetchData(String response, String code){
        ParseJSON pj = new ParseJSON(response);
        if(code.equals("get")){
            List<String> fetchGet = pj.detailTimParse("maindata");
            namaTim.setText(fetchGet.get(0));
            deskripsiTim.setText(fetchGet.get(1));
            picTim = fetchGet.get(3);
        }
        else{
            String status = pj.statusCodeParse();
            if(status.equals("success")){
                Toast.makeText(getBaseContext(),"Halaman tim Berhasil diedit",Toast.LENGTH_LONG).show();
                finish();
            }
            else Toast.makeText(getBaseContext(),"Gagal Edit Halaman Tim",Toast.LENGTH_LONG).show();
        }
    }
}
