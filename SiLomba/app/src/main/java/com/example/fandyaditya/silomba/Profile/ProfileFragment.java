package com.example.fandyaditya.silomba.Profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.example.fandyaditya.silomba.LoginActivity;
import com.example.fandyaditya.silomba.ParseJSON;
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

public class ProfileFragment extends Fragment {
    ImageView profPic;
    TextView namaProfile;
    TextView jurusanProfile;
    TextView angkatanProfile;
    LinearLayout aturProfile;
    LinearLayout logOut;

    View rootView;


    Bundle bundle;
    String idUser;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.profile_fragment,container,false);
        final Session session = new Session(getContext());

        profPic = (ImageView)rootView.findViewById(R.id.profile_img);
        namaProfile = (TextView)rootView.findViewById(R.id.profile_nama);
        jurusanProfile = (TextView)rootView.findViewById(R.id.profile_jurusan);
//        angkatanProfile = (TextView)rootView.findViewById(R.id.profile_angkatan);
        aturProfile = (LinearLayout) rootView.findViewById(R.id.profile_atur_btn);
        logOut = (LinearLayout) rootView.findViewById(R.id.profile_logout_btn);
        aturProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIntent(AturProfile.class);
            }
        });
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOutApps();
            }
        });

        idUser = session.getPreferences();
        getData();
//        Bundle bundle = this.getArguments();
//        idUser = bundle.getString("idUser");
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==101 && resultCode==AturProfile.RESULT_OK){
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(getParentFragment()).attach(getParentFragment()).commit();
        }
    }

    private void getData(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konstanta.ip+"/editprofile", new Response.Listener<String>() {
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
        List<String> data = pj.detailUserParse();
        namaProfile.setText(data.get(0));
        jurusanProfile.setText(data.get(1));
//        angkatanProfile.setText(data.get(2));
        String picPath = Konstanta.ip+"/file/"+data.get(3);
        if(data.get(3).length()<=10){

        }
        else{
            Glide.with(getContext()).load(picPath).into(profPic);
        }

    }
    private void openIntent(Class x){
        Intent openPage = new Intent(getContext(),x);
        Bundle bundle = new Bundle();
        bundle.putString("idUser",idUser);
        openPage.putExtras(bundle);
        startActivityForResult(openPage,101);
    }
    private void logOutApps(){
        Session session = new Session(getContext());
        session.removePreferences();
        Intent launchNextActivity;
        launchNextActivity = new Intent(getContext(), LoginActivity.class);
        launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(launchNextActivity);
    }
}
