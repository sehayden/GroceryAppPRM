package com.prm392.groceryappprm;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prm392.groceryappprm.adapters.CartAdapter;
import com.prm392.groceryappprm.model.CartItem;
import com.prm392.groceryappprm.utils.BaseUrlConstant;

import java.util.ArrayList;
import java.util.List;


public class MyCartsFragment extends Fragment {



    public MyCartsFragment() {
        // Required empty public constructor
    }
    private RecyclerView cartRecyclerView;
    private List<CartItem> cartItemList;
    CartAdapter cartAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_my_carts, container, false);

        cartRecyclerView = root.findViewById(R.id.cart_rec);

        cartRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        cartItemList = new ArrayList<>();
        cartItemList = BaseUrlConstant.cart;
        cartAdapter = new CartAdapter(getContext(), cartItemList);
        cartRecyclerView.setAdapter(cartAdapter);

        return root;
    }
}