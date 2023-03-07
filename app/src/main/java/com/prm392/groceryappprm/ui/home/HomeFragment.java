package com.prm392.groceryappprm.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prm392.groceryappprm.R;
import com.prm392.groceryappprm.adapters.HomeAdapter;
import com.prm392.groceryappprm.adapters.PopularAdapter;
import com.prm392.groceryappprm.api.ApiService;
import com.prm392.groceryappprm.model.Category;
import com.prm392.groceryappprm.model.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    PopularAdapter popularAdapter;
    HomeAdapter categoryAdapter;
    private RecyclerView popularProductsRecyclerView, homeCatRec;
    private List<Product> popularProducts;
    private List<Category> categories;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        popularProductsRecyclerView = root.findViewById(R.id.pop_rec);
        homeCatRec = root.findViewById(R.id.explorer_rec);

        popularProductsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        popularProducts = new ArrayList<>();
        getPopularProducts();

        homeCatRec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        categories = new ArrayList<>();
        getCategories();

        return root;
    }

    private void getPopularProducts() {
        ApiService.apiService
                .getPopularProducts()
                .enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                        Toast.makeText(getContext(), "Call API successfully", Toast.LENGTH_SHORT).show();
                        popularProducts = response.body();
                        popularAdapter = new PopularAdapter(getContext(), popularProducts);
                        popularProductsRecyclerView.setAdapter(popularAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {
                        Toast.makeText(getContext(), "Call API fail", Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private void getCategories() {
        ApiService.apiService
                .getCategories()
                .enqueue(new Callback<List<Category>>() {
                    @Override
                    public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                        Toast.makeText(getContext(), "Call API successfully", Toast.LENGTH_SHORT).show();
                        categories = response.body();
                        categoryAdapter = new HomeAdapter(getContext(), categories);
                        homeCatRec.setAdapter(categoryAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<Category>> call, Throwable t) {
                        Toast.makeText(getContext(), "Call API fail", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}