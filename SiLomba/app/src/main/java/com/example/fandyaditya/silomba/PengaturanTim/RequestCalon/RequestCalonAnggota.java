package com.example.fandyaditya.silomba.PengaturanTim.RequestCalon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestCalonAnggota extends AppCompatActivity {

    RecyclerView rv;
    Bundle bundle;

    String idTim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_calon_anggota);
        rv = (RecyclerView)findViewById(R.id.request_calon_rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        bundle = getIntent().getExtras();
        idTim = bundle.getString("idTim");

        getData();
    }

    private void getData(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konstanta.ip+"/listrequest", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                fetchData(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<>();
                param.put("id_tim",idTim);
                return param;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void fetchData(String response){
        ParseJSON pj = new ParseJSON(response);
        List<RequestCalonObjek> requestCalonObjekList = pj.listRequestCalon();
        RequestCalonAdapter requestCalonAdapter = new RequestCalonAdapter(requestCalonObjekList,this);
        rv.setAdapter(requestCalonAdapter);
    }
}
