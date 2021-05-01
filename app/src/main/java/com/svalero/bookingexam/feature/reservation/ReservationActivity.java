package com.svalero.bookingexam.feature.reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
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

import java.sql.Date;

public class ReservationActivity extends AppCompatActivity implements ReservationContract.View {
    private TextView nombreCliente, nombreLocalidad, nombreHotel, numeroHabitacion, numeroNoches, precioTotal, fraseFinal;
    private EditText numPersons;
    private Button fechaEntrada, fechaSalida, finalizar;
    private ReservationPresenter reservationPresenter;
    private String roomId, userId, roomIdR, userIdR;
    private String miHotel, miHotelR, miLocalidad, miLocalidadR, userName, userNameR;
    private String numPers, fechaIn, fechaOut, nights, precio, numPersR, fechaInR, fechaOutR, nightsR, precioR;
    private String option;
    private double factura, facturaR;

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
                numPers = bundle.getString("num_person");
                fechaIn = bundle.getString("fecha_in");
                fechaOut = bundle.getString("fecha_out");
                precio = bundle.getString("precio");
            }
            else if (option.equals("register")) {
                roomIdR = bundle.getString("room_id_r");
                userIdR = bundle.getString("id_user_r");
                miHotelR = bundle.getString("nombre_hotel_r");
                miLocalidadR = bundle.getString("nombre_localidad_r");
                userNameR = bundle.getString("user_name_r");
                numPersR = bundle.getString("persons_r");
                fechaInR = bundle.getString("date_in_r");
                fechaOutR = bundle.getString("date_out_r");
                precioR = bundle.getString("precio_r");
            }
        }

        if (option.equals("fromRoomAdapter")) {
            nombreCliente.setText(userName);
            numeroHabitacion.setText(String.valueOf(roomId));
            nombreHotel.setText(miHotel);
            nombreLocalidad.setText(miLocalidad);

            setFinalMessage();

            if (fechaIn != null) {
                fechaEntrada.setText(fechaIn);
            }
            if (fechaOut != null) {
                fechaSalida.setText(fechaOut);
            }
            if(fechaIn != null && fechaOut != null) {
                nights = String.valueOf(numeroDiasEntreDosFechas(Date.valueOf(fechaIn), Date.valueOf(fechaOut)));
                numeroNoches.setText(nights);
            }
            if(numPers != null) {
                numPersons.setText(numPers);
            }
            if(precio != null && numPers != null && nights != null) {
                factura = prize(Double.parseDouble(precio), Integer.parseInt(numPers) , Integer.parseInt(nights));
                precioTotal.setText(String.valueOf(factura));
            }
        }
        else if(option.equals("register")) {
            nombreCliente.setText(userNameR);
            numeroHabitacion.setText(String.valueOf(roomIdR));
            nombreHotel.setText(miHotelR);
            nombreLocalidad.setText(miLocalidadR);

            setFinalMessage();

            if (fechaIn != null) {
                fraseFinal.setText("Pulse finalizar para reservar");
            }
            if (fechaInR != null) {
                fechaEntrada.setText(fechaInR);
            }
            if (fechaOutR != null) {
                fechaSalida.setText(fechaOutR);
            }
            if(fechaInR != null && fechaOutR != null) {
                nightsR = String.valueOf(numeroDiasEntreDosFechas(Date.valueOf(fechaInR), Date.valueOf(fechaOutR)));
                numeroNoches.setText(nightsR);
            }
            if(numPersR != null) {
                numPersons.setText(numPersR);
            } else {
                fechaEntrada.setActivated(false);
                fechaSalida.setActivated(false);
            }
            if(precioR != null && numPersR != null && nightsR != null) {
                facturaR = prize(Double.parseDouble(precioR), Integer.parseInt(numPersR) , Integer.parseInt(nightsR));
                precioTotal.setText(String.valueOf(facturaR));
            }
        }

        initListeners();

        reservationPresenter = new ReservationPresenter(this);
    }

    public static int numeroDiasEntreDosFechas(Date fecha1, Date fecha2){
        long diffDateMs = fecha2.getTime() - fecha1.getTime();
        long diffDays = ((diffDateMs / 1000) / 3600) / 24;
        return (int)diffDays;

    }

    public double prize(double roomPrize, int persons, int nights) {
        double totalPrize = (double) roomPrize * persons * nights;
        return totalPrize;
    }

    public void info(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void initComponents() {
        fraseFinal = findViewById(R.id.tvFraseFinReserva);
        nombreCliente = findViewById(R.id.tvCliente);
        nombreLocalidad = findViewById(R.id.tvLocalidadReserva);
        nombreHotel = findViewById(R.id.tvHotelReserva);
        numeroHabitacion = findViewById(R.id.tvNumeroRoomReserva);
        numeroNoches = findViewById(R.id.tvNochesReserva);
        numPersons = findViewById(R.id.etNumPersonsReserva);
        fechaEntrada = findViewById(R.id.bFechaEntrada);
        fechaSalida = findViewById(R.id.bFechaSalida);
        precioTotal = findViewById(R.id.tvPrecioTotal);
        finalizar = findViewById(R.id.bFinalizar);
    }

    public void initListeners() {
        fechaEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment calendarioIn = DatePickerFragment.newInstance(new DatePickerFragment.OnDateChangeListener() {
                    @Override
                    public void onDateChange(int year, int month, int dayOfMonth) {
                        String dateFormatted;

                        if(dayOfMonth < 10 && month < 9) {
                            dateFormatted = year + "-0" + (month + 1) + "-0" + dayOfMonth;
                        } else if (dayOfMonth < 10 && month >= 9) {
                            dateFormatted = year + "-" + (month + 1) + "-0" + dayOfMonth;
                        } else if (dayOfMonth >= 10 && month < 9) {
                            dateFormatted = year + "-0" + (month + 1) + "-" + dayOfMonth;
                        } else {
                            dateFormatted = year + "-" + (month + 1) + "-" + dayOfMonth;
                        }

                        fechaEntrada.setText(dateFormatted);
                        fechaIn = String.valueOf(fechaEntrada.getText());
                        fechaInR = String.valueOf(fechaEntrada.getText());
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
                        String dateFormatted;

                        if(dayOfMonth < 10 && month < 9) {
                            dateFormatted = year + "-0" + (month + 1) + "-0" + dayOfMonth;
                        } else if (dayOfMonth < 10 && month >= 9) {
                            dateFormatted = year + "-" + (month + 1) + "-0" + dayOfMonth;
                        } else if (dayOfMonth >= 10 && month < 9) {
                            dateFormatted = year + "-0" + (month + 1) + "-" + dayOfMonth;
                        } else {
                            dateFormatted = year + "-" + (month + 1) + "-" + dayOfMonth;
                        }

                        fechaSalida.setText(dateFormatted);
                        fechaOut = String.valueOf(fechaSalida.getText());
                        fechaOutR = String.valueOf(fechaSalida.getText());
                        nights = String.valueOf(numeroDiasEntreDosFechas(Date.valueOf((String) fechaEntrada.getText()), Date.valueOf((String) fechaSalida.getText())));
                        nightsR = String.valueOf(numeroDiasEntreDosFechas(Date.valueOf((String) fechaEntrada.getText()), Date.valueOf((String) fechaSalida.getText())));
                        numPers = String.valueOf(numPersons.getText());
                        numPersR = String.valueOf(numPersons.getText());

                        if(option.equals("fromRoomAdapter")) {
                            numeroNoches.setText(nights);
                            factura = prize(Double.parseDouble(precio), Integer.parseInt(numPers) , Integer.parseInt(nights));
                            precioTotal.setText(String.valueOf(factura));
                        }
                        else if(option.equals("register")) {
                            numeroNoches.setText(nightsR);
                            facturaR = prize(Double.parseDouble(precioR), Integer.parseInt(numPersR) , Integer.parseInt(nightsR));
                            precioTotal.setText(String.valueOf(facturaR));
                        }
                    }
                });
                calendarioOut.show(getSupportFragmentManager(), "fechaSalida");
            }
        });

        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(option.equals("fromRoomAdapter")) {
                    if(numPers == null) {
                        info("Debes indicar numero de personas");
                        return;
                    }
                    if(fechaIn == null || fechaOut == null) {
                        info("Debes indicar las fechas");
                        return;
                    }
                    numPers = String.valueOf(numPersons.getText());
                    BookingRoom bookingRoom = new BookingRoom();
                    bookingRoom.setIdRoom(Integer.parseInt(roomId));
                    bookingRoom.setIdUser(Integer.parseInt(userId));
                    bookingRoom.setNoches(Integer.parseInt(nights));
                    bookingRoom.setDateIn(Date.valueOf(fechaIn));
                    bookingRoom.setDateOut(Date.valueOf(fechaOut));
                    bookingRoom.setNumPerson(Integer.parseInt(numPers));
                    bookingRoom.setPrecio(factura);
                    reservationPresenter.makeReservation(bookingRoom);
                }
                else if(option.equals("register")) {
                    if(numPersR == null) {
                        info("Debes indicar numero de personas");
                        return;
                    }
                    if(fechaInR == null || fechaOutR == null) {
                        info("Debes indicar las fechas");
                        return;
                    }
                    numPersR = String.valueOf(numPersons.getText());
                    BookingRoom bookingRoom = new BookingRoom();
                    bookingRoom.setIdRoom(Integer.parseInt(roomIdR));
                    bookingRoom.setIdUser(Integer.parseInt(userIdR));
                    bookingRoom.setNoches(Integer.parseInt(nightsR));
                    bookingRoom.setDateIn(Date.valueOf(fechaInR));
                    bookingRoom.setDateOut(Date.valueOf(fechaOutR));
                    bookingRoom.setNumPerson(Integer.parseInt(numPersR));
                    bookingRoom.setPrecio(facturaR);
                    reservationPresenter.makeReservation(bookingRoom);
                }

            }
        });
    }

    public void setFinalMessage() {
        Resources res = getResources();
        String completedData = res.getString(R.string.final_message_completed_data);
        String incompletedData = res.getString(R.string.final_message_incompleted_data);
        String option = "";
        option = (fechaIn == null) ? incompletedData : completedData;
        fraseFinal.setText(option);
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