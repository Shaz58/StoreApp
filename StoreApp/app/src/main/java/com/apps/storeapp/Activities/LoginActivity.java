package com.apps.storeapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.storeapp.Database.SharedPrefManager;
import com.apps.storeapp.R;

public class LoginActivity extends AppCompatActivity {

    private EditText username,password;
    private TextView register;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().equals("")){
                    Toast.makeText(LoginActivity.this, "You must write user name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.getText().toString().equals("")){
                    Toast.makeText(LoginActivity.this, "You must write password", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean login = SharedPrefManager.getInstance(LoginActivity.this).loginUser(username.getText().toString(),password.getText().toString());
                if (login == true){
                    Toast.makeText(LoginActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this, "User name or pass word is wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
