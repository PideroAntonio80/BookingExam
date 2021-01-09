package com.svalero.bookingexam.feature.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.svalero.bookingexam.R;
import com.svalero.bookingexam.data.User;
import com.svalero.bookingexam.feature.search.SearchActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.View{
    private EditText etEmail;
    private EditText etPassword;
    private Button btLogin;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initComponents();

        loginPresenter = new LoginPresenter(this);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = etEmail.getText().toString();
                String userPassword = etPassword.getText().toString();
                User user = new User();
                // Lo siguiente es para comprobar que no deja campos vacíos
                user.setEmail(userEmail);
                user.setPassword(userPassword);
                loginPresenter.getUser(user);
            }
        });
    }

    private void initComponents() {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btLogin = findViewById(R.id.btLogin);
    }

    @Override
    public void successLogin(User user) {
        Toast.makeText(this, "Bienvenido=" + user.getName(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getBaseContext(), SearchActivity.class);
        startActivity(intent);
    }

    @Override
    public void failureLogin(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}