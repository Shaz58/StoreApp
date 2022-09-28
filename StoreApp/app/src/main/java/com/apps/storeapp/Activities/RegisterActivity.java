package com.apps.storeapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.apps.storeapp.Database.SharedPrefManager;
import com.apps.storeapp.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText username,password,confirm;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        confirm = findViewById(R.id.confirm);
        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().equals("")){
                    Toast.makeText(RegisterActivity.this, "You must write the user name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.getText().toString().equals("")){
                    Toast.makeText(RegisterActivity.this, "You must write the password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (confirm.getText().toString().equals("")){
                    Toast.makeText(RegisterActivity.this, "You must confirm your password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!confirm.getText().toString().equals(password.getText().toString())){
                    Toast.makeText(RegisterActivity.this, "You must confirm your password correctly", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (SharedPrefManager.getInstance(RegisterActivity.this).checkUserNameDublication(username.getText().toString()) == true){
                    Toast.makeText(RegisterActivity.this, "User name is already exists", Toast.LENGTH_SHORT).show();
                    return;
                }

                SharedPrefManager.getInstance(RegisterActivity.this).RegisterUser(username.getText().toString(),password.getText().toString());
                Toast.makeText(RegisterActivity.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
