package com.readsystem.JpApp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.readsystem.JpApp.R;
import com.readsystem.JpApp.models.NewProductsModel;
import com.readsystem.JpApp.models.PopularProductsModel;
import com.readsystem.JpApp.models.ShowAllModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailedActivity extends AppCompatActivity {

    ImageView detailedImg;
    TextView calificacion,nombre,descripcion,precio,cantidad;
    Button addToCart,buyNow;
    ImageView addItems,removeItems;

    Toolbar toolbar;
    int totalCantidad= 1;
    int totalPrecio=0;

    //nuevo productos
    NewProductsModel newProductsModel = null;

    //popular products
    PopularProductsModel popularProductsModel = null;

    //show all
    ShowAllModel showAllModel= null;

    FirebaseAuth auth;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        toolbar=findViewById(R.id.detailed_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        firestore= FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        final Object obj= getIntent().getSerializableExtra("detalle");
        if(obj instanceof NewProductsModel){
            newProductsModel = (NewProductsModel) obj;
        }else if(obj instanceof PopularProductsModel){
            popularProductsModel= (PopularProductsModel) obj;
        }else if(obj instanceof ShowAllModel){
            showAllModel= (ShowAllModel) obj;
        }

        detailedImg= findViewById(R.id.detailed_img);
        cantidad= findViewById(R.id.quantity);
        nombre= findViewById(R.id.detailed_name);
        calificacion= findViewById(R.id.rating);
        descripcion= findViewById(R.id.detailed_desc);
        precio= findViewById(R.id.detailed_price);

        addToCart= findViewById(R.id.add_to_cart);
        buyNow= findViewById(R.id.buy_now);

        addItems= findViewById(R.id.add_item);
        removeItems= findViewById(R.id.remove_item);

        //new products
        if(newProductsModel !=null){
            Glide.with(getApplicationContext()).load(newProductsModel.getImg_url()).into(detailedImg);
            nombre.setText(newProductsModel.getNombre());
            calificacion.setText(newProductsModel.getCalificacion());
            descripcion.setText(newProductsModel.getDescripcion());
            precio.setText(String.valueOf(newProductsModel.getPrecio()));
            nombre.setText(newProductsModel.getNombre());

            totalPrecio=newProductsModel.getPrecio() * totalCantidad;
            }


        //Popular products
        if(popularProductsModel !=null){
            Glide.with(getApplicationContext()).load(popularProductsModel.getImg_url()).into(detailedImg);
            nombre.setText(popularProductsModel.getNombre());
            calificacion.setText(popularProductsModel.getCalificacion());
            descripcion.setText(popularProductsModel.getDescripcion());
            precio.setText(String.valueOf(popularProductsModel.getPrecio()));
            nombre.setText(popularProductsModel.getNombre());

            totalPrecio=popularProductsModel.getPrecio() * totalCantidad;
        }

        //Show all products
        if(showAllModel !=null){
            Glide.with(getApplicationContext()).load(showAllModel.getImg_url()).into(detailedImg);
            nombre.setText(showAllModel.getNombre());
            calificacion.setText(showAllModel.getCalificacion());
            descripcion.setText(showAllModel.getDescripcion());
            precio.setText(String.valueOf(showAllModel.getPrecio()));
            nombre.setText(showAllModel.getNombre());

            totalPrecio=showAllModel.getPrecio() * totalCantidad;
        }

        //comprar ahora
        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailedActivity.this,AddressActivity.class);

                if(newProductsModel !=null){
                    intent.putExtra("articulo",newProductsModel);
                }
                if(popularProductsModel !=null){
                    intent.putExtra("articulo",popularProductsModel);
                }
                if(showAllModel !=null){
                    intent.putExtra("articulo",showAllModel);
                }
                startActivity(intent);

            }
        });
        //add to cart
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addtoCart();
            }
        });

        addItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(totalCantidad<10){
                   totalCantidad++;
                   cantidad.setText(String.valueOf(totalCantidad));

                   if(newProductsModel !=null){
                       totalPrecio= newProductsModel.getPrecio() * totalCantidad;

                   }
                   if(popularProductsModel !=null){
                       totalPrecio= popularProductsModel.getPrecio() * totalCantidad;
                   }if(showAllModel !=null){
                       totalPrecio= showAllModel.getPrecio() * totalCantidad;
                   }
               }
            }
        });

        removeItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalCantidad>1){
                    totalCantidad--;
                    cantidad.setText(String.valueOf(totalCantidad));
                }
            }
        });
        }

    private void addtoCart() {
        String saveCurrentTime,saveCurrentDate;

        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate= new SimpleDateFormat("dd MM, yyyy");
        saveCurrentDate= currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime= new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime= currentTime.format(calForDate.getTime());

        final HashMap<String,Object> cartMap =new HashMap<>();

        cartMap.put("productoNombre", nombre.getText().toString());
        cartMap.put("productoPrecio",precio.getText().toString());
        cartMap.put("tiempoActual",saveCurrentTime);
        cartMap.put("fechaActual",saveCurrentDate);
        cartMap.put("totalCantidad",cantidad.getText().toString());
        cartMap.put("totalPrecio",totalPrecio);

        firestore.collection("Añadir al carrito").document(auth.getCurrentUser().getUid())
                .collection("Usuario").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(DetailedActivity.this,"Añadido al carrito",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }


}
