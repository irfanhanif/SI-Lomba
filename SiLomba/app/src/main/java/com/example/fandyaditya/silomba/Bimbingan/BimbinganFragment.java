package com.example.fandyaditya.silomba.Bimbingan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fandyaditya.silomba.Bimbingan.Dosen.ListDosen;
import com.example.fandyaditya.silomba.Konstanta;
import com.example.fandyaditya.silomba.ParseJSON;
import com.example.fandyaditya.silomba.PengaturanTim.ListTim.ListPengaturanTimObjek;
import com.example.fandyaditya.silomba.R;
import com.example.fandyaditya.silomba.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Fandy Aditya on 4/24/2017.
 */

public class BimbinganFragment extends Fragment {
    RecyclerView rv;
    LinearLayout inputBimbingan;
    LinearLayout listDosen;
    Spinner spinner;

    String idTim;
    String nrp;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bimbingan,container,false);
        rv = (RecyclerView)rootView.findViewById(R.id.list_bimbingan_rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        inputBimbingan = (LinearLayout) rootView.findViewById(R.id.bimbingan_inputbtn);
        listDosen = (LinearLayout) rootView.findViewById(R.id.bimbingan_dosenbtn);
        spinner = (Spinner)rootView.findViewById(R.id.list_bimbingan_spinner);

        Session session = new Session(getContext());
        nrp = session.getPreferences();

        inputBimbingan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIntent(BikinBimbingan.class);
            }
        });
        listDosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIntent(ListDosen.class);
            }
        });
//        spinner.setOnItemSelectedListener(op);
        getSpinner();
        getData();
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        idTim = getArguments().getString("idTim");
//        getData();

    }
    private void getSpinner(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konstanta.ip+"/detailtim", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                fetchDataSpinner(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<>();
                param.put("nrp",nrp);
                return param;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
    private void fetchDataSpinner(String response){
        ParseJSON pj = new ParseJSON(response);
        List<ListPengaturanTimObjek> dataTimku = pj.listTimParse("my_team");
        List<String> listNama = new ArrayList<>();
        for (int i=0;i<dataTimku.size();i++){
            String nameAndId = (dataTimku.get(i).getNamaTim())+"|"+dataTimku.get(i).getIdTim();
            listNama.add(nameAndId);
        }
        ArrayAdapter<String> arraySpinner = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,listNama);
        spinner.setAdapter(arraySpinner);
    }

//    AdapterView.OnItemSelectedListener op = new AdapterView.OnItemSelectedListener() {
//        @Override
//        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//    };

    private void getData(){
//        String selectedItem = spinner.getSelectedItem().toString();
//        final String[] splitString = selectedItem.split("|");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konstanta.ip+"/semuabimbingan", new Response.Listener<String>() {
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
                param.put("id_tim","idtim");
                return param;
            }
        };
        RequestQueue newReq = Volley.newRequestQueue(getContext());
        newReq.add(stringRequest);
    }

    private void fetchData(String response){
        ParseJSON pj = new ParseJSON(response);
        List<BimbinganObjek> bimbinganObjekList = pj.listBimbingan();
        BimbinganAdapter bimbinganAdapter = new BimbinganAdapter(getContext(),bimbinganObjekList);
        rv.setAdapter(bimbinganAdapter);
    }
    private void openIntent(Class x){
        Intent openPage = new Intent(getContext(),x);
        startActivity(openPage);
    }
}
