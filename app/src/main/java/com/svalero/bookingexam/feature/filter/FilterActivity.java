package com.svalero.bookingexam.feature.filter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.svalero.bookingexam.R;
import com.svalero.bookingexam.data.Hotel;

import java.util.ArrayList;

public class FilterActivity extends AppCompatActivity implements FilterContract.View{

    private RecyclerView recyclerFilter;
    private RecyclerView.LayoutManager lManagerFilter;
    private FilterPresenter filterPresenter;
    private int opcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        filterPresenter = new FilterPresenter(this);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            opcion = bundle.getInt("opcion", 1);
        }

        if(opcion == 1) {
            filterPresenter.getXDestacados();
        } else if(opcion == 2) {
            filterPresenter.getXPrecioAsc();
        } else if(opcion == 3) {
            filterPresenter.getXPrecioDesc();
        } else if(opcion == 4) {
            filterPresenter.getXPuntos();
        }

    }

    @Override
    public void successDestacados(ArrayList<Hotel> hotelDestacados) {
        pintarLista(hotelDestacados);
    }

    @Override
    public void successPrecioAsc(ArrayList<Hotel> hotelPrecioAsc) {
        pintarLista(hotelPrecioAsc);
    }

    @Override
    public void successPrecioDesc(ArrayList<Hotel> hotelPrecioDesc) {
        pintarLista(hotelPrecioDesc);
    }

    @Override
    public void successPuntos(ArrayList<Hotel> hotelPuntos) {
        pintarLista(hotelPuntos);
    }

    @Override
    public void error(String message) {

    }

    public void pintarLista(ArrayList<Hotel> lista) {
        recyclerFilter = (RecyclerView) findViewById(R.id.rvFilter);
        recyclerFilter.setHasFixedSize(true);

        lManagerFilter = new LinearLayoutManager(this);
        recyclerFilter.setLayoutManager(lManagerFilter);

        FilterAdapter filterAdapter = new FilterAdapter(lista);

        recyclerFilter.setAdapter(filterAdapter);
    }
}