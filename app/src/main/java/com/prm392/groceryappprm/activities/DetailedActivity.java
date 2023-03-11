package com.prm392.groceryappprm.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.prm392.groceryappprm.R;
import com.prm392.groceryappprm.model.Product;

import java.math.BigDecimal;

public class DetailedActivity extends AppCompatActivity {

    TextView quantity;
    int totalQuantity = 1;
    BigDecimal totalPrice = BigDecimal.valueOf(0);
    ImageView detailedImg;
    TextView price, rating, description;
    Button addToCart;
    ImageView addItem, removeItem;
    Toolbar toolbar;

    Product product = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Object object = getIntent().getSerializableExtra("detail");
        if (object instanceof Product) {
            product = (Product) object;
        }

        quantity = findViewById(R.id.quantity);
        detailedImg = findViewById(R.id.detailed_img);
        addItem = findViewById(R.id.add_item);
        removeItem = findViewById(R.id.remove_item);
        price = findViewById(R.id.detailed_price);
        description = findViewById(R.id.detailed_dec);
        rating = findViewById(R.id.detailed_rating);

        if (product != null) {
            Glide.with(getApplicationContext()).load(product.getImageUrl()).into(detailedImg);
            rating.setText(String.valueOf(product.getRating()));
            description.setText(product.getProductDescription());
            price.setText("Price: $" + product.getPrice().toString());

            totalPrice = product.getPrice().multiply(BigDecimal.valueOf(totalQuantity));
        }

        addToCart = findViewById(R.id.add_to_cart);

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity < 10) {
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));
                }
            }
        });

        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity > 0) {
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                }
            }
        });

    }
}