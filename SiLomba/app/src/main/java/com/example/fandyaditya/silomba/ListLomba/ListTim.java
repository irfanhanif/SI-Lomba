package com.example.fandyaditya.silomba.ListLomba;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListTim extends AppCompatActivity {

    RecyclerView rv;

    String idLomba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tim);
        getSupportActionBar().setTitle("List Tim Ikut Lomba");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        idLomba = bundle.getString("idLomba");

        rv = (RecyclerView)findViewById(R.id.list_tim_rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        getData();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:finish();break;
        }
        return true;
    }

    private void getData(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konstanta.ip + "/listtimikutserta", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                fetchData(response);
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
                param.put("id_lomba",idLomba);
                return param;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void fetchData(String response){
        ParseJSON pj = new ParseJSON(response);
        List<ListTimObjek> dataq = pj.listTimIkutSerta();
        ListTimAdapter listTimAdapter = new ListTimAdapter(dataq,getBaseContext());
        rv.setAdapter(listTimAdapter);
    }
}
