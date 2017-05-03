package com.example.fandyaditya.silomba.Profile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fandyaditya.silomba.ParseJSON;
import com.example.fandyaditya.silomba.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AturProfile extends AppCompatActivity {

    EditText namaProfile;
    EditText jurusanProfile;
    Button uploadBtn;
    Button simpanBtn;
    Bundle bundle;
    String profPic;

    String idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atur_profile);

        namaProfile = (EditText)findViewById(R.id.atur_profile_nama);
        jurusanProfile = (EditText)findViewById(R.id.atur_profile_jurusan);
        uploadBtn = (Button)findViewById(R.id.atur_profile_uploadbtn);
        simpanBtn = (Button)findViewById(R.id.atur_profile_simpanbtn);

        getData();
        simpanBtn.setOnClickListener(op);
        uploadBtn.setOnClickListener(op);
    }

    View.OnClickListener op = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.atur_profile_simpanbtn: update();break;
                case R.id.atur_profile_uploadbtn: uploadImg();break;
            }
        }
    };

    private void getData(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "someurl.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                fetchData(response,"get");
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
                param.put("idUser",idUser);
                return param;
            }
        };

        RequestQueue newReq = Volley.newRequestQueue(this);
        newReq.add(stringRequest);
    }

    private void update(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "someurl.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                fetchData(response,"post");
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
                param.put("nama",namaProfile.getText().toString());
                param.put("jurusan",jurusanProfile.getText().toString());
//                param.put("fileProfPic",profPic.getBytes().toString());
                return param;
            }
        };
        RequestQueue newReq = Volley.newRequestQueue(this);
        newReq.add(stringRequest);
    }

    private void uploadImg(){

    }

    private void fetchData(String response,String code){
        ParseJSON pj = new ParseJSON(response);

        if (code.equals("get")){
            List<String> fetchGet = pj.detailUserParse();
            namaProfile.setText(fetchGet.get(0));
            jurusanProfile.setText(fetchGet.get(1));
//            angkatanProfile.setText(fetchGet.get(2));
            profPic = fetchGet.get(3);
        }
        else{
            String status = pj.statusCodeParse();
            if(status.equals("success")){
                Toast.makeText(getBaseContext(),"Profil Berhasil diedit",Toast.LENGTH_LONG).show();
            }
            else Toast.makeText(getBaseContext(),"Gagal Edit Profil",Toast.LENGTH_LONG).show();
        }
    }
}
