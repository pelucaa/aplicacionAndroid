package com.readsystem.JpApp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.readsystem.JpApp.R;
import com.readsystem.JpApp.activities.DetailedActivity;
import com.readsystem.JpApp.models.NewProductsModel;

import java.util.List;

public class NewProductsAdapter extends RecyclerView.Adapter<NewProductsAdapter.ViewHolder> {

  private Context context;
  private List<NewProductsModel>list;

    public NewProductsAdapter(Context context, List<NewProductsModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.new_products,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.newImg);
        holder.newNombre.setText(list.get(position).getNombre());
        holder.newPrecio.setText(String.valueOf(list.get(position).getPrecio()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, DetailedActivity.class);
                intent.putExtra("detalle", list.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView newImg;
        TextView newNombre,newPrecio;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            newImg= itemView.findViewById(R.id.new_img);
            newNombre= itemView.findViewById(R.id.new_product_name);
            newPrecio= itemView.findViewById(R.id.new_price);
        }
    }
}
