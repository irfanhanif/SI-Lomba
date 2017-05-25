package com.example.fandyaditya.silomba.Bimbingan;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fandyaditya.silomba.Konstanta;
import com.example.fandyaditya.silomba.ParseJSON;
import com.example.fandyaditya.silomba.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailBimbingan extends AppCompatActivity {
    Button downloadBtn;
    TextView tanggal;
    TextView comment;
    String idTim;
    String tanggalBimbingan;
    String commentBimbingan;
    String fileBimbingan;
    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_bimbingan);
        downloadBtn = (Button)findViewById(R.id.detail_bimbingan_downloadBtn);
        tanggal = (TextView)findViewById(R.id.detail_bimbingan_tgl);
        comment = (TextView)findViewById(R.id.detail_bimbingan_comment);
        bundle = getIntent().getExtras();
        String idBimbingan = bundle.getString("idBimbingan");
        idTim = bundle.getString("idTim");
        tanggalBimbingan = bundle.getString("tanggalBimbingan");
        commentBimbingan = bundle.getString("comment");
        fileBimbingan = bundle.getString("file");
        getSupportActionBar().setTitle("Detail Bimbingan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        Toast.makeText(getBaseContext(),fileBimbingan,Toast.LENGTH_LONG).show();
        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadMethod();
            }
        });
        setView();

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:finish();break;
        }
        return true;
    }

    private void setView(){
        tanggal.setText(tanggalBimbingan);
        comment.setText(commentBimbingan);
    }
    private void downloadMethod() {
        Log.d("file",fileBimbingan);
        String fileUri = Konstanta.ip+"/file/"+fileBimbingan;
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(fileUri));
        request.setTitle("Download File");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "file1");
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }
//    private void getData(final String idBimbingan){
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, "someurl.com", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                fetchData(response);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getBaseContext(),error.toString(),Toast.LENGTH_LONG).show();
//            }
//        })
//        {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> param = new HashMap<>();
//                param.put("idBimbingan",idBimbingan);
//                return param;
//            }
//        };
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
//    }
    private void fetchData(String response){
        ParseJSON pj = new ParseJSON(response);
        List<String> data = pj.detailBimbinganParse();
        tanggal.setText(data.get(0));
        comment.setText(data.get(1));
        fileBimbingan = data.get(2);
    }
}
