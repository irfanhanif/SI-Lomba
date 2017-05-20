package com.example.fandyaditya.silomba.ListLomba;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
import com.example.fandyaditya.silomba.Konstanta;
import com.example.fandyaditya.silomba.ParseJSON;
import com.example.fandyaditya.silomba.PengaturanTim.ListTim.ListPengaturanTimObjek;
import com.example.fandyaditya.silomba.PengaturanTim.ListTim.PengaturanTimAdapter;
import com.example.fandyaditya.silomba.R;
import com.example.fandyaditya.silomba.Session;

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
    TextView syaratLomba;
    Button ikutSertaBtn;
    Button listTimBtn;
    Bundle bundle;

    public static String idLomba;
    String namaLombaVal;
    String kategoriLombaVal;
    String syaratLombaVal;
    String deskripsiLombaVal;
    String hadiahLombaVal;
    String penyelenggaraVal;

    RecyclerView rv;

    String idUser;


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
        syaratLomba = (TextView)findViewById(R.id.detail_lomba_syarat);
        ikutSertaBtn = (Button)findViewById(R.id.detail_lomba_ikutserta_Btn);
        listTimBtn = (Button)findViewById(R.id.detail_lomba_listtim_btn);
        bundle = getIntent().getExtras();
        idLomba = bundle.getString("idLomba");
        namaLombaVal = bundle.getString("namaLomba");
        kategoriLombaVal = bundle.getString("kategoriLomba");
        syaratLombaVal = bundle.getString("syaratLomba");
        deskripsiLombaVal = bundle.getString("deskripsiLomba");
        hadiahLombaVal = bundle.getString("hadiahLomba");
        penyelenggaraVal = bundle.getString("penyelenggaraLomba");

        Session session = new Session(getBaseContext());
        idUser = session.getPreferences();

//        idTim = bundle.getString("idTim");
//        idUser = bundle.getString("idUser");

        listTimBtn.setOnClickListener(op);
        ikutSertaBtn.setOnClickListener(op);

//        getData();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail Lomba");
        setView();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:finish();break;
        }
        return true;
    }

    private void setView(){
        namaLomba.setText(namaLombaVal);
        kategoriLomba.setText(kategoriLombaVal);
        syaratLomba.setText(syaratLombaVal);
        deskripsiLomba.setText(deskripsiLombaVal);
        hadiahLomba.setText(hadiahLombaVal);
        penyelenggaraLomba.setText(penyelenggaraVal);
    }
    View.OnClickListener op = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.detail_lomba_listtim_btn:openIntent(ListTim.class);break;
                case R.id.detail_lomba_ikutserta_Btn:openDialog();break;
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
        else if(statusCode==1){
            String status = pj.statusCodeParse();
            if(status.equals("success")){
                Toast.makeText(getBaseContext(),"Ikut Lomba Sukses",Toast.LENGTH_SHORT).show();
                finish();
            }
            else
                Toast.makeText(getBaseContext(),"Ikut Lomba Gagal",Toast.LENGTH_SHORT).show();
        }
        else{
            List<ListPengaturanTimObjek> data = pj.listTimParse("other");
            PengaturanTimAdapter pengaturanTimAdapter = new PengaturanTimAdapter(data,getBaseContext(),"listikuttim");
            rv.setAdapter(pengaturanTimAdapter);
        }
    }
    private void openIntent(Class page){
        Bundle bundle = new Bundle();
        bundle.putString("idLomba",idLomba);
        Intent myIntent = new Intent(getBaseContext(),page);
        myIntent.putExtras(bundle);
        startActivityForResult(myIntent,0);
    }
    private void showListTim(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konstanta.ip + "/listtim", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                fetchData(response,2);
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
                return param;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void openDialog(){
        View v = LayoutInflater.from(this).inflate(R.layout.dialog_ikutlomba,null);
        AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
        myDialog.setView(v);
        rv = (RecyclerView)v.findViewById(R.id.dialog_ikutlomba_rv);
        rv.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        showListTim();
        myDialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        myDialog.show();
    }
}
