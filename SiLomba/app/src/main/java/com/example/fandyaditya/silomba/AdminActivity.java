package com.example.fandyaditya.silomba;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity {

    Button tambahLomba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        tambahLomba = (Button)findViewById(R.id.admin_tambahlomba_btn);

        setTitle("Halaman Admin");

        tambahLomba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),TambahLomba.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Session session = new Session(getBaseContext());
        session.removePreferences();
        finish();
    }
}
