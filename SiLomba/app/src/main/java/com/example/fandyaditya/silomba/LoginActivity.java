package com.example.fandyaditya.silomba;

import android.content.Intent;
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

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText nrp;
    EditText password;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nrp = (EditText)findViewById(R.id.login_nrp);
        password = (EditText)findViewById(R.id.login_password);
        loginBtn = (Button)findViewById(R.id.login_btn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "10.151.12.235:3000/login", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                validate(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getBaseContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<>();
                param.put("nrp",nrp.getText().toString());
                param.put("password",password.getText().toString());
                return param;
            }
        };
        RequestQueue newReq = Volley.newRequestQueue(this);
        newReq.add(stringRequest);
    }

    private void validate(String response){
        ParseJSON pj = new ParseJSON(response);
        String status = pj.statusCodeParse();

        if(status.equals("success")){
            openPage(MainActivity.class);
        }
        else{
            Toast.makeText(getBaseContext(),"Login Gagal, Periksa nrp atau password kamu",Toast.LENGTH_LONG).show();
        }
    }

    private void openPage(Class page){
        Intent myIntent = new Intent(getBaseContext(),page);
        startActivity(myIntent);
    }
}
