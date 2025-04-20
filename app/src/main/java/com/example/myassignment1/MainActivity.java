package com.example.myassignment1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myassignment1.dataaccess.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String NAME = "NAME";
    public static final String PASS = "PASS";
    public static final String FLAG = "FLAG";
    private boolean flag = false;
    private EditText edtUname;
    private EditText edtPass;
    private CheckBox chkRem;
    private Button loginBtn;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setupViews();
        setupSharedPrefs();
        checkPrefs();
        setLoginBtn();

    }
    public void setLoginBtn() {
        List <User> users = new ArrayList<>();
        users.add(new User("Laith Atawneh","laith_wa22","Laith@2004","+970595834379","Hebron - Bait Kahel"));
        users.add(new User("Sara Nasser", "sara_n12", "Sara#123","+970592834475","Rammalah"));
        users.add(new User("Omar Khaled", "omar_k99", "Omar*789","+970295884371","Nablus - Assira"));
        users.add(new User("Lina Zayed", "lina_z77", "Lina@456","+970515832377","Tulkarm - Anabta"));
        users.add(new User("Ahmad Qassem", "ahmad_q55", "Ahmad!321","+970595934343","Tubas - Tammoun"));
        loginBtn=findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String name = edtUname.getText().toString();
        String pass = edtPass.getText().toString();
        User user = canLogin(name,pass,users);
        if (user !=null){
        Intent intent = new Intent(MainActivity.this,HomeActivity.class);
        intent.putExtra("NAME",user.getName());
            intent.putExtra("Location",user.getLocation());
            intent.putExtra("Phone",user.getPhone());


            startActivity(intent);}else {
            Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();

        }

    }
});

    }

    public User canLogin(String username, String password, List<User> users) {
        for (User user : users) {
            if (user.getUname().equals(username) && user.getPassword().equals(password)) {
                return user; // Login success
            }
        }
        return null; // Login failed
    }

    private void checkPrefs() {
        flag = prefs.getBoolean(FLAG, false);

        if(flag){
            String name = prefs.getString(NAME, "");
            String password = prefs.getString(PASS, "");
            edtUname.setText(name);
            edtPass.setText(password);
            chkRem.setChecked(true);
        }
    }

    private void setupSharedPrefs() {
        prefs= PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }

    private void setupViews() {
        edtUname = findViewById(R.id.editUname);
        edtPass = findViewById(R.id.editPass);
        chkRem = findViewById(R.id.chkRem);
    }


    public void btnLoginOnClick(View view) {
        String name = edtUname.getText().toString();
        String password = edtPass.getText().toString();

        if(chkRem.isChecked()){
            if(!flag) {
                editor.putString(NAME, name);
                editor.putString(PASS, password);
                editor.putBoolean(FLAG, true);
                editor.commit();
            }

        }

    }

}