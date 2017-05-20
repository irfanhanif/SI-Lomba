package com.example.fandyaditya.silomba.PengaturanTim.ListTim;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.fandyaditya.silomba.Konstanta;
import com.example.fandyaditya.silomba.ListLomba.ListLombaObjek;
import com.example.fandyaditya.silomba.ParseJSON;
import com.example.fandyaditya.silomba.PengaturanTim.BuatTim;
import com.example.fandyaditya.silomba.PengaturanTim.RequestCalon.RequestCalonAnggota;
import com.example.fandyaditya.silomba.R;
import com.example.fandyaditya.silomba.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Fandy Aditya on 4/24/2017.
 */

public class PengaturanTimFragment extends Fragment {


    RecyclerView rvKetua;
    RecyclerView rvAnggota;

    Button bikinTim;
    String idUser;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.pengaturan_tim,container,false);

        rvKetua = (RecyclerView) rootView.findViewById(R.id.pengaturan_timrv_ketua);
        rvAnggota = (RecyclerView)rootView.findViewById(R.id.pengaturan_timrv_anggota);

        rvKetua.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        rvAnggota.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        bikinTim = (Button)rootView.findViewById(R.id.pengaturan_tim_btn);
        Session session = new Session(getContext());

        idUser = session.getPreferences();

        bikinTim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIntent(BuatTim.class);
            }
        });

        getData();

        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getData();
//        bundle = g

    }

    private void getData(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konstanta.ip+"/detailtim", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                fetchData(response);
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
                param.put("nrp",idUser);
                return param;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    private void fetchData(String response){
        ParseJSON pj = new ParseJSON(response);
//        try {
//            JSONObject jsonObject = new JSONObject(response);
//            JSONArray jsonArray = jsonObject.getJSONArray("joined_team");
//            for(int i=0;i<jsonArray.length();i++){
//                JSONObject jRow = jsonArray.getJSONObject(i);
//                JSONObject jsonObject1 = jRow.getJSONObject("tim");
//                Log.d("testing", jsonObject1.getString("nama_tim"));
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


        List<ListPengaturanTimObjek> dataTimku = pj.listTimParse("my_team");
        List<ListPengaturanTimObjek> dataTimMu = pj.listTimParse("joined_team");

        PengaturanTimAdapter timKuAdapter = new PengaturanTimAdapter(dataTimku,getContext(),"pengaturantim");
        PengaturanTimAdapter timMuAdapter = new PengaturanTimAdapter(dataTimMu,getContext(),"pengaturantim");

        rvKetua.setAdapter(timKuAdapter);
        rvAnggota.setAdapter(timMuAdapter);
    }

    private void openIntent(Class x){
        Intent openPage = new Intent(getContext(),x);
        startActivity(openPage);
    }
}
