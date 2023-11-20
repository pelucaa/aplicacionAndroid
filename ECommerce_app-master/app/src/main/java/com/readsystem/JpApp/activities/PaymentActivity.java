package com.readsystem.JpApp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.readsystem.JpApp.R;

public class PaymentActivity extends AppCompatActivity {

    double amount= 0.0;
    Toolbar toolbar;
    TextView subTotal,descuento,envio,total;
    Button paymentBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        //Toolbar
        toolbar = findViewById(R.id.payment_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        amount= getIntent().getDoubleExtra("Monto",0.0);

        subTotal= findViewById(R.id.sub_total);
        descuento= findViewById(R.id.textView17);
        envio= findViewById(R.id.textView18);
        total= findViewById(R.id.total_amt);
        paymentBtn= findViewById(R.id.pay_btn);

        subTotal.setText("S/. "+amount);

        paymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }


    }
