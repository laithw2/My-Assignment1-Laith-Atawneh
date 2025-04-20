package com.example.myassignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myassignment1.dataaccess.Item;
import com.example.myassignment1.dataaccess.itemDA;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
     private TextView welcomeLab;
     private ListView itemsList;
    private FloatingActionButton cartBtn;
    private FloatingActionButton searchBtn;

    private String userName="";
    private String userPhone="";
    private String userLocation="";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent = getIntent();
        userName= intent.getStringExtra("NAME");
        userPhone= intent.getStringExtra("Phone");
        userLocation= intent.getStringExtra("Location");

        welcomeLab=findViewById(R.id.wellcomeLab);
        welcomeLab.setText("Wellcome " + userName + " to our shop");
        setupList();
        setCartBtn();
        setSearchBtn();
    }
    public void setupList(){
        itemsList=findViewById(R.id.ittemsList);
        itemDA itemDA = new itemDA();
        List<Item> items=itemDA.getItems();
        ProductAdapter adapter = new ProductAdapter(this, items);
        itemsList.setAdapter(adapter);

    }

    public void setCartBtn(){
        cartBtn=findViewById(R.id.cartBtn);
        cartBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,CartActivity.class);
                intent.putExtra("NAME",userName);
                intent.putExtra("Location",userLocation);
                intent.putExtra("Phone",userPhone);
                startActivity(intent);
            }
        });
    }

    public void setSearchBtn(){
        searchBtn=findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });
    }
}