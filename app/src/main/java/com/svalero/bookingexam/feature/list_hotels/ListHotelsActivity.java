package com.svalero.bookingexam.feature.list_hotels;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.svalero.bookingexam.R;
import com.svalero.bookingexam.data.Hotel;

import java.util.ArrayList;

public class ListHotelsActivity extends AppCompatActivity implements ListHotelsContract.View{

    private RecyclerView recycler;
    private ListHotelsPresenter listHotelsPresenter;
    private RecyclerView.LayoutManager lManager;
    private DividerItemDecoration divider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hotels);

        listHotelsPresenter = new ListHotelsPresenter(this);
        listHotelsPresenter.getHotels();
    }

    @Override
    public void success(ArrayList<Hotel> hotels) {
        recycler = (RecyclerView) findViewById(R.id.rvList);
        recycler.setHasFixedSize(true);

        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        ListAdapter listAdapter = new ListAdapter(hotels);
        listAdapter.notifyDataSetChanged();
        divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(getResources().getDrawable(R.drawable.recyclerview_divider));
        recycler.addItemDecoration(divider);
        recycler.setAdapter(listAdapter);

    }

    @Override
    public void error(String message) {
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }
}