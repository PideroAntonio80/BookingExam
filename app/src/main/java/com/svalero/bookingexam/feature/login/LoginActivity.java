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
import com.svalero.bookingexam.feature.register.RegisterActivity;
import com.svalero.bookingexam.feature.reservation.ReservationActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.View{
    private EditText etEmail;
    private EditText etPassword;
    private Button btLogin;
    private Button btRegistro;
    private String idRoom;
    private String nombreHotel;
    private String nombreLocalidad;
    private String idRoomR;
    private String nombreHotelR;
    private String nombreLocalidadR;
    private String option;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initComponents();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            option = bundle.getString("option");
            System.out.println(option);
            if(option.equals("fromRoomAdapter")) {
                idRoom = bundle.getString("room_id");
                nombreHotel= bundle.getString("nombre_hotel");
                nombreLocalidad= bundle.getString("nombre_localidad");
            }
            else if(option.equals("register")) {
                idRoomR = bundle.getString("datoRoom");
                nombreHotelR = bundle.getString("datoHotel");
                nombreLocalidadR = bundle.getString("datoLocalidad");
            }
        }

        System.out.println(idRoomR + ", " + nombreHotelR + ", " + nombreLocalidadR);

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

        btRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(), RegisterActivity.class);
                intent.putExtra("datoLocalidad", nombreLocalidad);
                intent.putExtra("datoHotel", nombreHotel);
                intent.putExtra("datoRoom", idRoom);
                startActivity(intent);
            }
        });
    }

    private void initComponents() {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btLogin = findViewById(R.id.btLogin);
        btRegistro = findViewById(R.id.btRegistrate);
    }

    public void info(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void successLogin(User user) {
        if (option.equals("fromRoomAdapter")) {
            Toast.makeText(this, "Ultimo paso " + user.getName(), Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getBaseContext(), ReservationActivity.class);
            intent.putExtra("id_user", String.valueOf(user.getId()));
            intent.putExtra("user_name", String.valueOf(user.getName()));
            intent.putExtra("room_id", idRoom);
            intent.putExtra("nombre_hotel", nombreHotel);
            intent.putExtra("nombre_localidad", nombreLocalidad);
            intent.putExtra("option", option);
            startActivity(intent);
        } else if(option.equals("register")) {
            Toast.makeText(this, "Ultimo paso " + user.getName(), Toast.LENGTH_LONG).show();
            Intent intentR = new Intent(getBaseContext(), ReservationActivity.class);
            intentR.putExtra("id_user_r", String.valueOf(user.getId()));
            intentR.putExtra("user_name_r", String.valueOf(user.getName()));
            intentR.putExtra("room_id_r", idRoomR);
            intentR.putExtra("nombre_hotel_r", nombreHotelR);
            intentR.putExtra("nombre_localidad_r", nombreLocalidadR);
            intentR.putExtra("option", option);
            startActivity(intentR);
        }
    }

    @Override
    public void failureLogin(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}