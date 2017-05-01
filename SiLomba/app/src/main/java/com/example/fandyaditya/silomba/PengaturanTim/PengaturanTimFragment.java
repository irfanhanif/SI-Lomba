package com.example.fandyaditya.silomba.PengaturanTim;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.example.fandyaditya.silomba.ParseJSON;
import com.example.fandyaditya.silomba.PengaturanTim.RequestCalon.RequestCalonAnggota;
import com.example.fandyaditya.silomba.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Fandy Aditya on 4/24/2017.
 */

public class PengaturanTimFragment extends Fragment {

    ImageView timImg;
    TextView namaTim;
    TextView deskripsiTim;
    ImageView anggota1Img;
    ImageView anggota2Img;
    ImageView anggota3Img;
    ImageView anggota4Img;
    ImageView anggota5Img;
    ImageView lombaImg;
    Button aturTim;
    Button requestCalon;
    Bundle bundle;

    String idTim;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.pengaturan_tim,container,false);
        timImg = (ImageView) rootView.findViewById(R.id.tim_img);
        namaTim = (TextView) rootView.findViewById(R.id.tim_nama);
        deskripsiTim = (TextView) rootView.findViewById(R.id.tim_deskripsi);
        anggota1Img = (ImageView) rootView.findViewById(R.id.tim_anggota_1);
        anggota2Img = (ImageView) rootView.findViewById(R.id.tim_anggota_2);
        anggota3Img = (ImageView) rootView.findViewById(R.id.tim_anggota_3);
        anggota4Img = (ImageView) rootView.findViewById(R.id.tim_anggota_4);
        anggota5Img = (ImageView) rootView.findViewById(R.id.tim_anggota_5);
        lombaImg = (ImageView) rootView.findViewById(R.id.tim_lomba_img);
        aturTim = (Button) rootView.findViewById(R.id.atur_tim_btn);
        requestCalon = (Button) rootView.findViewById(R.id.request_calon_btn);

        aturTim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIntent(AturTim.class);
            }
        });
        requestCalon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIntent(RequestCalonAnggota.class);
            }
        });

        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
//        bundle = g

    }

    private void getData(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "someurl.com", new Response.Listener<String>() {
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
                param.put("idTim",idTim);
                return param;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    private void fetchData(String response){
        ParseJSON pj = new ParseJSON(response);
        List<String> mainData = pj.detailTimParse("maindata");
        List<String> secondData = pj.detailTimParse("seconddata");

        namaTim.setText(mainData.get(0));
        deskripsiTim.setText(mainData.get(1));
        Glide.with(getContext()).load(mainData.get(3)).into(timImg);
        Glide.with(getContext()).load(mainData.get(5)).into(lombaImg);
    }

    private void openIntent(Class x){
        Intent openPage = new Intent(getContext(),x);
        startActivity(openPage);
    }
}
