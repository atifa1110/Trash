package com.codinginflow.trashapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.codinginflow.trashapp.R;
import com.codinginflow.trashapp.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import de.hdodenhof.circleimageview.CircleImageView;


public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private CircleImageView ivFoto;
    private EditText etNama, etAlamat, etNoTel,etKodePos;
    private Button btnSimpan;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private ProgressDialog progress, progress2;
    private String uid ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        //ivFoto = findViewById(R.id.iv_editprofile_foto);

        etNama = (EditText) findViewById(R.id.et_editprofile_nama);
        //etEmail = findViewById(R.id.et_editprofile_email);
        etAlamat = (EditText) findViewById(R.id.et_editprofile_alamat);
        etNoTel = (EditText) findViewById(R.id.et_editprofile_no_telp);
        etKodePos = (EditText) findViewById(R.id.et_editprofile_kodepos);


        btnSimpan = findViewById(R.id.btn_editprofile_simpan);
        progress = new ProgressDialog(this);
       progress2 = new ProgressDialog(this);
        progress.setMessage("saving changes, please wait");
       progress2.setMessage("fetching data");

        btnSimpan.setOnClickListener(this);
        //ivFoto.setOnClickListener(this);
        etKodePos.setOnClickListener(this);
        FirebaseApp.initializeApp(this);
        //mStorage = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        uid = mAuth.getCurrentUser().getUid();
        Log.d("uid",uid);
        fetchData();
        inputValidated();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_editprofile_simpan :
                progress.show();
                update();
                break;
            case R.id.iv_editprofile_foto :
                //pickFoto();
                break;
        }
    }
    private void fetchData(){
        progress2.show();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("User").child(mAuth.getCurrentUser().getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String alamat = dataSnapshot.child("alamat").getValue().toString();
                String kodepos = dataSnapshot.child("kodepos").getValue().toString();
                String nama = dataSnapshot.child("nama").getValue().toString();
                String notelp = dataSnapshot.child("nomertelpon").getValue().toString();

                etAlamat.setText(alamat);
                etKodePos.setText(kodepos);
                etNama.setText(nama);
                etNoTel.setText(notelp);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void update(){
        String uid = mAuth.getCurrentUser().getUid();
        String nama = etNama.getText().toString();
        String alamat = etAlamat.getText().toString();
        String no_telp = etNoTel.getText().toString();
        String kodepos = etKodePos.getText().toString();

        User user = new User(uid, nama,alamat,kodepos,no_telp);
        Log.d("uid",uid);
        mDatabase.child("User").child(uid).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progress.dismiss();
                finish();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        //uploadFoto(uid);
    }
//    private void uploadFoto(String uid) {
//        StorageReference ref = mStorage.child("User/" + uid + ".jpg");
//        if(uri!=null) ref.putFile(uri);
//    }
//    private void pickFoto(){
//        //PickImageDialog.build(new PickSetup()).show(this);
//    }
//    public void onPickResult(PickResult r) {
//        if (r.getError() == null) {
//            //If you want the Uri.
//            //Mandatory to refresh image from Uri.
//            //getImageView().setImageURI(null);
//
//            //Setting the real returned image.
//            //getImageView().setImageURI(r.getUri());
//
//            //If you want the Bitmap.
//            uri = r.getUri();
//            ivFoto.setImageBitmap(r.getBitmap());
//
//            //Image path
//            //r.getPath();
//        } else {
//            //Handle possible errors
//            //TODO: do what you have to do with r.getError();
//            Toast.makeText(this, r.getError().getMessage(), Toast.LENGTH_LONG).show();
//        }
//    }
    private boolean inputValidated() {
        boolean res = true;
        if (etNama.getText().toString().isEmpty()) {
            res = false;
            etNama.setError("This is required");
        }if (etAlamat.getText().toString().isEmpty()){
            res = false;
            etAlamat.setError("This is required");
        } if (etKodePos.getText().toString().isEmpty()){
            res = false;
            etKodePos.setError("This is required");
        } if(etNoTel.getText().toString().isEmpty()){
            res = false;
            etNoTel.setError("This is required");
        }
        return res;
    }
}

