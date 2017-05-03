package com.example.fandyaditya.silomba.ListLomba;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fandyaditya.silomba.R;

import java.util.List;

/**
 * Created by Fandy Aditya on 4/24/2017.
 */

public class ListLombaAdapter extends RecyclerView.Adapter<ListLombaAdapter.ViewHolder> {

    private List<ListLombaObjek> listItem;
    private Context context;

    public ListLombaAdapter(Context listItem, List<ListLombaObjek> context) {
        this.listItem = listItem;
        this.context = context;
    }

    @Override
    public ListLombaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_lomba_objek,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ListLombaAdapter.ViewHolder holder, int position) {
        ListLombaObjek listLombaObjek = listItem.get(position);

        final String idLomba = listLombaObjek.getId();

        holder.namaLomba.setText(listLombaObjek.getNama());
        holder.penyelenggaraLomba.setText(listLombaObjek.getPenyelenggara());
        holder.hadiahLomba.setText(listLombaObjek.getHadiah());
        Glide.with(context).load(listLombaObjek.getImg()).into(holder.imgLomba);

        holder.viewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIntent(idLomba);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView namaLomba;
        public TextView penyelenggaraLomba;
        public TextView hadiahLomba;
        public TextView viewMore;
        public ImageView imgLomba;

        public ViewHolder(View itemView) {
            super(itemView);
            namaLomba = (TextView)itemView.findViewById(R.id.lomba_objek_nama);
            penyelenggaraLomba = (TextView)itemView.findViewById(R.id.lomba_objek_penyelenggara);
            hadiahLomba = (TextView)itemView.findViewById(R.id.lomba_objek_hadiah);
            viewMore = (TextView)itemView.findViewById(R.id.lomba_objek_viewmore);
            imgLomba = (ImageView)itemView.findViewById(R.id.lomba_objek_img);
        }
    }

    private void openIntent(String idLomba){
        Bundle bundle = new Bundle();
        Intent openPage = new Intent(context, DetailLomba.class);
        bundle.putString("idLomba",idLomba);
        openPage.putExtras(bundle);
        context.startActivity(openPage);
    }
}
