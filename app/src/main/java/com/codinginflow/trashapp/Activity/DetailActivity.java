package com.codinginflow.trashapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.codinginflow.trashapp.R;
import com.codinginflow.trashapp.model.Pengepul;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.SliderLayout;

public class DetailActivity extends AppCompatActivity {

    private Pengepul pengepul;
    private TextView namapengepul, namaUsh, alamat, harga;
    private EditText total;
    private Button mau,hitung;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private String id, nama, namausaha, alamatusaha, kodepos, nomertelp,hrg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        id = getIntent().getExtras().getString("pengepulid");
        nama = getIntent().getExtras().getString("pengepulnama");
        namausaha = getIntent().getExtras().getString("pengepulnamausaha");
        alamatusaha = getIntent().getExtras().getString("pengepulalamatusaha");
        kodepos = getIntent().getExtras().getString("pengepulkodepos");
        nomertelp = getIntent().getExtras().getString("pengepulnomertelp");
        hrg = getIntent().getExtras().getString("pengepulharga");

        namaUsh = findViewById(R.id.namausaha);
        namapengepul = findViewById(R.id.namapengepul);
        alamat = findViewById(R.id.alamat);
        harga = findViewById(R.id.harga);
        mau = findViewById(R.id.btn_detailproduk_mau);
        hitung = findViewById(R.id.hitung);
        total = findViewById(R.id.et_detail_angka);

        namaUsh.setText(namausaha);
        alamat.setText(alamatusaha);
        namapengepul.setText(nama);
        harga.setText(hrg);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        //progressBar = findViewById(R.id.p_detailproduk_progres);

        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double angka = Double.parseDouble(harga.getText().toString());
                Double kg = Double.parseDouble(total.getText().toString());
                Double hargaTotal = angka*kg;
                harga.setText(hargaTotal.toString());
            }
        });
    }

}