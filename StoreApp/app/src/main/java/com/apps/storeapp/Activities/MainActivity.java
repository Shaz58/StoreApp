package com.apps.storeapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.apps.storeapp.Adapters.ProductsAdapter;
import com.apps.storeapp.Database.SharedPrefManager;
import com.apps.storeapp.Models.ProductObject;
import com.apps.storeapp.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button logout;
    private ListView list;
    private List<ProductObject> products;
    private ProductsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logout = findViewById(R.id.logout);
        list = findViewById(R.id.list);
        products = new ArrayList<>();
        adapter = new ProductsAdapter(this,R.layout.item_product,products);
        list.setAdapter(adapter);

        ProductObject p1 = new ProductObject();
        p1.setId(1);
        p1.setName("Cappuccino");
        p1.setDetails("A cappuccino is an espresso-based coffee drink that originated in Italy, and is traditionally prepared with steamed milk foam. Variations of the drink involve the use of cream instead of milk, using non-dairy milks, and flavoring with cinnamon or chocolate powder. ");
        p1.setImage(R.drawable.cappuccino);
        p1.setPrice(15);
        products.add(p1);

        ProductObject p2 = new ProductObject();
        p2.setId(2);
        p2.setName("Green tea");
        p2.setDetails("Green tea is a type of tea that is made from Camellia sinensis leaves and buds that have not undergone the same withering and oxidation process used to make oolong teas and black teas. Green tea originated in China, but its production and manufacture has spread to other countries in East Asia.");
        p2.setImage(R.drawable.green_tea);
        p2.setPrice(20);
        products.add(p2);

        ProductObject p3 = new ProductObject();
        p3.setId(3);
        p3.setName("Latte");
        p3.setDetails("Caffe latte is a coffee drink made with espresso and steamed milk. The word comes from the Italian caffè e latte, caffelatte or caffellatte, which means \"coffee & milk\".");
        p3.setImage(R.drawable.latte);
        p3.setPrice(10);
        products.add(p3);

        ProductObject p4 = new ProductObject();
        p1.setId(4);
        p4.setName("Smoothie");
        p4.setDetails("A smoothie is a drink made from pureed raw fruit and/or vegetables, typically using a blender. A smoothie often has a liquid base such as water, fruit juice, plant milk, and sometimes dairy products, such as milk, yogurt, ice cream or cottage cheese.");
        p4.setImage(R.drawable.smoothie);
        p4.setPrice(12);
        products.add(p4);

        adapter.notifyDataSetChanged();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ProductDetailsActivity.class);
                intent.putExtra("product",new Gson().toJson(products.get(position)));
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefManager.getInstance(MainActivity.this).clearCart();
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.cart){
            Intent intent = new Intent(MainActivity.this,CartActivity.class);
            startActivity(intent);
            return true;
        }

        return false;
    }

    @Override
    public void onBackPressed() {
        return;
    }
}
