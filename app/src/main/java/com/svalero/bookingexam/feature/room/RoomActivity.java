package com.svalero.bookingexam.feature.room;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
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
    private DividerItemDecoration divider;
    private String nombreHotel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            nombreHotel = bundle.getString("nombre_hotel");
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

        RoomAdapter roomAdapter = new RoomAdapter(rooms);
        roomAdapter.notifyDataSetChanged();
        divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(getResources().getDrawable(R.drawable.recyclerview_divider));
        recycler.addItemDecoration(divider);
        recycler.setAdapter(roomAdapter);
    }

    @Override
    public void error(String message) {
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }
}

