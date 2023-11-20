package com.readsystem.JpApp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.readsystem.JpApp.R;

public class LoginActivity extends AppCompatActivity {

    EditText email,contraseña;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getSupportActionBar().hide();


        auth=FirebaseAuth.getInstance();

        email=findViewById(R.id.email);
        contraseña=findViewById(R.id.password);
    }

    public void inicia_sesion(View view) {

        String userEmail=email.getText().toString();
        String userPassword=contraseña.getText().toString();

        if(TextUtils.isEmpty(userEmail)){
            Toast.makeText(this,"Ingrese su email", Toast.LENGTH_SHORT).show();
            return;

        }

        if(TextUtils.isEmpty(userPassword)){
            Toast.makeText(this,"Ingrese su contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        if(userPassword.length() <6){
            Toast.makeText(this,"Contraseña demasiado corta, ingrese un mínimo de 6 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.signInWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this,"Inicio de sesión exitosa",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        }else{
                            Toast.makeText(LoginActivity.this,"Error:"+task.getException(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    public void registrate(View view) {
        startActivity(new Intent(LoginActivity.this,RegistroActivity.class));
    }
}