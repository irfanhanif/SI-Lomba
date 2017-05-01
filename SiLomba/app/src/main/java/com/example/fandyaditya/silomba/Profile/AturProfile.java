package com.example.fandyaditya.silomba.Profile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.fandyaditya.silomba.R;

public class AturProfile extends AppCompatActivity {

    EditText namaProfile;
    EditText jurusanProfile;
    Button uploadBtn;
    Button simpanBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atur_profile);

        namaProfile = (EditText)findViewById(R.id.atur_profile_nama);
        jurusanProfile = (EditText)findViewById(R.id.atur_profile_jurusan);
        uploadBtn = (Button)findViewById(R.id.atur_profile_uploadbtn);
        simpanBtn = (Button)findViewById(R.id.atur_profile_simpanbtn);
    }
}
