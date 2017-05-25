package com.example.fandyaditya.silomba.Bimbingan;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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
import com.example.fandyaditya.silomba.ParseJSON;
import com.example.fandyaditya.silomba.PengaturanTim.ListTim.ListPengaturanTimObjek;
import com.example.fandyaditya.silomba.PengaturanTim.ListTim.PengaturanTimAdapter;
import com.example.fandyaditya.silomba.R;
import com.example.fandyaditya.silomba.Session;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BikinBimbingan extends AppCompatActivity {

    EditText tanggalBimbingan;
    EditText comment;
    Button upload;
    Button simpan;
    Bundle bundle;
    Spinner spinner;

    String idTim;
    String idDosbing;
    Uri filePath;
    Bitmap imgBit;
    String nrp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bikin_bimbingan);

        requestStoragePermission();

        tanggalBimbingan = (EditText)findViewById(R.id.bikin_bimbingan_tgl);
        comment = (EditText)findViewById(R.id.bikin_bimbingan_comment);
        upload = (Button)findViewById(R.id.bikin_bimbingan_uploadbtn);
        simpan = (Button)findViewById(R.id.bikin_bimbingan_simpanbtn);
        bundle = getIntent().getExtras();
        spinner = (Spinner)findViewById(R.id.bimbingan_tim_spinner);

//        idTim = bundle.getString("idTim");
//        idDosbing = bundle.getString("idDosbing");
        simpan.setOnClickListener(op);
        upload.setOnClickListener(op);
        filePath=null;
        imgBit=null;

        Session session = new Session(getBaseContext());
        nrp = session.getPreferences();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Bikin Bimbingan");

        getData();
    }
    private void getData(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konstanta.ip+"/detailtim", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                fetchData(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getBaseContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<>();
                param.put("nrp",nrp);
                return param;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getBaseContext());
        requestQueue.add(stringRequest);
    }
    private void fetchData(String response){
        ParseJSON pj = new ParseJSON(response);
        List<ListPengaturanTimObjek> dataTimku = pj.listTimParse("my_team");
        List<String> listNama = new ArrayList<>();
        for (int i=0;i<dataTimku.size();i++){
            String nameAndId = (dataTimku.get(i).getNamaTim())+"|"+dataTimku.get(i).getIdTim();
            listNama.add(nameAndId);
        }
        ArrayAdapter<String> arraySpinner = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,listNama);
        spinner.setAdapter(arraySpinner);
    }
    @Override
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
                case R.id.bikin_bimbingan_uploadbtn : showFileChooser();break;
                case R.id.bikin_bimbingan_simpanbtn : {
                    if(filePath==null)insert();
                    else uploadFile();finish();
                    break;
                }
            }
        }
    };

    private void uploadFile(){
        Toast.makeText(getBaseContext(),"Mengupload file, mohon tunggu . . .",Toast.LENGTH_LONG).show();
        String selectedItem = spinner.getSelectedItem().toString();
        String[] splitString = selectedItem.split("|");
        try {
            String uploadId = UUID.randomUUID().toString();
            String path = FilePath.getPath(getBaseContext(),filePath);
            new MultipartUploadRequest(this,uploadId,Konstanta.ip+"/uploadbimbingan")
                    .addFileToUpload(path,"file")
                    .addParameter(splitString[1],"id_tim")
                    .addParameter(tanggalBimbingan.getText().toString(),"tanggal_bimbingan")
                    .addParameter(comment.getText().toString(),"comment")
                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(2)
                    .startUpload();
        } catch (Exception exc) {
            Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private void insert(){
        String selectedItem = spinner.getSelectedItem().toString();
        final String[] splitString = selectedItem.split("|");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konstanta.ip+"/uploadbimbingan", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                validate(response);
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
                param.put(splitString[1],"id_tim");
                param.put(tanggalBimbingan.getText().toString(),"tanggal_bimbingan");
                param.put(comment.getText().toString(),"comment");
                return param;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void validate(String response){
        ParseJSON pj = new ParseJSON(response);
        String status = pj.statusCodeParse();

        if(status.equals("uploaded")){
            Toast.makeText(getBaseContext(),"Bikin Bimbingan Sukses",Toast.LENGTH_LONG).show();
            finish();
        }
        else{
            Toast.makeText(getBaseContext(),"Gagal Bikin Bimbingan",Toast.LENGTH_LONG).show();
        }
    }
    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("application/*");
//        String[] mimetypes={"application/msword,application/msexcel,application/pdf,application/mspowerpoint"};
//        intent.putExtra(Intent.EXTRA_MIME_TYPES,mimetypes);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Pdf"), 0);
    }
    public void take_gallery(int choice){
        Intent ambil_foto = new Intent (Intent.ACTION_GET_CONTENT,null);
        ambil_foto.setType("image/*");
        startActivityForResult(ambil_foto,choice);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            filePath = data.getData();
    }
    public String getStringImage(Bitmap bmp){
        int size=20;
        if (bmp!=null){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, size, baos);
            byte[] imageBytes = baos.toByteArray();
            return Base64.encodeToString(imageBytes, Base64.DEFAULT);
        }
        else return "";
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
