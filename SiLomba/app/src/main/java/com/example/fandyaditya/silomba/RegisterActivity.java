package com.example.fandyaditya.silomba;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    EditText ulangiPassword;
    EditText namaUser;
    EditText nrpUser;
    Spinner jurusan;
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText)findViewById(R.id.register_username);
        password = (EditText)findViewById(R.id.register_password);
        ulangiPassword = (EditText)findViewById(R.id.register_ulangipassword);
        namaUser = (EditText)findViewById(R.id.register_nama);
        nrpUser = (EditText)findViewById(R.id.register_nrp);
        jurusan = (Spinner) findViewById(R.id.register_jurusan_spnr);
        registerBtn = (Button) findViewById(R.id.register_registerbtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                errorChecking();
            }
        });
    }

    private void errorChecking(){
        if(username.length()<=3 || password.length()<=3){
            Toast.makeText(getBaseContext(),"username atau password kurang panjang",Toast.LENGTH_SHORT).show();
        }
        else if(namaUser.length()==0||nrpUser.length()==0){
            Toast.makeText(getBaseContext(),"Nama dan NRP harus diisi!!",Toast.LENGTH_LONG).show();
        }
        else {
            register();
        }
    }
    private void register(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "someurl.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                validate(response);
        }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getBaseContext(),"server error, cek koneksi internet anda",Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<>();
                param.put("username",username.getText().toString().trim());
                param.put("password",username.getText().toString().trim());
                param.put("nama",namaUser.getText().toString().trim());
                param.put("nrp",nrpUser.getText().toString().trim());
                param.put("jurusan",jurusan.getSelectedItem().toString());
                return param;
            }
        };
        RequestQueue newReq = Volley.newRequestQueue(this);
        newReq.add(stringRequest);
    }

    private void validate(String response){
        ParseJSON pj = new ParseJSON(response);
        String status = pj.registerParse();
        if(status.equals("success")){
            Toast.makeText(getBaseContext(),"Register Sukses",Toast.LENGTH_SHORT).show();
            finish();
        }
        else {
            Toast.makeText(getBaseContext(),"Register Gagal",Toast.LENGTH_SHORT).show();
        }
    }
}
