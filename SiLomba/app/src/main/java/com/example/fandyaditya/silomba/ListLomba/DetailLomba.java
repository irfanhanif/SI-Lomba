package com.example.fandyaditya.silomba.ListLomba;

import android.content.Intent;
import android.media.Image;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.fandyaditya.silomba.ParseJSON;
import com.example.fandyaditya.silomba.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailLomba extends AppCompatActivity {

    ImageView lombaImg;
    TextView namaLomba;
    TextView penyelenggaraLomba;
    TextView kategoriLomba;
    TextView hadiahLomba;
    TextView deskripsiLomba;
    TextView linkLomba;
    Button ikutSertaBtn;
    Button listTimBtn;
    Bundle bundle;

    String idLomba;
    String idTim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_lomba);

        lombaImg = (ImageView)findViewById(R.id.detail_lomba_img);
        namaLomba = (TextView)findViewById(R.id.detail_lomba_nama);
        penyelenggaraLomba = (TextView)findViewById(R.id.detail_lomba_penyelenggara);
        kategoriLomba = (TextView)findViewById(R.id.detail_lomba_kategori);
        hadiahLomba = (TextView)findViewById(R.id.detail_lomba_hadiah);
        deskripsiLomba = (TextView)findViewById(R.id.detail_lomba_deskripsi);
        linkLomba = (TextView)findViewById(R.id.detail_lomba_web);
        ikutSertaBtn = (Button)findViewById(R.id.detail_lomba_ikutserta_Btn);
        listTimBtn = (Button)findViewById(R.id.detail_lomba_listtim_btn);
        bundle = getIntent().getExtras();
        idLomba = bundle.getString("idLomba");
        idTim = bundle.getString("idTim");

        listTimBtn.setOnClickListener(op);
        ikutSertaBtn.setOnClickListener(op);

        getData();
    }
    View.OnClickListener op = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.detail_lomba_listtim_btn:openIntent(ListTim.class);break;
                case R.id.detail_lomba_ikutserta_Btn:ikutSerta();break;
            }
        }
    };

    private void getData(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "someurl.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                fetchData(response,0);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getBaseContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<>();
                param.put("idLomba",idLomba);
                return param;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void fetchData(String response,int statusCode){
        ParseJSON pj = new ParseJSON(response);
        if(statusCode==0){
            List<String> data = pj.detailLombaParse();
            namaLomba.setText(data.get(0));
            penyelenggaraLomba.setText(data.get(1));
            kategoriLomba.setText(data.get(2));
            hadiahLomba.setText(data.get(3));
            deskripsiLomba.setText(data.get(4));
            Glide.with(this).load(data.get(5)).into(lombaImg);
        }
        else{
            String status = pj.statusCodeParse();
            if(status.equals("success")){
                Toast.makeText(getBaseContext(),"Ikut Lomba Sukses",Toast.LENGTH_SHORT).show();
                finish();
            }
            else
                Toast.makeText(getBaseContext(),"Ikut Lomba Gagal",Toast.LENGTH_SHORT).show();
        }
    }
    private void ikutSerta(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "somuerl.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                fetchData(response, 1);
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
                param.put("idLomba",idLomba);
                return param;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void openIntent(Class page){
        Intent myIntent = new Intent(getBaseContext(),page);
        startActivityForResult(myIntent,0);
    }
}
