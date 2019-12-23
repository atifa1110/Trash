package com.codinginflow.trashapp.Fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.codinginflow.trashapp.Activity.KategoriActivity;
import com.codinginflow.trashapp.Adapter.PesanAdapter;
import com.codinginflow.trashapp.Adapter.PesananAdapter;
import com.codinginflow.trashapp.R;
import com.codinginflow.trashapp.model.Pengepul;
import com.codinginflow.trashapp.model.PesananItem;
import com.codinginflow.trashapp.model.pesanan;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PesananFragment extends Fragment {

    private Toolbar toolbar;
    private FirebaseAuth firebaseAuth;
    private PesananAdapter pesananAdapter;
    private RecyclerView recyclerView;

    public PesananFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pesanan, container, false);
        firebaseAuth = FirebaseAuth.getInstance();

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_pesanan);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Pesanan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<pesanan> itemPesanan = new ArrayList<>();
                for (DataSnapshot d:dataSnapshot.getChildren()){
                    String uidUser = d.child("uidUser").getValue().toString();
                    if (firebaseAuth.getCurrentUser().getUid().equals(uidUser)){
                            String id = d.getKey(),
                                    uid = d.child("uidUser").getValue().toString(),
                                    namaPengepul = d.child("namaPengepul").getValue().toString(),
                                    hargaTotal = d.child("hargaTotal").getValue().toString(),
                                    tanggal = d.child("tanggal").getValue().toString(),
                                    status = d.child("status").getValue().toString();
                            pesanan pesanan = new pesanan(uid, namaPengepul, Double.parseDouble(hargaTotal),tanggal,false);
                            Log.d("item",pesanan.getNamaPengepul());
                            itemPesanan.add(pesanan);
                    }
                    pesananAdapter = new PesananAdapter(getActivity(),itemPesanan);
                    recyclerView.setAdapter(pesananAdapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }

}
