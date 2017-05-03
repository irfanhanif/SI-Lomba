package com.example.fandyaditya.silomba.Bimbingan.Dosen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fandyaditya.silomba.ParseJSON;
import com.example.fandyaditya.silomba.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListDosen extends AppCompatActivity {

    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_dosen);

        rv = (RecyclerView)findViewById(R.id.list_dosen_rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        getData();
    }

    private void getData(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "someurl.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                fetchData(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void fetchData(String response){
        ParseJSON pj = new ParseJSON(response);
        List<DosenObjek> dataDosen = pj.listDosen();
        DosenAdapter dosenAdapter = new DosenAdapter(dataDosen,this);
        rv.setAdapter(dosenAdapter);
    }
}
