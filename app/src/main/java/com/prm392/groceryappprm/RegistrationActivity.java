package com.prm392.groceryappprm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.prm392.groceryappprm.api.ApiService;
import com.prm392.groceryappprm.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {


    Button signUp;
    EditText email, name, password;
    TextView signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        signUp = findViewById(R.id.login_btn);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email_reg);
        password = findViewById(R.id.password_reg);
        signIn = findViewById(R.id.sign_in);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().trim().length() == 0
                || email.getText().toString().trim().length() == 0
                || password.getText().toString().trim().length() == 0) {
                    Toast.makeText(RegistrationActivity.this, "Some fields are empty", Toast.LENGTH_SHORT).show();
                }
                registerUser();
            }
        });
    }
    private void registerUser() {
        User user = new User(name.getText().toString(), email.getText().toString(), password.getText().toString());
        ApiService.apiService
                .registerUser(user)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Toast.makeText(RegistrationActivity.this, "Call API successfully", Toast.LENGTH_SHORT).show();
                        User user = response.body();
                        if (user.getUserName().trim().length() > 0) {
                            Toast.makeText(RegistrationActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                        } else {
                            Toast.makeText(RegistrationActivity.this, "User already existed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(RegistrationActivity.this, "Call API fail", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}