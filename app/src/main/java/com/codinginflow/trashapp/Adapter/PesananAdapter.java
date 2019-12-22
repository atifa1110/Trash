package com.codinginflow.trashapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codinginflow.trashapp.R;
import com.codinginflow.trashapp.model.pesanan;

import java.util.ArrayList;

public class PesananAdapter extends RecyclerView.Adapter<PesananAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<pesanan> pesanans;

    public PesananAdapter(Context context , ArrayList<pesanan> pengepuls){
        this.context = context;
        this.pesanans = pengepuls;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pesananitem,parent,false);
        return new PesananAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final pesanan Pesanan = pesanans.get(position);
        holder.namaToko.setText(Pesanan.getNamaPengepul());
        holder.status.setText("On Progress");
        holder.tanggal.setText(Pesanan.getTanggal());
    }

    @Override
    public int getItemCount() {
        return pesanans.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView namaToko, status, tanggal;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            namaToko = itemView.findViewById(R.id.lineCard1);
            status = itemView.findViewById(R.id.lineCard2);
            tanggal = itemView.findViewById(R.id.lineCard3);
        }
    }
}
