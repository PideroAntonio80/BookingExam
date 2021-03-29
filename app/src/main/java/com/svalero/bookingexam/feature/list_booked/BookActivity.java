package com.svalero.bookingexam.feature.list_booked;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.bookingexam.R;
import com.svalero.bookingexam.data.models.Hotel;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity implements BookContract.View {
    private RecyclerView recycler;
    private BookPresenter bookPresenter;
    private RecyclerView.LayoutManager lManager;
    private DividerItemDecoration divider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        bookPresenter = new BookPresenter(this);

        bookPresenter.getBookedHotels();

    }

    @Override
    public void successBookedHotels(ArrayList<Hotel> hotels) {
        recycler = (RecyclerView) findViewById(R.id.rvListBook);
        recycler.setHasFixedSize(true);

        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        ListAdapterBook listAdapterBook = new ListAdapterBook(hotels);
        listAdapterBook.notifyDataSetChanged();
        divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(getResources().getDrawable(R.drawable.recyclerview_divider));
        recycler.addItemDecoration(divider);
        recycler.setAdapter(listAdapterBook);
    }

    @Override
    public void error(String message) {
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }
}