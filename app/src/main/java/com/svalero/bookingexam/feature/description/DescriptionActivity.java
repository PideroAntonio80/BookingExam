package com.svalero.bookingexam.feature.description;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;
import com.svalero.bookingexam.R;
import com.svalero.bookingexam.data.Hotel;
import com.svalero.bookingexam.feature.custom_search.SearchActivity;
import com.svalero.bookingexam.feature.room.RoomActivity;

public class DescriptionActivity extends AppCompatActivity {

    private String mTitle;
    private Toolbar mToolbar;

    private ImageView ivFotoDescripcion;
    private TextView tvNombreDescripcion, tvLocalizacion, tvReservado, tvDescripcion, tvPuntos, tvPrecio;
    private RatingBar estrellasCategoria;
    private float estrellasDescripcion;
    private Button bSeleccionar;

    private Hotel hotel;

    private static String TAG = DescriptionActivity.class.getSimpleName();

    private String numPers;
    private String fechaIn;
    private String fechaOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initComponents();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            hotel = (Hotel) bundle.getSerializable("my_hotel");
            numPers = bundle.getString("num_person");
            fechaIn = bundle.getString("fecha_in");
            fechaOut = bundle.getString("fecha_out");
        }

        showHotel();

        Log.d(TAG, "onCreate: Descripción Hotel");

        bSeleccionar.setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), RoomActivity.class);
            intent.putExtra("my_hotel", hotel);
            intent.putExtra("num_pers", numPers);
            intent.putExtra("date_in", fechaIn);
            intent.putExtra("date_out", fechaOut);
            startActivity(intent);
        });
    }

    public void initComponents() {
        ivFotoDescripcion = findViewById(R.id.ivFotoDescripcion);
        tvNombreDescripcion = findViewById(R.id.tvNombreDescripcion);
        tvLocalizacion = findViewById(R.id.tvLocation);
        tvReservado = findViewById(R.id.tvReservado);
        tvDescripcion = findViewById(R.id.tvDescripcion);
        estrellasCategoria = findViewById(R.id.rbEstrellasDescripcion);
        tvPuntos = findViewById(R.id.tvPuntos);
        tvPrecio = findViewById(R.id.tvPrecioMedio);
        bSeleccionar = findViewById(R.id.bSeleccionar);
        mToolbar = findViewById(R.id.toolbar);
    }

    public void showHotel() {
        String urlImage = "http://192.168.1.142:8090/BookingWeb/images/" + hotel.getFoto() + ".png";
        Picasso.get().load(urlImage).into(ivFotoDescripcion);
        AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
        animation.setDuration(1000);
        animation.setStartOffset(1000);
        animation.setFillAfter(true);
        ivFotoDescripcion.startAnimation(animation);
        tvNombreDescripcion.setText(hotel.getNombre());
        tvLocalizacion.setText(hotel.getNombre_localidad());
        tvReservado.setText(String.valueOf(hotel.getNumeroReservas()));
        tvDescripcion.setText(hotel.getDescripcion());
        estrellasDescripcion = hotel.getEstrellas();
        estrellasCategoria.setRating(estrellasDescripcion);
        tvPuntos.setText(String.valueOf(hotel.getPuntuacion()));
        tvPrecio.setText(String.valueOf(hotel.getPrecio_medio()));
        mTitle = hotel.getNombre();
        mToolbar.setTitle(mTitle);
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}