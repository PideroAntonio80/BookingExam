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
import com.svalero.bookingexam.feature.FinalSplashActivity;
import com.svalero.bookingexam.utils.customui.DatePickerFragment;

public class ReservationActivity extends AppCompatActivity implements ReservationContract.View {
    private TextView nombreCliente, nombreLocalidad, nombreHotel, numeroHabitacion;
    private EditText numeroNoches;
    private Button fechaEntrada, fechaSalida, finalizar;
    private ReservationPresenter reservationPresenter;
    private String roomId, userId, roomIdR, userIdR;
    private String miHotel, miHotelR, miLocalidad, miLocalidadR, userName, userNameR;
    private String option;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        initComponents();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            option = bundle.getString("option");
            if (option.equals("fromRoomAdapter")) {
                roomId = bundle.getString("room_id");
                userId = bundle.getString("id_user");
                miHotel = bundle.getString("nombre_hotel");
                miLocalidad = bundle.getString("nombre_localidad");
                userName = bundle.getString("user_name");
            }
            else if (option.equals("register")) {
                roomIdR = bundle.getString("room_id_r");
                userIdR = bundle.getString("id_user_r");
                miHotelR = bundle.getString("nombre_hotel_r");
                miLocalidadR = bundle.getString("nombre_localidad_r");
                userNameR = bundle.getString("user_name_r");
            }
        }

        if (option.equals("fromRoomAdapter")) {
            nombreCliente.setText(userName);
            numeroHabitacion.setText(String.valueOf(roomId));
            nombreHotel.setText(miHotel);
            nombreLocalidad.setText(miLocalidad);
        }
        else if(option.equals("register")) {
            nombreCliente.setText(userNameR);
            numeroHabitacion.setText(String.valueOf(roomIdR));
            nombreHotel.setText(miHotelR);
            nombreLocalidad.setText(miLocalidadR);
        }

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
                if(option.equals("fromRoomAdapter")) {
                    BookingRoom bookingRoom = new BookingRoom();
                    bookingRoom.setIdRoom(Integer.parseInt(roomId));
                    bookingRoom.setIdUser(Integer.parseInt(userId));
                    reservationPresenter.makeReservation(bookingRoom);
                }
                else if(option.equals("register")) {
                    BookingRoom bookingRoom = new BookingRoom();
                    bookingRoom.setIdRoom(Integer.parseInt(roomIdR));
                    bookingRoom.setIdUser(Integer.parseInt(userIdR));
                    reservationPresenter.makeReservation(bookingRoom);
                }

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