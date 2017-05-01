package com.example.fandyaditya.silomba.PengaturanTim.RequestCalon;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fandyaditya.silomba.R;

import java.util.List;

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

        String id = requestCalonObjek.getId();
        String strJob = "("+requestCalonObjek.getJob()+")";

        holder.nama.setText(requestCalonObjek.getNama());
        holder.job.setText(strJob);

        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nama;
        private TextView job;
        private ImageView accept;
        private ImageView reject;
        public ViewHolder(View itemView) {
            super(itemView);
            nama = (TextView)itemView.findViewById(R.id.request_calon_objek_nama);
            job = (TextView)itemView.findViewById(R.id.request_calon_objek_job);
            accept = (ImageView)itemView.findViewById(R.id.request_calon_objek_acc);
            reject = (ImageView)itemView.findViewById(R.id.request_calon_objek_rjt);
        }
    }
}
