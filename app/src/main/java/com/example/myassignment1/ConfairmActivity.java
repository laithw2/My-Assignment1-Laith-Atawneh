package com.example.myassignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ConfairmActivity extends AppCompatActivity {

    private TextView total2,uname,location,phone;
    private Button doneBtn;

    private String totalSum="";
    private String userName="";
    private String userPhone="";
    private String userLocation="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_confairm);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        total2=findViewById(R.id.total2);
        uname=findViewById(R.id.uname2);
       phone=findViewById(R.id.phone);
       location=findViewById(R.id.location);
        doneBtn=findViewById(R.id.doneBtn);

        Intent intent = getIntent();
        totalSum= intent.getStringExtra("Total");
        userName= intent.getStringExtra("NAME");
        userPhone= intent.getStringExtra("Phone");
        userLocation= intent.getStringExtra("Location");


        total2.setText("Total : "+"₪ "+totalSum);
        uname.setText("Name : "+userName);
        phone.setText("Phone : "+userPhone);
        location.setText("Location : "+userLocation);

        setDoneBtn ();


    }

    public void setDoneBtn (){
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfairmActivity.this,HomeActivity.class);
               startActivity(intent);
            }
        });
    }
}