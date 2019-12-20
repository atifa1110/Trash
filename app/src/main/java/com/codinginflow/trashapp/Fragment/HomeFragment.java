package com.codinginflow.trashapp.Fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.codinginflow.trashapp.Adapter.KategoriAdapter;
import com.codinginflow.trashapp.Activity.MapsActivity;
import com.codinginflow.trashapp.R;
import com.codinginflow.trashapp.model.Kategori;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.SliderLayout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{

    private RecyclerView recyclerView;
    private Button btnLihat;
    private SliderLayout sliderLayout;
    private TextView saldo;
    private FirebaseAuth firebaseAuth;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        sliderLayout = view.findViewById(R.id.imageSlider);
        //setSliderLayout();
        recyclerView = view.findViewById(R.id.rv_home_kategori);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        saldo = view.findViewById(R.id.tv_home_textsaldo);

        ArrayList<Kategori> kategoris = new ArrayList<>();
        kategoris.add(new Kategori("Plastik",R.drawable.plastic));
        kategoris.add(new Kategori("Botol",R.drawable.botol));
        kategoris.add(new Kategori("Kertas",R.drawable.papers));
        kategoris.add(new Kategori("Kardus",R.drawable.kardus));
        KategoriAdapter kategoriAdapter = new KategoriAdapter(getActivity(),kategoris);
        recyclerView.setAdapter(kategoriAdapter);

        //getSaldo();

        btnLihat = view.findViewById(R.id.btn_home_lihat);
        btnLihat.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_home_lihat :
                Intent intent = new Intent(getContext(), MapsActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    public void getSaldo(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("User").child(firebaseAuth.getCurrentUser().getUid());
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String sld = dataSnapshot.child("saldo").getValue().toString();
                Log.d("saldo",sld);
                saldo.setText(sld);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void setSliderLayout (){

        ArrayList<Integer> slide = new ArrayList<>();
        slide.add(R.drawable.home);

        DefaultSliderView sliderView = new DefaultSliderView(getContext());
        //int imageRes = sliderView.getImageRes(slide);
        sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);

        //at last add this view in your layout :
        sliderLayout.addSliderView(sliderView);
    }
}
