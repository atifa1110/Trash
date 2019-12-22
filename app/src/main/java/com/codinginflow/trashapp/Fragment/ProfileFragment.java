package com.codinginflow.trashapp.Fragment;


import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.codinginflow.trashapp.Activity.DaftarPengepulActivity;
import com.codinginflow.trashapp.Activity.LoginActivity;
import com.codinginflow.trashapp.Activity.EditProfileActivity;
import com.codinginflow.trashapp.Activity.GantiPasswordActivity;
import com.codinginflow.trashapp.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener{

    private CircleImageView iv_foto;
    private TextView nama, editProfil, editPassword, syaratKetentuan, keluar ,daftarpengepul;
    private FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;
    private View mainView;

    private StorageReference storageReference;
    private static final int IMAGE_REQUEST = 1;
    private Uri imageUri;
    private StorageTask uploadTask;
    private DatabaseReference databaseReference;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        iv_foto = view.findViewById(R.id.iv_profile_foto);
        nama = view.findViewById(R.id.tv_profile_nama);
        daftarpengepul = view.findViewById(R.id.tv_profile_daftarpengepul);
        editProfil = view.findViewById(R.id.tv_profile_edit);
        editPassword = view.findViewById(R.id.tv_profile_ganti_pass);
        syaratKetentuan = view.findViewById(R.id.tv_profile_syarat_dan_ketentuan);
        keluar = view.findViewById(R.id.tv_profile_keluar);
        //progressBar = rootView.findViewById(R.id.pb_profile);
        mainView = view.findViewById(R.id.main_view_profile);

        storageReference = FirebaseStorage.getInstance().getReference("uploads");
        firebaseAuth = FirebaseAuth.getInstance();

        Profile();
        Picture();

        editProfil.setOnClickListener(this);
        editPassword.setOnClickListener(this);
        daftarpengepul.setOnClickListener(this);
        keluar.setOnClickListener(this);
        syaratKetentuan.setOnClickListener(this);

        return view;
    }

    public void Profile(){
        databaseReference = FirebaseDatabase.getInstance().getReference().child("User").child(firebaseAuth.getCurrentUser().getUid());
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String sNama = dataSnapshot.child("nama").getValue().toString();
                Log.d("nama",sNama);
                nama.setText(sNama);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void Picture(){
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("User").child(firebaseAuth.getCurrentUser().getUid()+".jpg");
        try {
            final File localFile = File.createTempFile("Images", "jpg");
            storageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    if(localFile!=null)iv_foto.setImageBitmap(BitmapFactory.decodeFile(localFile.getAbsolutePath()));
                    progressBar.setVisibility(View.GONE);
                    mainView.setVisibility(View.VISIBLE);
                }
            });
        } catch (
                IOException e) {
            e.printStackTrace();
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_profile_daftarpengepul :
                startActivity(new Intent(getContext(), DaftarPengepulActivity.class));
                break;
            case R.id.tv_profile_edit :
                startActivity(new Intent(getContext(), EditProfileActivity.class));
                break;
            case R.id.tv_profile_ganti_pass :
                startActivity(new Intent(getActivity().getApplication(), GantiPasswordActivity.class));
                break;
            case R.id.tv_profile_syarat_dan_ketentuan :
                break;
            case R.id.tv_profile_keluar :
                firebaseAuth.signOut();
                startActivity(new Intent(getContext(), LoginActivity.class));
                break;
        }
    }
}
