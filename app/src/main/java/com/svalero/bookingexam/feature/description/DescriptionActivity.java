package com.svalero.bookingexam.feature.description;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.svalero.bookingexam.R;
import com.svalero.bookingexam.data.Hotel;
import com.svalero.bookingexam.feature.CentralActivity;
import com.svalero.bookingexam.feature.room.RoomActivity;

public class DescriptionActivity extends AppCompatActivity implements DescriptionContract.View {

    private ImageView ivFotoDescripcion;
    private TextView tvNombreDescripcion, tvLocalizacion, tvReservado, tvDescripcion, tvCategoria, tvPuntos, tvPrecio;
    private Button bSeleccionar;

    private DescriptionPresenter descriptionPresenter;
    private Hotel hotel;
    private Hotel hotelDescrito;
    private String nombreHotel;
    private String nombreLocalidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        initComponents();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            nombreHotel = bundle.getString("nombre_hotel");
            nombreLocalidad = bundle.getString("nombre_localidad");
        }

        hotel = new Hotel();
        hotel.setNombre(nombreHotel);

        descriptionPresenter = new DescriptionPresenter(this);
        descriptionPresenter.getOneHotel(hotel);

        bSeleccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), RoomActivity.class);
                intent.putExtra("nombre_hotel", nombreHotel);
                startActivity(intent);
            }
        });
    }

    public void initComponents() {
        ivFotoDescripcion = findViewById(R.id.ivFotoDescripcion);
        tvNombreDescripcion = findViewById(R.id.tvNombreDescripcion);
        tvLocalizacion = findViewById(R.id.tvLocation);
        tvReservado = findViewById(R.id.tvReservado);
        tvDescripcion = findViewById(R.id.tvDescripcion);
        tvCategoria = findViewById(R.id.tvEstrellasDescript);
        tvPuntos = findViewById(R.id.tvPuntos);
        tvPrecio = findViewById(R.id.tvPrecioMedio);
        bSeleccionar = findViewById(R.id.bSeleccionar);
    }

    @Override
    public void showHotel(Hotel hotel) {
        this.hotelDescrito = hotel;
        String urlImage = "http://192.168.1.142:8090/BookingWeb/images/" + hotel.getFoto() + ".png";
        Picasso.get().load(urlImage).into(ivFotoDescripcion);
        tvNombreDescripcion.setText(hotel.getNombre());
        tvLocalizacion.setText(hotel.getNombre_localidad());
        tvReservado.setText(String.valueOf(hotel.getNumeroReservas()));
        tvDescripcion.setText(hotel.getDescripcion());
        tvCategoria.setText(String.valueOf(hotel.getEstrellas()));
        tvPuntos.setText(String.valueOf(hotel.getPuntuacion()));
        tvPrecio.setText(String.valueOf(hotel.getPrecio_medio()));

    }
}