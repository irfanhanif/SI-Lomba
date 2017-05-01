package com.example.fandyaditya.silomba.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fandyaditya.silomba.R;

/**
 * Created by Fandy Aditya on 4/24/2017.
 */

public class ProfileFragment extends Fragment {
    ImageView profPic;
    TextView namaProfile;
    TextView jurusanProfile;
    TextView angkatanProfile;
    Button aturProfile;
    Button logOut;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.profile_fragment,container,false);

        profPic = (ImageView)rootView.findViewById(R.id.profile_img);
        namaProfile = (TextView)rootView.findViewById(R.id.profile_nama);
        jurusanProfile = (TextView)rootView.findViewById(R.id.profile_jurusan);
        angkatanProfile = (TextView)rootView.findViewById(R.id.profile_angkatan);
        aturProfile = (Button)rootView.findViewById(R.id.profile_atur_btn);
        logOut = (Button)rootView.findViewById(R.id.profile_logout_btn);
        aturProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIntent(AturProfile.class);
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
