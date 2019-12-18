package com.codinginflow.trashapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.codinginflow.trashapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class GantiPasswordActivity extends AppCompatActivity implements View.OnClickListener{

    private TextInputEditText passBaru, konfBaru ,passLama;
    private Button ChangeButtton;
    private ProgressDialog progressDialog;
    private FirebaseUser user;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganti_password);

        //Inisialisasi Widget dan Membuat Objek dari FirebaeUser
        passLama = findViewById(R.id.ti_ganti_password_passlama);
        passBaru = findViewById(R.id.ti_ganti_password_passbaru);
        konfBaru = findViewById(R.id.ti_ganti_password_konfirmpassbaru);
        ChangeButtton = findViewById(R.id.btn_ganti_password_simpan);
        user = FirebaseAuth.getInstance().getCurrentUser();
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating Password...");

        ChangeButtton.setOnClickListener(this);

        passLama.addTextChangedListener(textWatcher);
        passBaru.addTextChangedListener(textWatcher);
        konfBaru.addTextChangedListener(textWatcher);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_ganti_password_simpan:
                updatePassword();
                break;
            default:
                break;
        }
    }

    private TextWatcher textWatcher = new TextWatcher() {

        String newPass = passBaru.getText().toString();
        String konNewPass = konfBaru.getText().toString();
        String passlama = passLama.getText().toString() ;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            ChangeButtton.setEnabled(!newPass.isEmpty()&&!konNewPass.isEmpty()&&!passlama.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    private void updatePassword(){
        if (inputValidated()){
            final String newPass = passBaru.getText().toString();
            String passlama = passLama.getText().toString();
            String email = mAuth.getCurrentUser().getEmail();
            AuthCredential cred = EmailAuthProvider.getCredential(email,passlama);

            user.reauthenticate(cred).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    progressDialog.dismiss();
                    if (task.isSuccessful()){
                        user.updatePassword(newPass).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(GantiPasswordActivity.this, "password updated", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(GantiPasswordActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else{
                                    Toast.makeText(GantiPasswordActivity.this, "Error Auth Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else  {
                        Toast.makeText(GantiPasswordActivity.this, "Error : "+task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    private boolean inputValidated(){
        boolean ret = true;
        if (passLama.getText().toString().isEmpty()){
            ret = false;
            passLama.setError("this is required");
        }if (passBaru.getText().toString().isEmpty()){
            ret = false;
            passBaru.setError("this is required");
        } else if (!konfBaru.getText().toString().equals(passBaru.getText().toString())){
            ret = false;
            konfBaru.setError("password does not match");
        }
        return ret;
    }

}
