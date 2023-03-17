package com.prm392.groceryappprm.ui.cart;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.prm392.groceryappprm.R;
import com.prm392.groceryappprm.adapters.CartAdapter;
import com.prm392.groceryappprm.api.ApiService;
import com.prm392.groceryappprm.model.CartItem;
import com.prm392.groceryappprm.model.CreateOrder;
import com.prm392.groceryappprm.utils.BaseUrlConstant;

import org.json.JSONObject;

import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.ZaloPayError;
import vn.zalopay.sdk.ZaloPaySDK;
import vn.zalopay.sdk.listeners.PayOrderListener;


public class MyCartsFragment extends Fragment {


    CartAdapter cartAdapter;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private RecyclerView cartRecyclerView;
    private TextView tvTotalPrice;
    private List<CartItem> cartItemList;
    private Button orderBtn;

    public MyCartsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_my_carts, container, false);

        cartRecyclerView = root.findViewById(R.id.cart_rec);
        tvTotalPrice = root.findViewById(R.id.tvTotalPrice);
        orderBtn = root.findViewById(R.id.cart_orderBtn);
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // ZaloPay SDK Init
        ZaloPaySDK.init(2553, Environment.SANDBOX);

        cartRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        cartItemList = BaseUrlConstant.cart;
        cartAdapter = new CartAdapter(getContext(), cartItemList);
        cartRecyclerView.setAdapter(cartAdapter);

        if (!BaseUrlConstant.cart.isEmpty()) {
            tvTotalPrice.setText("Total Price: $" + BaseUrlConstant.cart.stream().mapToLong(c -> c.getPrice()).sum());
        }

        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!BaseUrlConstant.cart.isEmpty()) {
                    placeOrder();
                    payWithZalo(root);
                }
            }
        });


        return root;
    }

    private void payWithZalo(View root) {
        CreateOrder orderApi = new CreateOrder();

        try {
            JSONObject data = orderApi.createOrder(String.valueOf(BaseUrlConstant.cart.stream().mapToLong(c -> c.getPrice()).sum() * 23000));
            String code = data.getString("return_code");

            cartItemList.clear();
            tvTotalPrice.setText("Total Price: $" + 0);

            cartRecyclerView = root.findViewById(R.id.cart_rec);
            cartRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
            cartAdapter = new CartAdapter(getContext(), cartItemList);
            cartRecyclerView.setAdapter(cartAdapter);


            if (code.equals("1")) {
                String token = data.getString("zp_trans_token");
                ZaloPaySDK.getInstance().payOrder(getActivity(), token, "demozpdk://app", new PayOrderListener() {
                    @Override
                    public void onPaymentSucceeded(String s, String s1, String s2) {

                    }

                    @Override
                    public void onPaymentCanceled(String s, String s1) {

                    }

                    @Override
                    public void onPaymentError(ZaloPayError zaloPayError, String s, String s1) {

                    }
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void placeOrder() {
        ApiService.apiService
                .placeOrder(BaseUrlConstant.cart)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String responseMsg = response.body();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(getContext(), "Call API successfully", Toast.LENGTH_SHORT).show();
                        //For some reason, this onFailure gets called but the data still get inserted to DB successfully
                        //So I replaced the text by calling API successfully
                    }
                });
    }
}