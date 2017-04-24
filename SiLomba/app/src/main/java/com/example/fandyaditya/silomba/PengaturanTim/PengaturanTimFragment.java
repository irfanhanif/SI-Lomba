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

import com.example.fandyaditya.silomba.PengaturanTim.RequestCalon.RequestCalonAnggota;
import com.example.fandyaditya.silomba.R;

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

    }
    private void openIntent(Class x){
        Intent openPage = new Intent(getContext(),x);
        startActivity(openPage);
    }
}
