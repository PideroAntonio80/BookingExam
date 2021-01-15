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
import com.svalero.bookingexam.feature.CentralActivity;

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
                user.setEmail(userEmail);
                user.setPassword(userPassword);

                // Comprobar que no deja campos vacíos
                if(userEmail.isEmpty() || userPassword.isEmpty()) {
                    String infoMessage = "Debes rellenar los campos email y password";
                    info(infoMessage);
                }
                else {
                    loginPresenter.getUser(user);
                }

            }
        });
    }

    private void initComponents() {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btLogin = findViewById(R.id.btLogin);
    }

    public void info(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void successLogin(User user) {
        Toast.makeText(this, "Bienvenido " + user.getName(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getBaseContext(), CentralActivity.class);
        startActivity(intent);
    }

    @Override
    public void failureLogin(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}