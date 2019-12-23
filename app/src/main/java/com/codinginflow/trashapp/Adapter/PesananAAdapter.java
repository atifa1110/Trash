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
import com.codinginflow.trashapp.model.pesananA;

import java.util.ArrayList;

public class PesananAAdapter extends RecyclerView.Adapter<PesananAAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<pesananA> pesananAs;

    public PesananAAdapter(Context context , ArrayList<pesananA> pesananAs){
        this.context = context;
        this.pesananAs = pesananAs;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pesananitem,parent,false);
        return new PesananAAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final pesananA PesananA = pesananAs.get(position);
        holder.namaToko.setText(PesananA.getNamaPengepul());
        holder.status.setText("Trip Complete");
        holder.tanggal.setText(PesananA.getTanggal());
    }

    @Override
    public int getItemCount() {
        return pesananAs.size();
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
