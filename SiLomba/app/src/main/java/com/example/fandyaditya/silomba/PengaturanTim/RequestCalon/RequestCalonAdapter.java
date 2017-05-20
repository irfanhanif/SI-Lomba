package com.example.fandyaditya.silomba.PengaturanTim.RequestCalon;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

/**
 * Created by Fandy Aditya on 4/24/2017.
 */

public class RequestCalonAdapter extends RecyclerView.Adapter<RequestCalonAdapter.ViewHolder> {

    private List<RequestCalonObjek> listItem;
    private Context context;

    public RequestCalonAdapter(List<RequestCalonObjek> listItem, Context context) {
        this.listItem = listItem;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_calon_objek,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RequestCalonObjek requestCalonObjek = listItem.get(position);

        final String idUser = requestCalonObjek.getIdUser();

        holder.nama.setText(requestCalonObjek.getNama());
        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionRequest(idUser,"accept");
            }
        });
        holder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionRequest(idUser,"reject");
            }
        });

    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nama;
        private ImageView accept;
        private ImageView reject;
        public ViewHolder(View itemView) {
            super(itemView);
            nama = (TextView)itemView.findViewById(R.id.request_calon_objek_nama);
            accept = (ImageView)itemView.findViewById(R.id.request_calon_objek_acc);
            reject = (ImageView)itemView.findViewById(R.id.request_calon_objek_rjt);
        }
    }
    private void actionRequest(final String idUser,final String code){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konstanta.ip+"/acceptrequest", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                validate(response,code);
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
                param.put("code",code);
                return param;
            }
        };
        RequestQueue newReq = Volley.newRequestQueue(context);
        newReq.add(stringRequest);
    }

    private void validate(String response,String code){
        ParseJSON pj = new ParseJSON(response);
        String status = pj.statusCodeParse();
        if(code.equals("acc")){
            if(status.equals("success")){
                Toast.makeText(context,"Anggota berhasil diterima",Toast.LENGTH_LONG).show();
            }
            else Toast.makeText(context,"Gagal Menerima Anggota",Toast.LENGTH_LONG).show();
        }
        else{
            if(status.equals("success")){
                Toast.makeText(context,"Anggota Ditolak",Toast.LENGTH_LONG).show();
            }
            else Toast.makeText(context,"Gagal Menolak Anggota",Toast.LENGTH_LONG).show();
        }

    }
}
