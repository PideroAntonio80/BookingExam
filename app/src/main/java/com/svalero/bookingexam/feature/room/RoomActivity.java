package com.svalero.bookingexam.feature.room;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.bookingexam.R;
import com.svalero.bookingexam.data.Hotel;
import com.svalero.bookingexam.data.Room;

import java.util.ArrayList;

public class RoomActivity extends AppCompatActivity implements RoomContract.View {
    private RecyclerView recycler;
    private RoomPresenter roomPresenter;
    private RecyclerView.LayoutManager lManager;
    private String nombreHotel;
    private String numPers;
    private String fechaIn;
    private String fechaOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            nombreHotel = bundle.getString("nombre_hotel");
            numPers = bundle.getString("num_pers");
            fechaIn = bundle.getString("date_in");
            fechaOut = bundle.getString("date_out");
        }

        Hotel hotel = new Hotel();
        hotel.setNombre(nombreHotel);

        roomPresenter = new RoomPresenter(this);
        roomPresenter.getRooms(hotel);
    }

    @Override
    public void success(ArrayList<Room> rooms) {
        recycler = (RecyclerView) findViewById(R.id.rvRoom);
        recycler.setHasFixedSize(true);

        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        RoomAdapter roomAdapter = new RoomAdapter(rooms, numPers, fechaIn, fechaOut);
        roomAdapter.notifyDataSetChanged();
        recycler.setAdapter(roomAdapter);
    }

    @Override
    public void error(String message) {
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }
}

