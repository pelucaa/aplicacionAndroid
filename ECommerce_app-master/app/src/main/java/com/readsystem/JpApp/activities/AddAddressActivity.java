package com.readsystem.JpApp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.readsystem.JpApp.R;

import java.util.HashMap;
import java.util.Map;

public class AddAddressActivity extends AppCompatActivity {

    EditText nombre,direccion,ciudad,codigoPostal,numeroTelefonico;
    Toolbar toolbar;

    Button addAddressBtn;

    FirebaseFirestore firestore;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        toolbar= findViewById(R.id.add_address_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        auth= FirebaseAuth.getInstance();
        firestore= FirebaseFirestore.getInstance();

        nombre= findViewById(R.id.ad_name);
        direccion= findViewById(R.id.ad_address);
        ciudad= findViewById(R.id.ad_city);
        numeroTelefonico= findViewById(R.id.ad_phone);
        codigoPostal= findViewById(R.id.ad_code);
        addAddressBtn= findViewById(R.id.ad_add_address);

        addAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName= nombre.getText().toString();
                String userCity= ciudad.getText().toString();
                String userAddress= direccion.getText().toString();
                String userCode= codigoPostal.getText().toString();
                String userNumber= numeroTelefonico.getText().toString();

                String final_address="";

                if(!userName.isEmpty()){
                    final_address+=userName;

                }
                if(!userCity.isEmpty()){
                    final_address+=userCity;

                }
                if(!userAddress.isEmpty()){
                    final_address+=userAddress;

                }
                if(!userCode.isEmpty()){
                    final_address+=userCode;

                }
                if(!userNumber.isEmpty()){
                    final_address+=userNumber;

                }
                if(!userName.isEmpty()&& !userCity.isEmpty()&& !userAddress.isEmpty() && !userCode.isEmpty() && !userNumber.isEmpty()){
                    Map<String, String> map =new HashMap<>();
                    map.put("dirección de usuario",final_address);

                    firestore.collection("Usuario actual").document(auth.getCurrentUser().getUid())
                            .collection("Direccion").add(map).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(AddAddressActivity.this,"Dirección agregada",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(AddAddressActivity.this,DetailedActivity.class));
                                finish();
                            }
                        }
                    });

                }else{
                    Toast.makeText(AddAddressActivity.this,"Amablemente llene todo el campo", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}