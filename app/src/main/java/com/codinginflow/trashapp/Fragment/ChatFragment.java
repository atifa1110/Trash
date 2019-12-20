package com.codinginflow.trashapp.Fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.codinginflow.trashapp.Adapter.PengepulAdapter;
import com.codinginflow.trashapp.R;
import com.codinginflow.trashapp.model.Pengepul;
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
public class ChatFragment extends Fragment {

    private RecyclerView recyclerView;

    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        recyclerView = view.findViewById(R.id.rv_chat_pengepul);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        readUser();

        return view;
    }

    private void readUser(){
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Pengepul").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Pengepul> pengepuls = new ArrayList<>();
                pengepuls.clear();
                for (DataSnapshot d:dataSnapshot.getChildren()) {
                    String id = d.getKey(),
                            nama = d.child("nama").getValue().toString(),
                            namausaha = d.child("namausaha").getValue().toString(),
                            alamatusaha = d.child("alamatusaha").getValue().toString(),
                            kodepos = d.child("kodepos").getValue().toString(),
                            nomertelpon = d.child("nomertelpon").getValue().toString();
                    Pengepul peng = new Pengepul(id,nama,namausaha,alamatusaha,kodepos,nomertelpon);
                    pengepuls.add(peng);
                }
                PengepulAdapter pengepulAdapter= new PengepulAdapter(getContext(),pengepuls);
                recyclerView.setAdapter(pengepulAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
