package com.example.fandyaditya.silomba.Profile;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
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
import com.example.fandyaditya.silomba.FilePath;
import com.example.fandyaditya.silomba.Konstanta;
import com.example.fandyaditya.silomba.MainActivity;
import com.example.fandyaditya.silomba.ParseJSON;
import com.example.fandyaditya.silomba.R;
import com.example.fandyaditya.silomba.Session;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class AturProfile extends AppCompatActivity {

    EditText namaProfile;
    EditText jurusanProfile;
    EditText passwordProfile;
    EditText ulangiPassword;
    Button uploadBtn;
    Button simpanBtn;
    Bundle bundle;
    String profPic;

    Uri filePic;

    String idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atur_profile);

        requestStoragePermission();

        namaProfile = (EditText)findViewById(R.id.atur_profile_nama);
        jurusanProfile = (EditText)findViewById(R.id.atur_profile_jurusan);
        passwordProfile = (EditText)findViewById(R.id.atur_profile_password);
        ulangiPassword = (EditText)findViewById(R.id.atur_profile_ulangipassword);
        uploadBtn = (Button)findViewById(R.id.atur_profile_uploadbtn);
        simpanBtn = (Button)findViewById(R.id.atur_profile_simpanbtn);

        simpanBtn.setOnClickListener(op);
        uploadBtn.setOnClickListener(op);
        filePic = null;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Atur Profile");

        Session session = new Session(getBaseContext());

        idUser = session.getPreferences();
//        Bundle bundle = getIntent().getExtras();
//        idUser = bundle.getString("idUser");
        getData();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:finish();break;
        }
        return true;
    }

    View.OnClickListener op = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.atur_profile_simpanbtn:{
                    if(passwordProfile.getText().toString().compareTo(ulangiPassword.getText().toString())==0){
                        if(filePic==null){
                            update();
                        }
                        else uploadImg();
                        finish();
                    }
                    else {
                        Toast.makeText(getBaseContext(),"Password tidak sama",Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case R.id.atur_profile_uploadbtn: take_gallery(0);break;
            }
        }
    };
    public void take_gallery(int choice){
        Intent ambil_foto = new Intent (Intent.ACTION_GET_CONTENT,null);
        ambil_foto.setType("image/*");
        startActivityForResult(ambil_foto,choice);
    }

    private void getData(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konstanta.ip+"/editprofile", new Response.Listener<String>() {
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
                param.put("nrp",idUser);
                return param;
            }
        };

        RequestQueue newReq = Volley.newRequestQueue(this);
        newReq.add(stringRequest);
    }

    private void update(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konstanta.ip+"/changeprofile", new Response.Listener<String>() {
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
                param.put("password",passwordProfile.getText().toString());
                param.put("nrp",idUser);
//                param.put("fileProfPic",profPic.getBytes().toString());
                return param;
            }
        };
        RequestQueue newReq = Volley.newRequestQueue(this);
        newReq.add(stringRequest);
    }

    private void uploadImg(){
        Toast.makeText(getBaseContext(),"Sedang mengupload file, harap tunggu . . .",Toast.LENGTH_LONG).show();
        try {
            String uploadId = UUID.randomUUID().toString();
            String path = FilePath.getPath(getBaseContext(),filePic);
            new MultipartUploadRequest(this,uploadId,Konstanta.ip+"/changeprofile")
                    .addFileToUpload(path,"file")
                    .addParameter(namaProfile.getText().toString(),"nama")
                    .addParameter(jurusanProfile.getText().toString(),"jurusan")
                    .addParameter(passwordProfile.getText().toString(),"password")
                    .addParameter(idUser,"nrp")
                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(2)
                    .startUpload();
        } catch (Exception exc) {
            Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void fetchData(String response,String code){

        ParseJSON pj = new ParseJSON(response);

        if (code.equals("get")){
            List<String> fetchGet = pj.detailUserParse();

            namaProfile.setText(fetchGet.get(0));
            jurusanProfile.setText(fetchGet.get(1));
            passwordProfile.setText(fetchGet.get(2));
//            angkatanProfile.setText(fetchGet.get(2));
//            profPic = fetchGet.get(3);
        }
        else{
            String status = pj.statusCodeParse();
            if(status.equals("Perubahan data berhasil!")){
                Toast.makeText(getBaseContext(),"Profil Berhasil diedit",Toast.LENGTH_LONG).show();
//                setResult(AturProfile.RESULT_OK);
                finish();
            }
            else Toast.makeText(getBaseContext(),"Gagal Edit Profil",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        filePic = data.getData();
    }

    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

//        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
//
//        }
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 123) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }
}
