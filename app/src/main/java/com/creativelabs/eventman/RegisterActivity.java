package com.creativelabs.eventman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    EditText etFirstName, etLastName, etEmail, etPassword, etConfirmPassword;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);

        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> {
            if (validate(v)) {
                // Submit the details
                finish();
            }
        });
    }

    private boolean validate(View view) {

        if (TextUtils.isEmpty(etFirstName.getText().toString())) {
            Snackbar.make(view, "Please enter your first name", Snackbar.LENGTH_LONG).show();
            return false;
        }

        if (TextUtils.isEmpty(etEmail.getText().toString())) {
            Snackbar.make(view, "Please enter your email", Snackbar.LENGTH_LONG).show();
            return false;
        }

        if (!checkEmail(etEmail.getText().toString())) {
            Snackbar.make(view, "Please enter a valid email", Snackbar.LENGTH_LONG).show();
            return false;
        }

        if (TextUtils.isEmpty(etPassword.getText().toString())) {
            Snackbar.make(view, "Please enter your password", Snackbar.LENGTH_LONG).show();
            return false;
        }

        if (TextUtils.isEmpty(etConfirmPassword.getText().toString())) {
            Snackbar.make(view, "Please enter your confirm password", Snackbar.LENGTH_LONG).show();
            return false;
        }

        if (!passwordMatch(etPassword.getText().toString(), etConfirmPassword.getText().toString())) {
            Snackbar.make(view, "Your confirm password must be same as your password", Snackbar.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private boolean passwordMatch(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    private boolean checkEmail (String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}