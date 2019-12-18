package com.codinginflow.trashapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codinginflow.trashapp.Activity.ChatActivity;
import com.codinginflow.trashapp.R;
import com.codinginflow.trashapp.model.Pengepul;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PengepulAdapter extends RecyclerView.Adapter<PengepulAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Pengepul> pengepuls;

    public PengepulAdapter(Context context , ArrayList<Pengepul> pengepuls){
        this.context = context;
        this.pengepuls = pengepuls;
    }

    @NonNull
    @Override
    public PengepulAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pengepul,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PengepulAdapter.MyViewHolder holder, int position) {
        holder.nama.setText(pengepuls.get(position).getNama());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("Pengepul",pengepuls);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pengepuls.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView foto;
        private TextView nama;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            foto = itemView.findViewById(R.id.iv_pengepul_gambar);
            nama = itemView.findViewById(R.id.tv_pengepul_nama);
        }
    }
}
