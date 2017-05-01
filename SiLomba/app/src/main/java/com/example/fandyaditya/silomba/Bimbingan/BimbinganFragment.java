package com.example.fandyaditya.silomba.Bimbingan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.fandyaditya.silomba.Bimbingan.Dosen.ListDosen;
import com.example.fandyaditya.silomba.R;

/**
 * Created by Fandy Aditya on 4/24/2017.
 */

public class BimbinganFragment extends Fragment {
    RecyclerView rv;
    Button inputBimbingan;
    Button listDosen;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bimbingan,container,false);
        rv = (RecyclerView)rootView.findViewById(R.id.list_bimbingan_rv);
        inputBimbingan = (Button)rootView.findViewById(R.id.bimbingan_inputbtn);
        listDosen = (Button)rootView.findViewById(R.id.bimbingan_dosenbtn);
        inputBimbingan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIntent(BikinBimbingan.class);
            }
        });
        listDosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIntent(ListDosen.class);
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
