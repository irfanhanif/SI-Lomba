package com.example.fandyaditya.silomba.Bimbingan.Dosen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fandyaditya.silomba.R;

import java.util.List;

/**
 * Created by Fandy Aditya on 4/24/2017.
 */

public class DosenAdapter extends RecyclerView.Adapter<DosenAdapter.ViewHolder> {
    private List<DosenObjek> listItem;
    Context context;

    public DosenAdapter(List<DosenObjek> listItem, Context context) {
        this.listItem = listItem;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_dosen_objek,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DosenObjek dosenObjek = listItem.get(position);

        holder.namaDosen.setText(dosenObjek.getNama());
        holder.noHpDosen.setText(dosenObjek.getNoHp());
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView namaDosen;
        public TextView noHpDosen;

        public ViewHolder(View itemView) {
            super(itemView);
            namaDosen = (TextView)itemView.findViewById(R.id.list_dosen_nama);
            noHpDosen = (TextView)itemView.findViewById(R.id.list_dosen_nohp);
        }
    }
}
