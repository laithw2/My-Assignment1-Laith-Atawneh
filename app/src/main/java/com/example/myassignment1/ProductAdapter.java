package com.example.myassignment1;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.myassignment1.dataaccess.Item;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductAdapter extends ArrayAdapter<Item> {

    public static final String DATA="DATA";
    public ProductAdapter(Context context, List<Item> products) {
        super(context, 0, products);
    }

//source (https://stackoverflow.com/questions/10120119/how-does-the-getview-method-work-when-creating-your-own-custom-adapter)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.product_item, parent, false);
        }

        ImageView image = convertView.findViewById(R.id.productImage);
        TextView name = convertView.findViewById(R.id.productName);
        TextView price = convertView.findViewById(R.id.productPrice);
        Button addToCart = convertView.findViewById(R.id.addToCartBtn);

        image.setImageResource(item.getImageResId());
        name.setText(item.getName());
        price.setText("₪" + item.getPrice());

        addToCart.setOnClickListener(v -> {
            if (item.getQuantity()!=0){
            //setup Shared Preferences and Gson
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
            SharedPreferences.Editor editor = prefs.edit();
            Gson gson = new Gson();


            String json = prefs.getString(DATA, "");
            Item[] existingItems = gson.fromJson(json, Item[].class);
            List<Item> cart = new ArrayList<>();
            if (existingItems != null) {
                cart = new ArrayList<>(Arrays.asList(existingItems));
            }
            cart.add(item);

            editor.putString(DATA, gson.toJson(cart.toArray(new Item[0])));
            editor.commit();

            Toast.makeText(getContext(), item.getName() + " added to cart!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getContext(), item.getName() + " out of stock", Toast.LENGTH_SHORT).show();

            }
        });
        return convertView;
    }
}

