package com.prm392.groceryappprm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.prm392.groceryappprm.R;
import com.prm392.groceryappprm.model.CartItem;
import com.prm392.groceryappprm.utils.BaseUrlConstant;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    Context context;
    List<CartItem> cartItemList;

    public CartAdapter(Context context, List<CartItem> cartItemList) {
        this.context = context;
        this.cartItemList = cartItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(cartItemList.get(position).getImg()).into(holder.img);
        holder.name.setText(cartItemList.get(position).getName());
        holder.quantity.setText(String.valueOf(cartItemList.get(position).getQuantity()));
        holder.price.setText(String.valueOf((int) cartItemList.get(position).getPrice()));
        holder.total.setText(String.valueOf(cartItemList.get(position).getQuantity() * (int) cartItemList.get(position).getPrice()));

        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < BaseUrlConstant.cart.size(); i++) {
                    if (BaseUrlConstant.cart.get(i).getId() == cartItemList.get(holder.getAdapterPosition()).getId()) {
                        BaseUrlConstant.cart.get(i).setQuantity(BaseUrlConstant.cart.get(i).getQuantity() + 1);
                        holder.quantity.setText(String.valueOf(cartItemList.get(holder.getAdapterPosition()).getQuantity()));
                        holder.total.setText(String.valueOf(cartItemList.get(holder.getAdapterPosition()).getQuantity() * (int) cartItemList.get(position).getPrice()));
                    }
                }

            }
        });
        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cartItemList.get(holder.getAdapterPosition()).getQuantity() > 0) {
                    for (int i = 0; i < BaseUrlConstant.cart.size(); i++) {
                        if (BaseUrlConstant.cart.get(i).getId() == cartItemList.get(holder.getAdapterPosition()).getId()) {
                            BaseUrlConstant.cart.get(i).setQuantity(BaseUrlConstant.cart.get(i).getQuantity() - 1);
                            holder.quantity.setText(String.valueOf(cartItemList.get(holder.getAdapterPosition()).getQuantity()));
                            holder.total.setText(String.valueOf(cartItemList.get(holder.getAdapterPosition()).getQuantity() * (int) cartItemList.get(position).getPrice()));
                        }
                    }
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView name, price, quantity, total;
        Button btnAdd, btnRemove;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnAdd = itemView.findViewById(R.id.cart_addBtn);
            btnRemove = itemView.findViewById(R.id.cart_removeBtn);
            img = itemView.findViewById(R.id.cart_itemImg);
            name = itemView.findViewById(R.id.cart_itemName);
            price = itemView.findViewById(R.id.cart_itemPrice);
            quantity = itemView.findViewById(R.id.cart_itemQuantity);
            total = itemView.findViewById(R.id.cart_itemTotal);
        }
    }
}
