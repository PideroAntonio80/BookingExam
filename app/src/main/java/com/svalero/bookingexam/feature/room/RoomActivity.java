package com.svalero.bookingexam.feature.room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.bookingexam.R;
import com.svalero.bookingexam.data.Hotel;
import com.svalero.bookingexam.data.Room;
import com.svalero.bookingexam.feature.list_hotels.view.ListHotelsActivity;

import java.util.ArrayList;

public class RoomActivity extends AppCompatActivity implements RoomContract.View {

    private RoomPresenter roomPresenter;
    private RecyclerView.LayoutManager lManager;
    private RecyclerView recycler;
    private ProgressBar loading;
    private LinearLayout layoutError;
    private Button retry;

    private Hotel hotel;
    private String locationName;

    private String numPers;
    private String fechaIn;
    private String fechaOut;

    private static String TAG = RoomActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initView();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            hotel = (Hotel) bundle.getSerializable("my_hotel");
            numPers = bundle.getString("num_pers");
            fechaIn = bundle.getString("date_in");
            fechaOut = bundle.getString("date_out");
        }

        locationName = hotel.getNombre_localidad();

        roomPresenter = new RoomPresenter(this);
        roomPresenter.getRooms(hotel.getNombre());

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading.setVisibility(View.VISIBLE);
                recycler.setVisibility(View.GONE);
                layoutError.setVisibility(View.GONE);

                roomPresenter.getRooms(hotel.getNombre());
            }
        });
    }

    public void initView() {
        recycler = (RecyclerView) findViewById(R.id.rvRoom);
        loading = findViewById(R.id.pbLoadingRoom);
        layoutError = findViewById(R.id.llLayoutErrorRoom);
        retry = findViewById(R.id.bRetryRoom);
    }

    @Override
    public void success(ArrayList<Room> rooms) {
        Log.d(TAG, "success: Ej. Habitación número: " + rooms.get(0).getIdRoom());
        recycler.setVisibility(View.VISIBLE);
        loading.setVisibility(View.GONE);
        layoutError.setVisibility(View.GONE);

        recycler.setHasFixedSize(true);
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        RoomAdapter roomAdapter = new RoomAdapter(rooms, locationName, numPers, fechaIn, fechaOut);
        roomAdapter.notifyDataSetChanged();
        recycler.setAdapter(roomAdapter);
    }

    @Override
    public void error(String message) {
        layoutError.setVisibility(View.VISIBLE);
        recycler.setVisibility(View.GONE);
        loading.setVisibility(View.GONE);
        //Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

