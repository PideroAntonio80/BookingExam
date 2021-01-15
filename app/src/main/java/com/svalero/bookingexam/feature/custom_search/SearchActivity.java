package com.svalero.bookingexam.feature.custom_search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.svalero.bookingexam.R;
import com.svalero.bookingexam.data.Hotel;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements SearchContract.View {

    private RecyclerView recycler;
    private RecyclerView.LayoutManager lManager;
    private DividerItemDecoration divider;
    private SearchPresenter searchPresenter;
    private String localidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            localidad = bundle.getString("nombre_localidad");
        }

        Hotel hotel = new Hotel();
        hotel.setNombre_localidad(localidad);

        searchPresenter = new SearchPresenter(this);
        searchPresenter.searchHotels(hotel);
    }

    @Override
    public void success(ArrayList<Hotel> hotels) {
        recycler = (RecyclerView) findViewById(R.id.rvSearch);
        recycler.setHasFixedSize(true);

        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        SearchAdapter searchAdapter = new SearchAdapter(hotels);
        searchAdapter.notifyDataSetChanged();
        divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(getResources().getDrawable(R.drawable.recyclerview_divider));
        recycler.addItemDecoration(divider);
        recycler.setAdapter(searchAdapter);
    }

    @Override
    public void error(String message) {
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }
}