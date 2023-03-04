package com.prm392.groceryappprm.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.prm392.groceryappprm.MainActivity;
import com.prm392.groceryappprm.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        public void (View view){
//            startActivity(new Intent(HomeActivity.this, MainActivity.class));
//        }

    }
    public void main(View view){
        startActivity(new Intent(HomeActivity.this, MainActivity.class));
    }

    public void login(View view) {
        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
    }

    public void registration(View view) {

        startActivity(new Intent(HomeActivity.this, RegistrationActivity.class));
    }
}