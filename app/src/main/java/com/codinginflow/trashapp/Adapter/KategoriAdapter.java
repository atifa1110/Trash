package com.codinginflow.trashapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codinginflow.trashapp.R;
import com.codinginflow.trashapp.model.Kategori;

import java.util.ArrayList;

public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Kategori> kategoris;

    public KategoriAdapter(Context context, ArrayList<Kategori> kategoris) {
        this.context = context;
        this.kategoris = kategoris;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_kategori,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(kategoris.get(position).getNama());
        holder.imageView.setImageDrawable(context.getResources().getDrawable(kategoris.get(position).getIcon()));
    }

    @Override
    public int getItemCount() {
        return kategoris.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_kategori_judul);
            imageView = itemView.findViewById(R.id.iv_kategori_gambar);
        }
    }
}
