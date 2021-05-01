package com.svalero.bookingexam.feature.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.svalero.bookingexam.R;
import com.svalero.bookingexam.data.User;
import com.svalero.bookingexam.feature.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View {
    private EditText etNombre;
    private EditText etApellidos;
    private EditText etEmail;
    private EditText etPassword;
    private Button btRegistro;
    private String idRoom;
    private String nombreHotel;
    private String nombreLocalidad;
    private String numPers;
    private String fechaIn;
    private String fechaOut;
    private String precio;

    RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initComponents();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            idRoom = bundle.getString("datoRoom");
            nombreHotel= bundle.getString("datoHotel");
            nombreLocalidad= bundle.getString("datoLocalidad");
            numPers = bundle.getString("num_pers");
            fechaIn = bundle.getString("fecha_in");
            fechaOut = bundle.getString("fecha_out");
            precio = bundle.getString("precioReg");
        }
        System.out.println(idRoom + ", " + nombreHotel + ", " + nombreLocalidad);

        registerPresenter = new RegisterPresenter(this);

        btRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = etNombre.getText().toString();
                String userSurename = etApellidos.getText().toString();
                String userEmail = etEmail.getText().toString();
                String userPassword = etPassword.getText().toString();

                User user = new User();
                user.setName(userName);
                user.setSureName(userSurename);
                user.setEmail(userEmail);
                user.setPassword(userPassword);

                // Comprobar que no deja campos vacíos
                if(userName.isEmpty() || userSurename.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty()) {
                    String infoMessage = "Debes rellenar todos los campos";
                    info(infoMessage);
                }
                else {
                    registerPresenter.makeRegister(user);
                }

            }
        });
    }

    private void initComponents() {
        etNombre = findViewById(R.id.etNombreReg);
        etApellidos = findViewById(R.id.etApellidosReg);
        etEmail = findViewById(R.id.etEmailReg);
        etPassword = findViewById(R.id.etPasswordReg);
        btRegistro = findViewById(R.id.btRegistro);
    }

    public void info(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void success(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
        intent.putExtra("datoLocalidad", nombreLocalidad);
        intent.putExtra("datoHotel", nombreHotel);
        intent.putExtra("datoRoom", idRoom);
        intent.putExtra("persons", numPers);
        intent.putExtra("dateIn", fechaIn);
        intent.putExtra("dateOut", fechaOut);
        intent.putExtra("precio_reg", precio);
        intent.putExtra("option", "register");
        startActivity(intent);
    }

    @Override
    public void failure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}