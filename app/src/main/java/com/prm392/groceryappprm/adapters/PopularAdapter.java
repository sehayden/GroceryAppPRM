package com.prm392.groceryappprm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.prm392.groceryappprm.R;
import com.prm392.groceryappprm.model.Product;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {

    Context context;
    List<Product> popularProductList;

    public PopularAdapter(Context context, List<Product> popularProductList) {
        this.context = context;
        this.popularProductList = popularProductList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(popularProductList.get(position).getImageUrl()).into(holder.imageView);
        holder.name.setText(popularProductList.get(position).getProductName());
        holder.description.setText(popularProductList.get(position).getProductDescription());
        holder.price.setText(popularProductList.get(position).getPrice().toString());
        holder.discount.setText(popularProductList.get(position).getDiscount());

    }

    @Override
    public int getItemCount() {
        return popularProductList.size();
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
