package com.apps.storeapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.apps.storeapp.Models.ProductObject;
import com.apps.storeapp.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ProductsAdapter extends ArrayAdapter<ProductObject> {
    private Context context;
    private List<ProductObject> list;

    public ProductsAdapter(@NonNull Context context, int resource, List<ProductObject> list) {
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
            view = inflater.inflate(R.layout.item_product,parent,false);
        }

        final ProductObject u = list.get(position);

        ImageView image = view.findViewById(R.id.image);
        TextView name = view.findViewById(R.id.name);
        TextView details = view.findViewById(R.id.details);

        name.setText(u.getName());
        details.setText(u.getDetails());
        image.setImageResource(u.getImage());

        return view;

    }
}
