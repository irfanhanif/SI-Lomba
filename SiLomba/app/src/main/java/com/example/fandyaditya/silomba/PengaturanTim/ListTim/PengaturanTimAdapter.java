package com.example.fandyaditya.silomba.PengaturanTim.ListTim;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
import com.example.fandyaditya.silomba.ListLomba.DetailLomba;
import com.example.fandyaditya.silomba.ParseJSON;
import com.example.fandyaditya.silomba.PengaturanTim.DetailTim;
import com.example.fandyaditya.silomba.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Fandy Aditya on 5/11/2017.
 */

public class PengaturanTimAdapter extends RecyclerView.Adapter<PengaturanTimAdapter.ViewHolder> {

    private List<ListPengaturanTimObjek> listItem;
    private Context context;
    private String activity;

    public PengaturanTimAdapter(List<ListPengaturanTimObjek> listItem, Context context,String activity) {
        this.listItem = listItem;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public PengaturanTimAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pengaturan_tim_objek,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PengaturanTimAdapter.ViewHolder holder, int position) {
        ListPengaturanTimObjek listPengaturanTimObjek = listItem.get(position);

        final String idTim = listPengaturanTimObjek.getIdTim();
        final String namaTim = listPengaturanTimObjek.getNamaTim();
        final String deskripsiTim = listPengaturanTimObjek.getDeskripsiTim();
        final String maxAnggota = listPengaturanTimObjek.getMaxAnggota();
        final String profPic = listPengaturanTimObjek.getFileFotoprofilTim();
        final String tipeTim = listPengaturanTimObjek.getTipeTim();
        final String idLomba = DetailLomba.idLomba;
        holder.namaTim.setText(listPengaturanTimObjek.getNamaTim());
        holder.selectTim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(activity.equals("pengaturantim"))
                {
                    openPage(idTim,namaTim,maxAnggota,deskripsiTim,profPic,tipeTim);
                }
                else {
                    ikutSerta(idTim,idLomba);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView namaTim;
        ImageView selectTim;

        public ViewHolder(View itemView) {
            super(itemView);

            namaTim = (TextView)itemView.findViewById(R.id.pengaturan_tim_objeknama);
            selectTim = (ImageView) itemView.findViewById(R.id.pengaturan_tim_objekselect);
        }
    }

    private void openPage(String idTim,String namaTim, String maxAnggota, String deskripsiTim, String profPic,String tipeTim){
        Intent myIntent = new Intent(context, DetailTim.class);
        Bundle bundle = new Bundle();
        bundle.putString("idTim",idTim);
        bundle.putString("namaTim",namaTim);
        bundle.putString("maxAnggota",maxAnggota);
        bundle.putString("deskripsiTim",deskripsiTim);
        bundle.putString("profPic",profPic);
        bundle.putString("tipeTim",tipeTim);
        myIntent.putExtras(bundle);
        context.startActivity(myIntent);
    }
    private void ikutSerta(final String idTim,final String idLomba){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konstanta.ip+"/ikutlomba", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                fetchData(response);
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
                param.put("id_tim",idTim);
                param.put("id_lomba",idLomba);
                return param;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
    private void fetchData(String response){
        ParseJSON parseJSON = new ParseJSON(response);
        String status = parseJSON.statusCodeParse();
        if(status.equals("Input berhasil!")){
            Toast.makeText(context,"Tim berhasil terdaftar di list pencarian tim",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(context,"Gagal terdaftar sebagai ikut serta tim",Toast.LENGTH_LONG).show();
        }
    }
}
