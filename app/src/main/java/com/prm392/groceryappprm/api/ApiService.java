package com.prm392.groceryappprm.api;

import static com.prm392.groceryappprm.utils.BaseUrlConstant.baseUrl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prm392.groceryappprm.model.Product;
import com.prm392.groceryappprm.model.User;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    //http://localhost:8080/api/customer/login?email=abc@gmail.com&password=123
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("api/customer/login")
    Call<User> loginUser(@Query("email") String email,
                         @Query("password") String password);

    @POST("api/customer")
    Call<User> registerUser(@Body User user);

    @GET("api/product/popular")
    Call<List<Product>> getPopularProducts();
}
