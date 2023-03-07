package com.prm392.groceryappprm.ui.category;

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
import com.prm392.groceryappprm.adapters.CategoryAdapter;
import com.prm392.groceryappprm.api.ApiService;
import com.prm392.groceryappprm.model.Category;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CategoryFragment extends Fragment {

    CategoryAdapter categoryAdapter;
    private RecyclerView categoryRecyclerView;
    private List<Category> categories;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_category, container, false);
        categoryRecyclerView = root.findViewById(R.id.cat_rec);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        categories = new ArrayList<>();

        getCategories();

        return root;
    }

    private void getCategories() {
        ApiService.apiService
                .getCategories()
                .enqueue(new Callback<List<Category>>() {
                    @Override
                    public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                        Toast.makeText(getContext(), "Call API successfully", Toast.LENGTH_SHORT).show();
                        categories = response.body();
                        categoryAdapter = new CategoryAdapter(getContext(), categories);
                        categoryRecyclerView.setAdapter(categoryAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<Category>> call, Throwable t) {
                        Toast.makeText(getContext(), "Call API fail", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}