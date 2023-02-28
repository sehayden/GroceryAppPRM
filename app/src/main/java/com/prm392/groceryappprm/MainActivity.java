package com.prm392.groceryappprm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View view) {
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
    }

    public void registration(View view) {
        startActivity(new Intent(MainActivity.this,RegistrationActivity.class));
    }
}