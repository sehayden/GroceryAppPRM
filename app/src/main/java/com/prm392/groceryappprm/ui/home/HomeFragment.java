package com.prm392.groceryappprm.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.prm392.groceryappprm.R;
import com.prm392.groceryappprm.activities.LoginActivity;
import com.prm392.groceryappprm.adapters.PopularAdapter;
import com.prm392.groceryappprm.api.ApiService;
import com.prm392.groceryappprm.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    private RecyclerView popularProductsRecyclerView;
    private List<Product> popularProducts;
    PopularAdapter popularAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        return  root;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        popularProductsRecyclerView = view.findViewById(R.id.pop_rec);
        popularProductsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        popularProducts = new ArrayList<>();
        popularProducts.add(new Product(1, "h", "h", "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg","h", new BigDecimal("0.1"), 1, "j"));
        //getPopularProducts();
        popularAdapter = new PopularAdapter(getContext(), popularProducts);
        popularProductsRecyclerView.setAdapter(popularAdapter);
    }

    private void getPopularProducts() {
        ApiService.apiService
                .getPopularProducts()
                .enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                        Toast.makeText(getContext(), "Call API successfully", Toast.LENGTH_SHORT).show();
                        popularProducts = response.body();
                        if (popularProducts != null &&popularProducts.get(0) != null) {
                            for (int i = 0; i < popularProducts.size(); i++) {
                                Product product = new Product(popularProducts.get(i).getProductId(),
                                        popularProducts.get(i).getProductName(),
                                        popularProducts.get(i).getProductDescription(),
                                        popularProducts.get(i).getImageUrl(),
                                        popularProducts.get(i).getDiscount(),
                                        popularProducts.get(i).getPrice(),
                                        popularProducts.get(i).getRating(),
                                        popularProducts.get(i).getProductType());
                                popularProducts.add(product);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {
                        Toast.makeText(getContext(), "Call API fail", Toast.LENGTH_SHORT).show();

                    }
                });
    }
}