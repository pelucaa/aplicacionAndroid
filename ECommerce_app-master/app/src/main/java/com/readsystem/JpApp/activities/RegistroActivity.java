package com.readsystem.JpApp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class RegistroActivity extends AppCompatActivity {

    EditText nombre,email,contraseña;
    private FirebaseAuth auth;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //getSupportActionBar().hide();


        auth=FirebaseAuth.getInstance();

        if(auth.getCurrentUser()!=null){
            startActivity(new Intent(RegistroActivity.this,MainActivity.class));
            finish();
        }
        nombre=findViewById(R.id.name);
        email=findViewById(R.id.email);
        contraseña=findViewById(R.id.password);

        sharedPreferences=getSharedPreferences("onBoardingScreen",MODE_PRIVATE);

        boolean isFirstTime= sharedPreferences.getBoolean("primera vez",true);

        if(isFirstTime) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("primera vez", false);
            editor.commit();

            Intent intent = new Intent(RegistroActivity.this, OnBoardingActivity.class);
            startActivity(intent);
            finish();
        }


    }

    public void registrate(View view) {

        String userName=nombre.getText().toString();
        String userEmail=email.getText().toString();
        String userPassword=contraseña.getText().toString();

        if(TextUtils.isEmpty(userName)){
            Toast.makeText(this,"Ingrese su nombre", Toast.LENGTH_SHORT).show();
            return;
        }

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
        auth.createUserWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(RegistroActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegistroActivity.this,"Registrado existosamente",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegistroActivity.this,MainActivity.class));
                        }else{
                            Toast.makeText(RegistroActivity.this,"Registro fallido"+task.getException(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //startActivity(new Intent(RegistroActivity.this,MainActivity.class));
    }

    public void inicia_sesion(View view) {
        startActivity(new Intent(RegistroActivity.this,LoginActivity.class));
    }
}