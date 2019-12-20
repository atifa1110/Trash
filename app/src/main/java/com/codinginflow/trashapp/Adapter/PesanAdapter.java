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
import com.codinginflow.trashapp.Activity.DetailActivity;
import com.codinginflow.trashapp.R;
import com.codinginflow.trashapp.model.Pengepul;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PesanAdapter extends RecyclerView.Adapter<PesanAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Pengepul> pengepuls;

    public PesanAdapter(Context context , ArrayList<Pengepul> pengepuls){
        this.context = context;
        this.pengepuls = pengepuls;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pengepul,parent,false);
        return new PesanAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Pengepul pengepul = pengepuls.get(position);
        holder.nama.setText(pengepul.getNama());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("pengepulid",pengepul.getUid());
                intent.putExtra("pengepulnama",pengepul.getNama());
                intent.putExtra("pengepulnamausaha",pengepul.getNamaUsaha());
                intent.putExtra("pengepulalamatusaha",pengepul.getAlamatUsaha());
                intent.putExtra("pengepulkodepos",pengepul.getKodepos());
                intent.putExtra("pengepulnomertelp",pengepul.getNomertelpon());
                intent.putExtra("pengepulharga",pengepul.getHarga());
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
