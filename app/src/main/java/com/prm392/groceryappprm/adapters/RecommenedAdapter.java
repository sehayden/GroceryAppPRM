package com.prm392.groceryappprm.adapters;

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
import com.prm392.groceryappprm.R;
import com.prm392.groceryappprm.activities.DetailedActivity;
import com.prm392.groceryappprm.model.Product;

import java.util.List;

public class RecommenedAdapter extends RecyclerView.Adapter<RecommenedAdapter.ViewHolder> {

    Context context;
    List<Product> list;

    public RecommenedAdapter(Context context, List<Product> recommendProductList) {
        this.context = context;
        this.list = recommendProductList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecommenedAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImageUrl()).into(holder.imageView);
        holder.name.setText(list.get(position).getProductName());
        holder.description.setText(list.get(position).getProductDescription());
        holder.price.setText("$" + list.get(position).getPrice().toString());
        holder.discount.setText(list.get(position).getDiscount());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailedActivity.class);
                intent.putExtra("detail", list.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, description, price, discount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.pop_img);
            name = itemView.findViewById(R.id.pop_name);
            description = itemView.findViewById(R.id.pop_des);
            price = itemView.findViewById(R.id.pop_price);
            discount = itemView.findViewById(R.id.pop_dis);
        }
    }
}
