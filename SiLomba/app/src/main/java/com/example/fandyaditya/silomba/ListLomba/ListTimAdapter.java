package com.example.fandyaditya.silomba.ListLomba;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.example.fandyaditya.silomba.Session;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Fandy Aditya on 5/3/2017.
 */

public class ListTimAdapter extends RecyclerView.Adapter<ListTimAdapter.ViewHolder> {

    List<ListTimObjek> itemList;
    Context context;

    public ListTimAdapter(List<ListTimObjek> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_tim_objek,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListTimObjek listTimObjek = itemList.get(position);
        Session session = new Session(context);
        final String nrp = session.getPreferences();
        final String idTim = listTimObjek.getId();

        holder.namaTim.setText(listTimObjek.getNama());
        holder.sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest(idTim,nrp);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namaTim;
        ImageView sendBtn;
        public ViewHolder(View itemView) {
            super(itemView);
            namaTim = (TextView)itemView.findViewById(R.id.list_tim_objeknama);
            sendBtn = (ImageView) itemView.findViewById(R.id.list_tim_objeksend);
        }
    }

    private void sendRequest(final String idTim, final String nrp){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konstanta.ip+"/requesttim", new Response.Listener<String>() {
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
                param.put("idTim",idTim);
                param.put("nrp",nrp);
                return param;
            }
        };
        RequestQueue newReq = Volley.newRequestQueue(context);
        newReq.add(stringRequest);
    }

    private void validate(String response){
        ParseJSON pj = new ParseJSON(response);
        String status = pj.statusCodeParse();
        if(status.equals("Request berhasil!")){
            Toast.makeText(context,"Request anggota berhasil dikirim",Toast.LENGTH_LONG).show();
        }
        else Toast.makeText(context,"Gagal Mengirim request anggota",Toast.LENGTH_LONG).show();
    }
}
