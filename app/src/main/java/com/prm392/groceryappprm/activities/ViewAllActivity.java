package com.prm392.groceryappprm.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prm392.groceryappprm.R;
import com.prm392.groceryappprm.adapters.ViewAllAdapter;
import com.prm392.groceryappprm.api.ApiService;
import com.prm392.groceryappprm.model.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewAllActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ViewAllAdapter viewAllAdapter;
    List<Product> productList;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.view_all_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();

        productList = new ArrayList<>();
        getProductsByCategory(this, Integer.parseInt(intent.getStringExtra("categoryId")));

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void getProductsByCategory(Context context, int categoryId) {
        ApiService.apiService
                .getProductByCategory(categoryId)
                .enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                        productList = response.body();
                        viewAllAdapter = new ViewAllAdapter(context, productList);
                        recyclerView.setAdapter(viewAllAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Call API fail", Toast.LENGTH_SHORT).show();

                    }
                });
    }
}