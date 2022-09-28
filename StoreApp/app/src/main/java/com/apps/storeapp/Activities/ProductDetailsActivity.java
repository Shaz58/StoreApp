package com.apps.storeapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.storeapp.Database.SharedPrefManager;
import com.apps.storeapp.Models.CartItemObject;
import com.apps.storeapp.Models.ProductObject;
import com.apps.storeapp.R;
import com.google.gson.Gson;

public class ProductDetailsActivity extends AppCompatActivity {

    private ImageView image;
    private TextView name,price,qty,details;
    private Button plus,minus,add_to_cart;
    private RadioButton hot,iced;
    private CheckBox chk_toppings,chk_cream;
    private int selected_temp = 0;
    private boolean has_toppings = false;
    private boolean has_cream = false;

    private ProductObject p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        image = findViewById(R.id.image);
        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        qty = findViewById(R.id.qty);
        details = findViewById(R.id.details);
        plus = findViewById(R.id.plus);
        minus = findViewById(R.id.minus);
        add_to_cart = findViewById(R.id.add_to_cart);
        hot = findViewById(R.id.hot);
        iced = findViewById(R.id.iced);
        chk_toppings = findViewById(R.id.toppings);
        chk_cream = findViewById(R.id.cream);

        getData();

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int val = Integer.valueOf(qty.getText().toString());
                val = val + 1;
                qty.setText(String.valueOf(val));
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int val = Integer.valueOf(qty.getText().toString());
                if (val > 0){
                    val = val -1;
                    qty.setText(String.valueOf(val));
                }
            }
        });

        hot.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    selected_temp = 1;
                }
            }
        });

        iced.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    selected_temp = 2;
                }
            }
        });

        chk_toppings.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                has_toppings = isChecked;
            }
        });

        chk_cream.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                has_cream = isChecked;
            }
        });

        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (qty.getText().toString().equals("0")){
                    Toast.makeText(ProductDetailsActivity.this, "You must pick a quantity", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (SharedPrefManager.getInstance(ProductDetailsActivity.this).checkIfItemExistsInCart(p.getId()) == true){
                    Toast.makeText(ProductDetailsActivity.this, "This product is already in the cart", Toast.LENGTH_SHORT).show();
                    return;
                }

                CartItemObject c = new CartItemObject();
                c.setId(p.getId());
                c.setName(p.getName());
                c.setDetails(p.getDetails());
                c.setPrice(p.getPrice());
                c.setHas_cream(has_cream);
                c.setHas_toppings(has_toppings);
                c.setTemp(selected_temp);
                c.setQty(Integer.valueOf(qty.getText().toString()));

                SharedPrefManager.getInstance(ProductDetailsActivity.this).AddToCart(c);
                Toast.makeText(ProductDetailsActivity.this, "Added to cart", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ProductDetailsActivity.this,CartActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void getData(){
        String json = getIntent().getStringExtra("product");
        p = new Gson().fromJson(json,ProductObject.class);

        image.setImageResource(p.getImage());
        name.setText(p.getName());
        price.setText(p.getPrice()+"$");
        details.setText(p.getDetails());
    }
}
