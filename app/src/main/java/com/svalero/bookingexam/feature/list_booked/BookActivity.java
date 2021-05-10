package com.svalero.bookingexam.feature.list_booked;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.bookingexam.R;
import com.svalero.bookingexam.data.Hotel;
import com.svalero.bookingexam.feature.list_hotels.view.ListHotelsActivity;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity implements BookContract.View {
    private RecyclerView recycler;
    private BookPresenter bookPresenter;
    private RecyclerView.LayoutManager lManager;

    private ProgressBar loading;
    private LinearLayout layoutError;
    private Button retry;

    private static String TAG = BookActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initComponents();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        bookPresenter = new BookPresenter(this);

        bookPresenter.getBookedHotels();

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading.setVisibility(View.VISIBLE);
                recycler.setVisibility(View.GONE);
                layoutError.setVisibility(View.GONE);

                bookPresenter.getBookedHotels();
            }
        });
    }

    public void initComponents() {
        recycler = (RecyclerView) findViewById(R.id.rvListBook);
        loading = findViewById(R.id.pbLoadingBook);
        layoutError = findViewById(R.id.llLayoutErrorBook);
        retry = findViewById(R.id.bRetryBook);
    }

    @Override
    public void successBookedHotels(ArrayList<Hotel> hotels) {
        Log.d(TAG, "successBookedHotels: Ej. " + hotels.get(0).getNombre());
        recycler.setVisibility(View.VISIBLE);
        loading.setVisibility(View.GONE);
        layoutError.setVisibility(View.GONE);

        recycler.setHasFixedSize(true);

        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        ListAdapterBook listAdapterBook = new ListAdapterBook(hotels);
        listAdapterBook.notifyDataSetChanged();
        recycler.setAdapter(listAdapterBook);
    }

    @Override
    public void error(String message) {
        layoutError.setVisibility(View.VISIBLE);
        loading.setVisibility(View.GONE);
        recycler.setVisibility(View.GONE);
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}