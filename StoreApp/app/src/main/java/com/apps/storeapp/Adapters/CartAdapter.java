package com.apps.storeapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.apps.storeapp.Models.CartItemObject;
import com.apps.storeapp.Models.ProductObject;
import com.apps.storeapp.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CartAdapter extends ArrayAdapter<CartItemObject> {

    private Context context;
    private List<CartItemObject> list;

    public CartAdapter(@NonNull Context context, int resource, List<CartItemObject> list) {
        super(context, resource,list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_cart,parent,false);
        }

        final CartItemObject u = list.get(position);

        TextView name = view.findViewById(R.id.name);
        TextView qty = view.findViewById(R.id.qty);
        TextView price = view.findViewById(R.id.price);
        TextView has_toppings = view.findViewById(R.id.has_toppings);
        TextView has_cream = view.findViewById(R.id.has_cream);

        name.setText(u.getName());
        qty.setText(String.valueOf(u.getQty()));
        int price_value = u.getQty()*u.getPrice();
        if (u.isHas_toppings()){
            price_value = price_value+3;
        }
        if (u.isHas_cream()){
            price_value = price_value+2;
        }
        price.setText(price_value + "$");
        has_toppings.setText("Has toppings : "+(u.isHas_toppings()?"Yes":"No"));
        has_cream.setText("Has cream : "+(u.isHas_cream()?"Yes":"No"));
        return view;

    }
}
