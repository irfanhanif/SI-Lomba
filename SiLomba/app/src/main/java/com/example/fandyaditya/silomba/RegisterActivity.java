package com.example.fandyaditya.silomba;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

public class RegisterActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    EditText ulangiPassword;
    EditText namaUser;
    EditText nrpUser;
    Spinner jurusan;

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
    }
}
