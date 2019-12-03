package com.codinginflow.trashapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog alertDialog;
    private EditText etEmail, etPassword;
    private Button btnLogin;
    private FirebaseAuth mAuth;
    private ProgressDialog progress;
    private TextView forgotPass , tvDaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail = findViewById(R.id.et_login_username);
        etPassword = findViewById(R.id.et_login_password);

        //Daftar
        tvDaftar = findViewById(R.id.tv_login_daftar);
        tvDaftar.setOnClickListener(this);
        //inisialisasi firebase
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        progress = new ProgressDialog(this);
        progress.setMessage("logging in, please wait");
        //forgot password
        forgotPass = findViewById(R.id.tv_login_forgot);

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,ForgotPasswordActivity.class));
            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser!=null){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_login_daftar:
                Intent intent = new Intent(this, DaftarActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login_login :
                progress.show();
                SignIn();
                break;
        }
    }
    private void SignIn(){
        if (inputValidated()){
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    showProgressDialog();
                    if (task.isSuccessful()){
                        progress.dismiss();
                        Toast.makeText(getApplicationContext(), "sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else{
                        progress.dismiss();
                        String err = task.getException().getMessage();
                        if (err.contains("password")){
                            etPassword.setError(err);
                        } else {
                            Toast.makeText(getApplicationContext(), err, Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
        }
    }
    private boolean inputValidated(){
        boolean res = true;
        if (etEmail.getText().toString().isEmpty()){
            res = false;
            etEmail.setError("This is required");
        }if (etPassword.getText().toString().isEmpty()){
            res = false;
            etPassword.setError("This is required");
        }
        return res;
    }

    public void showProgressDialog() {
        dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.progress_dialog, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);
        alertDialog = dialogBuilder.create();
        alertDialog.show();
    }
}
