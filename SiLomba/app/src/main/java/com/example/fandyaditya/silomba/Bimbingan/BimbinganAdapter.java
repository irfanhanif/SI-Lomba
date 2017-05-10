package com.example.fandyaditya.silomba.Bimbingan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fandyaditya.silomba.R;

import java.util.List;

/**
 * Created by Fandy Aditya on 4/24/2017.
 */

public class BimbinganAdapter extends RecyclerView.Adapter<BimbinganAdapter.ViewHolder> {

    private List<BimbinganObjek> listItem;
    private Context context;

    public BimbinganAdapter(Context context, List<BimbinganObjek> listItem) {
        this.listItem = listItem;
    }

    @Override
    public BimbinganAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.bimbingan_objek,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BimbinganAdapter.ViewHolder holder, int position) {
        BimbinganObjek bimbinganObjek = listItem.get(position);
        final String idBimbingan = bimbinganObjek.getId();
        holder.tanggalBimbingan.setText(bimbinganObjek.getTanggal());
        holder.deskripsiBimbingan.setText(bimbinganObjek.getComment());
        holder.listLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPage(idBimbingan);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout listLayout;
        TextView tanggalBimbingan;
        TextView deskripsiBimbingan;

        public ViewHolder(View itemView) {
            super(itemView);
            tanggalBimbingan = (TextView)itemView.findViewById(R.id.bimbingan_objek_tanggal);
            deskripsiBimbingan = (TextView)itemView.findViewById(R.id.bimbingan_objek_comment);
            listLayout = (LinearLayout)itemView.findViewById(R.id.bimbingna_objek_lay);
        }
    }
    private void openPage(String id){
        Intent myIntent = new Intent(context, DetailBimbingan.class);
        Bundle bundle = new Bundle();
        bundle.putString("id",id);
        myIntent.putExtras(bundle);
        context.startActivity(myIntent);
    }
}
