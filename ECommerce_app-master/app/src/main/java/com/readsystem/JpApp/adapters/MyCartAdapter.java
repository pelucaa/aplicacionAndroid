package com.readsystem.JpApp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.readsystem.JpApp.R;
import com.readsystem.JpApp.models.MyCartModel;

import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {

    Context context;
    List<MyCartModel> list;
    int totalAmount=0;

    public MyCartAdapter(Context context, List<MyCartModel> list){
        this.context= context;
        this.list= list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart_item,parent));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.fecha.setText(list.get(position).getFechaActual());
        holder.tiempo.setText(list.get(position).getTiempoActual());
        holder.precio.setText(list.get(position).getProductoPrecio()+"S/.");
        holder.nombre.setText(list.get(position).getProductoNombre());
        holder.totalPrecio.setText(String.valueOf(list.get(position).getTotalPrecio()));
        holder.totalCantidad.setText(list.get(position).getTotalCantidad());

        //total amount pass to cart activity
        totalAmount = totalAmount + list.get(position).getTotalPrecio();
        Intent intent= new Intent("Mi monto total");
        intent.putExtra("cantidad total",totalAmount);

        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre,precio,fecha,tiempo,totalCantidad,totalPrecio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre= itemView.findViewById(R.id.product_name);
            precio= itemView.findViewById(R.id.product_price);
            fecha= itemView.findViewById(R.id.current_date);
            tiempo= itemView.findViewById(R.id.current_time);
            totalCantidad= itemView.findViewById(R.id.total_quantity);
            totalPrecio= itemView.findViewById(R.id.total_price);
        }
    }
}
