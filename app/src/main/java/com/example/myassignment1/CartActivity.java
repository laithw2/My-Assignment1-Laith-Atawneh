package com.example.myassignment1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myassignment1.dataaccess.Item;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private ListView cartListView;
    private TextView total;

    private Button checkOutBtn;
    private SharedPreferences prefs;
    private  SharedPreferences.Editor editor ;

    private float totalSum=0;
    private String userName="";
    private String userPhone="";
    private String userLocation="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent = getIntent();
        userName= intent.getStringExtra("NAME");
        userPhone= intent.getStringExtra("Phone");
        userLocation= intent.getStringExtra("Location");

        setUpViews ();
        loadCartItems();
        setCheckOutBtn();

    }

    private void setUpViews (){
        cartListView=findViewById(R.id.cartListView);
        total=findViewById(R.id.total);
        checkOutBtn=findViewById(R.id.chkBtn);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }

    private void loadCartItems() {
        Gson gson = new Gson();
        String json = prefs.getString(ProductAdapter.DATA, "");
        if (!json.equals("")) {
            Item[] items = gson.fromJson(json, Item[].class);
            for (Item item:items) {
                totalSum+=item.getPrice();
            }

            ArrayAdapter<Item> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);

            cartListView.setAdapter(adapter);

            total.setText("Total : "+"₪ "+totalSum);
        } else {
            Toast.makeText(this, "Cart is empty", Toast.LENGTH_SHORT).show();
        }
    }

    private void setCheckOutBtn(){
        checkOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                String json = prefs.getString(ProductAdapter.DATA, "");

                if (!json.equals("")) {
                    Item[] items = gson.fromJson(json, Item[].class);

                    for (Item item:items) {
                        int quant=item.getQuantity();
                        item.setQuantity(quant-1);
                    }
                }

                editor.remove(ProductAdapter.DATA);
                editor.commit();

                cartListView.setAdapter(null);

                total.setText("Total : "+"₪ "+0);


                Toast.makeText(CartActivity.this, "Thank you for your purchase!", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(CartActivity.this,ConfairmActivity.class);
                intent.putExtra("Total", String.valueOf( totalSum));
                intent.putExtra("NAME",userName);
                intent.putExtra("Location",userLocation);
                intent.putExtra("Phone",userPhone);
                startActivity(intent);


            }
        });
    }
}