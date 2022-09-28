package com.apps.storeapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.apps.storeapp.Adapters.CartAdapter;
import com.apps.storeapp.Database.SharedPrefManager;
import com.apps.storeapp.Models.CartItemObject;
import com.apps.storeapp.R;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private ListView list;
    private List<CartItemObject> items;
    private CartAdapter adapter;
    private Button clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        clear = findViewById(R.id.clear_database);
        list = findViewById(R.id.list);
        items = new ArrayList<>();
        adapter = new CartAdapter(this,R.layout.item_cart,items);
        list.setAdapter(adapter);

        getCart();

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefManager.getInstance(CartActivity.this).clearCart();
                items.clear();
                adapter.notifyDataSetChanged();
                getCart();
            }
        });
    }

    private void getCart(){
        if (SharedPrefManager.getInstance(CartActivity.this).getCart().size()>0){
            for (CartItemObject c : SharedPrefManager.getInstance(CartActivity.this).getCart()){
                items.add(c);
                adapter.notifyDataSetChanged();
            }
        }else {
            Toast.makeText(this, "No items in cart", Toast.LENGTH_SHORT).show();
        }
    }
}
