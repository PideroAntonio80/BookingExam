package com.svalero.bookingexam.feature.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.svalero.bookingexam.R;
import com.svalero.bookingexam.data.User;
import com.svalero.bookingexam.feature.list_hotels.view.ListHotelsActivity;
import com.svalero.bookingexam.feature.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View {

    private TextInputLayout tilPassRegistro;
    private TextInputEditText etNombre;
    private TextInputEditText etApellidos;
    private TextInputEditText etEmail;
    private TextInputEditText etPassword;
    private Button btRegistro;
    private String idRoom;
    private String nombreHotel;
    private String nombreLocalidad;
    private String numPers;
    private String fechaIn;
    private String fechaOut;
    private String precio;

    private ProgressBar loading;
    private LinearLayout llRegistro;

    private static String TAG = RegisterActivity.class.getSimpleName();

    RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

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
                loading.setVisibility(View.VISIBLE);
                llRegistro.setVisibility(View.GONE);

                String userName = String.valueOf(etNombre.getText());
                String userSurename = String.valueOf(etApellidos.getText());
                String userEmail = String.valueOf(etEmail.getText());
                String userPassword = String.valueOf(etPassword.getText());

                User user = new User();
                user.setName(userName);
                user.setSureName(userSurename);
                user.setEmail(userEmail);
                user.setPassword(userPassword);

                if(userName.isEmpty() || userSurename.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty()) {
                    loading.setVisibility(View.GONE);
                    llRegistro.setVisibility(View.VISIBLE);

                    String infoMessage = "Debes rellenar todos los campos";
                    tilPassRegistro.setError(infoMessage);
                }
                else {
                    registerPresenter.makeRegister(user);
                }
            }
        });

        etNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tilPassRegistro.setError(null);
            }
        });

        etApellidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tilPassRegistro.setError(null);
            }
        });

        etEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tilPassRegistro.setError(null);
            }
        });

        etPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tilPassRegistro.setError(null);
            }
        });
    }

    private void initComponents() {
        etNombre = findViewById(R.id.etNombreReg);
        etApellidos = findViewById(R.id.etApellidosReg);
        etEmail = findViewById(R.id.etEmailReg);
        etPassword = findViewById(R.id.etPasswordReg);
        btRegistro = findViewById(R.id.btRegistro);
        tilPassRegistro = findViewById(R.id.tilRegisterPassword);
        loading = findViewById(R.id.pbLoadingRegistro);
        llRegistro = findViewById(R.id.llRegistro);
    }

    public void info(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void success(String message) {
        Log.d(TAG, "success: Registro realizado");
        loading.setVisibility(View.GONE);
        llRegistro.setVisibility(View.VISIBLE);

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
        Log.d(TAG, "failure: fallo en el registro");
        loading.setVisibility(View.GONE);
        llRegistro.setVisibility(View.VISIBLE);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}