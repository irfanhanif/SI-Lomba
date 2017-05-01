package com.example.fandyaditya.silomba.Profile;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.fandyaditya.silomba.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Fandy Aditya on 4/24/2017.
 */

public class ProfileFragment extends Fragment {
    ImageView profPic;
    TextView namaProfile;
    TextView jurusanProfile;
    TextView angkatanProfile;
    Button aturProfile;
    Button logOut;

    String idUser;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.profile_fragment,container,false);

        profPic = (ImageView)rootView.findViewById(R.id.profile_img);
        namaProfile = (TextView)rootView.findViewById(R.id.profile_nama);
        jurusanProfile = (TextView)rootView.findViewById(R.id.profile_jurusan);
        angkatanProfile = (TextView)rootView.findViewById(R.id.profile_angkatan);
        aturProfile = (Button)rootView.findViewById(R.id.profile_atur_btn);
        logOut = (Button)rootView.findViewById(R.id.profile_logout_btn);
        aturProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIntent(AturProfile.class);
            }
        });
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();

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
                param.put("idUser",idUser);
                return param;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
    private void fetchData(String response){
        ParseJSON pj = new ParseJSON(response);
        List<String> data = pj.detailUserParse();
        namaProfile.setText(data.get(0));
        jurusanProfile.setText(data.get(1));
        angkatanProfile.setText(data.get(2));
        Glide.with(getContext()).load(data.get(3)).into(profPic);
    }
    private void openIntent(Class x){
        Intent openPage = new Intent(getContext(),x);
        startActivity(openPage);
    }
}
