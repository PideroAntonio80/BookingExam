package com.svalero.bookingexam.feature.custom_search;

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

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements SearchContract.View {

    private RecyclerView recycler;
    private RecyclerView.LayoutManager lManager;
    private SearchPresenter searchPresenter;
    private String localidad;
    private String numPers;
    private String fechaIn;
    private String fechaOut;

    private ProgressBar loading;
    private LinearLayout layoutError;
    private Button retry;

    private static String TAG = SearchActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initComponents();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            localidad = bundle.getString("nombre_localidad");
            numPers = bundle.getString("numero_personas");
            fechaIn = bundle.getString("fecha_entrada");
            fechaOut = bundle.getString("fecha_salida");
        }

        searchPresenter = new SearchPresenter(this);
        searchPresenter.searchHotels(localidad);

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading.setVisibility(View.VISIBLE);
                layoutError.setVisibility(View.GONE);
                recycler.setVisibility(View.GONE);

                searchPresenter.searchHotels(localidad);
            }
        });
    }

    public void initComponents() {
        recycler = (RecyclerView) findViewById(R.id.rvSearch);
        loading = findViewById(R.id.pbLoadingSearch);
        layoutError = findViewById(R.id.llLayoutErrorSearch);
        retry = findViewById(R.id.bRetrySearch);
    }

    @Override
    public void success(ArrayList<Hotel> hotels) {
        Log.e(TAG, "success: ej. " + hotels.get(0).getNombre());
        recycler.setVisibility(View.VISIBLE);
        loading.setVisibility(View.GONE);
        layoutError.setVisibility(View.GONE);

        recycler.setHasFixedSize(true);

        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        SearchAdapter searchAdapter = new SearchAdapter(hotels, numPers, fechaIn, fechaOut);
        searchAdapter.notifyDataSetChanged();
        recycler.setAdapter(searchAdapter);
    }

    @Override
    public void error(String message) {
        layoutError.setVisibility(View.VISIBLE);
        recycler.setVisibility(View.GONE);
        loading.setVisibility(View.GONE);
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}