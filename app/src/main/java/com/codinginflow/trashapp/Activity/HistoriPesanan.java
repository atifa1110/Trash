package com.codinginflow.trashapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.codinginflow.trashapp.Adapter.HistoryAdapter;
import com.codinginflow.trashapp.Adapter.PesananAAdapter;
import com.codinginflow.trashapp.Adapter.PesananAdapter;
import com.codinginflow.trashapp.R;
import com.codinginflow.trashapp.model.pesanan;
import com.codinginflow.trashapp.model.pesananA;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HistoriPesanan extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private PesananAdapter pesananAdapter;
    private PesananAAdapter pesananAAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histori_pesanan);
        firebaseAuth = FirebaseAuth.getInstance();
        recyclerView = findViewById(R.id.recyclerViewHistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Pesanan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<pesanan> itemPesanan = new ArrayList<>();
                for (DataSnapshot d:dataSnapshot.getChildren()){
                    String uidUser = d.child("uidUser").getValue().toString();
                    if (firebaseAuth.getCurrentUser().getUid().equals(uidUser)){
                        if (d.child("status").getValue().toString().equals("true")){
                            String id = d.getKey(),
                                    uid = d.child("uidUser").getValue().toString(),
                                    namaPengepul = d.child("namaPengepul").getValue().toString(),
                                    hargaTotal = d.child("hargaTotal").getValue().toString(),
                                    tanggal = d.child("tanggal").getValue().toString(),
                                    status = d.child("status").getValue().toString();
                            pesanan pesanan = new pesanan(uid, namaPengepul, Double.parseDouble(hargaTotal),tanggal,true);
                            Log.d("item",pesanan.getNamaPengepul());
                            itemPesanan.add(pesanan);
                        }
                    }
                    pesananAdapter = new PesananAdapter(HistoriPesanan.this,itemPesanan);
                    recyclerView.setAdapter(pesananAdapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        /*
        databaseReference.child("pesanan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<pesananA> itemPesananA = new ArrayList<>();
                for (DataSnapshot d:dataSnapshot.getChildren()){
                    String uidUser = d.child("uidUser").getValue().toString();
                    if (firebaseAuth.getCurrentUser().getUid().equals(uidUser)){
                        if (d.child("status").getValue().toString().equals("false")){
                            String id = d.getKey(),
                                    uid = d.child("uidUser").getValue().toString(),
                                    namaPengepul = d.child("namaPengepul").getValue().toString(),
                                    hargaTotal = d.child("hargaTotal").getValue().toString(),
                                    tanggal = d.child("tanggal").getValue().toString(),
                                    status = d.child("status").getValue().toString();
                            pesananA PesananA = new pesananA(uid, namaPengepul, Double.parseDouble(hargaTotal),tanggal,false);
                            Log.d("item",PesananA.getNamaPengepul());
                            itemPesananA.add(PesananA);
                        }
                    }
                    pesananAAdapter = new PesananAAdapter(HistoriPesanan.this,itemPesananA);
                    recyclerView.setAdapter(pesananAAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
    }
}
