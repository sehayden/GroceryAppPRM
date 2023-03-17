package com.prm392.groceryappprm.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.prm392.groceryappprm.MainActivity;
import com.prm392.groceryappprm.R;
import com.prm392.groceryappprm.api.ApiService;
import com.prm392.groceryappprm.model.User;
import com.prm392.groceryappprm.utils.BaseUrlConstant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    Button signIn;
    EditText email, password;
    TextView signUp;
    String emailIntent;
    SharedPreferences sharedpreferences;

    //ApiService

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signIn = findViewById(R.id.login_btn);
        email = findViewById(R.id.email_login);
        password = findViewById(R.id.password_login);
        signUp = findViewById(R.id.sign_up);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                loginUser(editor);
            }
        });
    }

    private void loginUser(SharedPreferences.Editor editor) {
        ApiService.apiService
                .loginUser(email.getText().toString(), password.getText().toString())
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Toast.makeText(LoginActivity.this, "Call API successfully", Toast.LENGTH_SHORT).show();
                        User user = response.body();
                        if (user != null && user.getUserName().trim().length() > 0) {
                            BaseUrlConstant.currentUser = user;
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("Email", user.getEmail());
//                            intent.putExtra("Username", user.getUserName());

                            editor.putString("username", user.getUserName());
                            editor.commit();

                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "Wrong email or password", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Call API fail", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}