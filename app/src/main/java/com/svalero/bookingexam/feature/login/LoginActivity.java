package com.svalero.bookingexam.feature.login;

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

import com.google.android.material.textfield.TextInputLayout;
import com.svalero.bookingexam.R;
import com.svalero.bookingexam.data.User;
import com.svalero.bookingexam.feature.list_hotels.view.ListHotelsActivity;
import com.svalero.bookingexam.feature.register.RegisterActivity;
import com.svalero.bookingexam.feature.reservation.ReservationActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.View{
    private EditText etEmail;
    private EditText etPassword;
    private TextInputLayout tilEmail;
    private TextInputLayout tilPassword;
    private Button btLogin;
    private Button btRegistro;
    private String idRoom;
    private String nombreHotel;
    private String nombreLocalidad;
    private String idRoomR;
    private String nombreHotelR;
    private String nombreLocalidadR;
    private String numPers;
    private String fechaIn;
    private String fechaOut;
    private String numPersR;
    private String fechaInR;
    private String fechaOutR;
    private String precio;
    private String precioR;
    private String option;

    private ProgressBar loading;
    private LinearLayout initSesion;
    private LinearLayout registrate;

    private static String TAG = LoginActivity.class.getSimpleName();

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initComponents();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            option = bundle.getString("option");
            System.out.println(option);

            if(option.equals("fromRoomAdapter")) {
                idRoom = bundle.getString("room_id");
                nombreHotel= bundle.getString("nombre_hotel");
                nombreLocalidad= bundle.getString("nombre_localidad");
                numPers = bundle.getString("num_person");
                fechaIn = bundle.getString("fecha_in");
                fechaOut = bundle.getString("fecha_out");
                precio = bundle.getString("precio");
            }
            else if(option.equals("register")) {
                idRoomR = bundle.getString("datoRoom");
                nombreHotelR = bundle.getString("datoHotel");
                nombreLocalidadR = bundle.getString("datoLocalidad");
                numPersR = bundle.getString("persons");
                fechaInR = bundle.getString("dateIn");
                fechaOutR = bundle.getString("dateOut");
                precioR = bundle.getString("precio_reg");
            }
        }

        loginPresenter = new LoginPresenter(this);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading.setVisibility(View.VISIBLE);
                initSesion.setVisibility(View.GONE);
                registrate.setVisibility(View.GONE);

                String userEmail = etEmail.getText().toString();
                String userPassword = etPassword.getText().toString();

                User user = new User();
                user.setEmail(userEmail);
                user.setPassword(userPassword);

                if(userEmail.isEmpty() || userPassword.isEmpty()) {
                    loading.setVisibility(View.GONE);
                    initSesion.setVisibility(View.VISIBLE);
                    registrate.setVisibility(View.VISIBLE);
                    String infoMessage = "Debes rellenar los campos email y password";
                    info(infoMessage);
                }
                else {
                    loginPresenter.getUser(user);
                }
            }
        });

        btRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(), RegisterActivity.class);
                intent.putExtra("datoLocalidad", nombreLocalidad);
                intent.putExtra("datoHotel", nombreHotel);
                intent.putExtra("datoRoom", idRoom);
                intent.putExtra("num_pers", numPers);
                intent.putExtra("fecha_in", fechaIn);
                intent.putExtra("fecha_out", fechaOut);
                intent.putExtra("precioReg", precio);
                startActivity(intent);
            }
        });

        etEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tilEmail.setError(null);
            }
        });
    }

    private void initComponents() {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btLogin = findViewById(R.id.btLogin);
        btRegistro = findViewById(R.id.btRegistrate);
        tilEmail = findViewById(R.id.tilEmail);
        tilPassword = findViewById(R.id.tilPassword);
        loading = findViewById(R.id.pbLoadingLogin);
        initSesion = findViewById(R.id.llIniciaSesion);
        registrate = findViewById(R.id.llRegistrate);
    }

    public void info(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void successLogin(User user) {
        Log.d(TAG, "successLogin: " + user.getName() + " " + user.getSureName());
        initSesion.setVisibility(View.VISIBLE);
        registrate.setVisibility(View.VISIBLE);
        loading.setVisibility(View.GONE);

        if (option.equals("fromRoomAdapter")) {
            System.out.println(user.getEmail());
            System.out.println(user.getName());
            System.out.println(user.getPassword());
            Intent intent = new Intent(getBaseContext(), ReservationActivity.class);
            intent.putExtra("id_user", String.valueOf(user.getId()));
            intent.putExtra("user_name", String.valueOf(user.getName()));
            intent.putExtra("room_id", idRoom);
            intent.putExtra("nombre_hotel", nombreHotel);
            intent.putExtra("nombre_localidad", nombreLocalidad);
            intent.putExtra("num_person", numPers);
            intent.putExtra("fecha_in", fechaIn);
            intent.putExtra("fecha_out", fechaOut);
            intent.putExtra("precio", precio);
            intent.putExtra("option", option);
            startActivity(intent);

        } else if(option.equals("register")) {
            Intent intentR = new Intent(getBaseContext(), ReservationActivity.class);
            intentR.putExtra("id_user_r", String.valueOf(user.getId()));
            intentR.putExtra("user_name_r", String.valueOf(user.getName()));
            intentR.putExtra("room_id_r", idRoomR);
            intentR.putExtra("nombre_hotel_r", nombreHotelR);
            intentR.putExtra("nombre_localidad_r", nombreLocalidadR);
            intentR.putExtra("persons_r", numPersR);
            intentR.putExtra("date_in_r", fechaInR);
            intentR.putExtra("date_out_r", fechaOutR);
            intentR.putExtra("precio_r", precioR);
            intentR.putExtra("option", option);
            startActivity(intentR);
        }
    }

    @Override
    public void failureLogin(String message) {
        initSesion.setVisibility(View.VISIBLE);
        registrate.setVisibility(View.VISIBLE);
        loading.setVisibility(View.GONE);
        tilEmail.setError(message);
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}