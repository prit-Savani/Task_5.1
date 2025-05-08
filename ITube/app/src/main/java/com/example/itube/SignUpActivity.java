package com.example.itube;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.itube.db.AppDatabase;
import com.example.itube.db.User;
import com.example.itube.db.UserDao;

public class SignUpActivity extends AppCompatActivity {
    EditText etFullName, etUsername, etPassword, etConfirmPassword;
    Button btnCreateAccount;
    AppDatabase db;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_ITube);
        setContentView(R.layout.activity_signup);

        etFullName = findViewById(R.id.etFullName);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);

        db = AppDatabase.getInstance(this);
        userDao = db.userDao();

        btnCreateAccount.setOnClickListener(v -> {
            String fullName = etFullName.getText().toString();
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();
            String confirmPassword = etConfirmPassword.getText().toString();

            if (fullName.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }
            new Thread(() -> {
                User user = new User();
                user.fullName = fullName;
                user.username = username;
                user.password = password;
                userDao.insert(user);
                runOnUiThread(() -> {
                    Toast.makeText(this, "Account created! Please login.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, LoginActivity.class));
                    finish();
                });
            }).start();
        });
    }
} 