package com.prm392.groceryappprm.ui.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prm392.groceryappprm.R;
import com.prm392.groceryappprm.adapters.CartAdapter;
import com.prm392.groceryappprm.model.CartItem;
import com.prm392.groceryappprm.utils.BaseUrlConstant;

import java.util.ArrayList;
import java.util.List;


public class MyCartsFragment extends Fragment {


    CartAdapter cartAdapter;
    private RecyclerView cartRecyclerView;
    private TextView tvTotalPrice;
    private List<CartItem> cartItemList;

    public MyCartsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_my_carts, container, false);

        cartRecyclerView = root.findViewById(R.id.cart_rec);
        tvTotalPrice = root.findViewById(R.id.tvTotalPrice);


        cartRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        cartItemList = new ArrayList<>();
        cartItemList = BaseUrlConstant.cart;
        cartAdapter = new CartAdapter(getContext(), cartItemList);
        cartRecyclerView.setAdapter(cartAdapter);

        if (!BaseUrlConstant.cart.isEmpty()) {
            tvTotalPrice.setText("Total Price: $" + BaseUrlConstant.cart.stream().mapToLong(c -> c.getPrice()).sum());
        }


        return root;
    }
}