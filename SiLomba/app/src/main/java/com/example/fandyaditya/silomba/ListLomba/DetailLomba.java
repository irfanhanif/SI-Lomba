package com.example.fandyaditya.silomba.ListLomba;

import android.media.Image;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fandyaditya.silomba.R;

public class DetailLomba extends AppCompatActivity {

    ImageView lombaImg;
    TextView namaLomba;
    TextView penyelenggaraLomba;
    TextView kategoriLomba;
    TextView hadiahLomba;
    TextView deskripsiLomba;
    TextView linkLomba;
    Button ikutSertaBtn;
    Button listTimBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_lomba);

        lombaImg = (ImageView)findViewById(R.id.detail_lomba_img);
        namaLomba = (TextView)findViewById(R.id.detail_lomba_nama);
        penyelenggaraLomba = (TextView)findViewById(R.id.detail_lomba_penyelenggara);
        kategoriLomba = (TextView)findViewById(R.id.detail_lomba_kategori);
        hadiahLomba = (TextView)findViewById(R.id.detail_lomba_hadiah);
        deskripsiLomba = (TextView)findViewById(R.id.detail_lomba_deskripsi);
        linkLomba = (TextView)findViewById(R.id.detail_lomba_web);
        ikutSertaBtn = (Button)findViewById(R.id.detail_lomba_ikutserta_Btn);
        listTimBtn = (Button)findViewById(R.id.detail_lomba_listtim_btn);
    }
}
