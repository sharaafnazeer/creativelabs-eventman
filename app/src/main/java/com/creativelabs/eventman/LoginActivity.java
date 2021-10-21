package com.creativelabs.eventman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    String userName = "admin";
    String password = "qwerty";

    // Java int string float char boolean
    // 2 Edit Text, Button 2, TextView

    EditText etUsername; // Variable declaration
    EditText etPassword;
    Button btnLogin;
    Button btnNewAccount;
    TextView tvForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login_linear); // 1 . Linear Layout
        setContentView(R.layout.activity_login_constraint);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnRegister);
        btnNewAccount = findViewById(R.id.btnCreateNewAccount);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);

        btnLogin.setOnClickListener(v -> {
            // Logic

            // Validate username and password
            // 1. Check for empty or null values
            // 2 . Check for credentials
            String uname = etUsername.getText().toString();
            String pass = etPassword.getText().toString();

            if (validate(uname, pass)) {
                Intent homeIntent = new Intent(this, HomeActivity.class);
                startActivity(homeIntent);
                finish();
            }
        });

        btnNewAccount.setOnClickListener(v -> {
            Intent registerIntent = new Intent(this, RegisterActivity.class);
            startActivity(registerIntent);
        });
    }

    private boolean validate(String uname, String pass) {
        if (TextUtils.isEmpty(uname)) {
            Toast.makeText(this, "Username is empty", Toast.LENGTH_LONG).show();
            return false;
        } else if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Password is empty", Toast.LENGTH_LONG).show();
            return false;
        } else if (!(userName.equals(uname) && password.equals(pass))) {
            Toast.makeText(this, "Invalid Username or Password", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}