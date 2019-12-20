package com.codinginflow.trashapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.codinginflow.trashapp.Adapter.PengepulAdapter;
import com.codinginflow.trashapp.R;
import com.codinginflow.trashapp.model.Pengepul;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class KategoriActivity extends AppCompatActivity {

    private TextView kategori;
    private RecyclerView recyclerView;
    private PengepulAdapter pengepulAdapter;
    private String sKategori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);

        kategori = findViewById(R.id.tv_kategori);
        recyclerView = findViewById(R.id.rv_kategori);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sKategori = getIntent().getExtras().getString("Kategori");
        kategori.setText(sKategori);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Pengepul").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Pengepul> item = new ArrayList<>();
                for (DataSnapshot d:dataSnapshot.getChildren()) {
                    String kategori = d.child("kategori").getValue().toString();
                    if (sKategori.equals(kategori)){
                        getSupportActionBar().setTitle(kategori);
                        String id = d.getKey(),
                                nama = d.child("nama").getValue().toString();
                        Pengepul pengepul = new Pengepul();
                        pengepul.getNama();
                        item.add(pengepul);
                    }
                }
                PengepulAdapter pen = new PengepulAdapter(KategoriActivity.this,item);
                recyclerView.setAdapter(pen);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}