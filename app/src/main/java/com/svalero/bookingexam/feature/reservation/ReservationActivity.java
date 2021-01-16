package com.svalero.bookingexam.feature.reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.svalero.bookingexam.R;
import com.svalero.bookingexam.data.BookingRoom;
import com.svalero.bookingexam.data.Hotel;
import com.svalero.bookingexam.data.Room;
import com.svalero.bookingexam.feature.CentralActivity;
import com.svalero.bookingexam.feature.FinalSplashActivity;
import com.svalero.bookingexam.feature.custom_search.SearchActivity;
import com.svalero.bookingexam.utils.customui.DatePickerFragment;

import java.util.ArrayList;

public class ReservationActivity extends AppCompatActivity implements ReservationContract.View {
    private TextView nombreCliente, nombreLocalidad, nombreHotel, numeroHabitacion;
    private EditText numeroNoches;
    private Button fechaEntrada, fechaSalida, finalizar;
    private ReservationPresenter reservationPresenter;
    private int roomId, userId;
    private String userName;
    private String miHotel;
    private String miLocalidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        initComponents();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            roomId = Integer.parseInt(bundle.getString("room_id"));
            userId = Integer.parseInt(bundle.getString("id_user"));
            userName = bundle.getString("user_name");
            miHotel = bundle.getString("nombre_hotel");
            miLocalidad = bundle.getString("nombre_localidad");
        }

        nombreCliente.setText(userName);
        numeroHabitacion.setText(String.valueOf(roomId));
        nombreHotel.setText(miHotel);
        nombreLocalidad.setText(miLocalidad);

        initListeners();

        reservationPresenter = new ReservationPresenter(this);

    }


    public void initComponents() {
        nombreCliente = findViewById(R.id.tvCliente);
        nombreLocalidad = findViewById(R.id.tvLocalidadReserva);
        nombreHotel = findViewById(R.id.tvHotelReserva);
        numeroHabitacion = findViewById(R.id.tvNumeroRoomReserva);
        numeroNoches = findViewById(R.id.etNoches);
        fechaEntrada = findViewById(R.id.bFechaEntrada);
        fechaSalida = findViewById(R.id.bFechaSalida);
        finalizar = findViewById(R.id.bFinalizar);

    }

    public void initListeners() {
        fechaEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment calendarioIn = DatePickerFragment.newInstance(new DatePickerFragment.OnDateChangeListener() {
                    @Override
                    public void onDateChange(int year, int month, int dayOfMonth) {
                        String dateFormatted = dayOfMonth + "/" + (month + 1) + "/" + year;
                        fechaEntrada.setText(dateFormatted);
                    }
                });
                calendarioIn.show(getSupportFragmentManager(), "fechaEntrada");
            }
        });

        fechaSalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment calendarioOut = DatePickerFragment.newInstance(new DatePickerFragment.OnDateChangeListener() {
                    @Override
                    public void onDateChange(int year, int month, int dayOfMonth) {
                        String dateFormatted = dayOfMonth + "/" + (month + 1) + "/" + year;
                        fechaSalida.setText(dateFormatted);
                    }
                });
                calendarioOut.show(getSupportFragmentManager(), "fechaSalida");
            }
        });

        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookingRoom bookingRoom = new BookingRoom();
                bookingRoom.setIdRoom(roomId);
                bookingRoom.setIdUser(userId);
                reservationPresenter.makeReservation(bookingRoom);
            }
        });

    }

    @Override
    public void success(String message) {
        Intent intent = new Intent(getBaseContext(), FinalSplashActivity.class);
        intent.putExtra("exito", message);
        startActivity(intent);
    }

    @Override
    public void failure(String message) {
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }
}