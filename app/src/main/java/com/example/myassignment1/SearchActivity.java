package com.example.myassignment1;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myassignment1.dataaccess.Item;
import com.example.myassignment1.dataaccess.itemDA;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private EditText searchTxt;
    private Spinner spinCats;
    private EditText from;
    private EditText to;
    private Button searchBtn;

    private ListView searchList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setUpViews ();
        bindSpinner();
        setSearchBtn ();
    }

    private void setUpViews (){
        searchTxt=findViewById(R.id.searchTxt);
        spinCats=findViewById(R.id.spinCats);
        from=findViewById(R.id.from);
        to=findViewById(R.id.to);
        searchBtn= findViewById(R.id.searchBtn);
        searchList=findViewById(R.id.searchList);

    }

    private void bindSpinner() {
        itemDA data =new itemDA();


        String[] cats = data.getCats();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, cats);
        spinCats.setAdapter(adapter);
    }



    private void setSearchBtn() {
        itemDA itemDA = new itemDA();
        List<Item> allItems = itemDA.getItems();

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameSearch = searchTxt.getText().toString().toLowerCase().trim();
                String selectedCategory = spinCats.getSelectedItem().toString();
                String fromStr = from.getText().toString().trim();
                String toStr = to.getText().toString().trim();

                double fromPrice = fromStr.isEmpty() ? 0 : Double.parseDouble(fromStr);
                double toPrice = toStr.isEmpty() ? Double.MAX_VALUE : Double.parseDouble(toStr);

                List<Item> filteredItems = new ArrayList<>();

                for (Item item : allItems) {
                    if (!nameSearch.isEmpty() && !item.getName().toLowerCase().contains(nameSearch)) {
                        continue;
                    }

                    if (!selectedCategory.equals("All") && !item.getCategory().equalsIgnoreCase(selectedCategory)) {
                        continue;
                    }

                    if (item.getPrice() < fromPrice || item.getPrice() > toPrice) {
                        continue;
                    }

                    filteredItems.add(item);
                }

                // هنا المشكلة كانت، ضيف هذا السطر لتحديث القائمة
                updateItems(filteredItems);
            }
        });
    }


    public void updateItems(List<Item> filteredItems){
        ProductAdapter adapter = new ProductAdapter(this, filteredItems);
        searchList.setAdapter(adapter);
    }

}