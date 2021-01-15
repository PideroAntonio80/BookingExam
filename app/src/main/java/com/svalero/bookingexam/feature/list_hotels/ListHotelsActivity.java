package com.svalero.bookingexam.feature.list_hotels;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.svalero.bookingexam.R;
import com.svalero.bookingexam.data.Hotel;
import com.svalero.bookingexam.feature.filter.FilterActivity;

import java.util.ArrayList;

public class ListHotelsActivity extends AppCompatActivity implements ListHotelsContract.View{

    private RecyclerView recycler;
    private ListHotelsPresenter listHotelsPresenter;
    private RecyclerView.LayoutManager lManager;
    private DividerItemDecoration divider;
    private Spinner filtros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hotels);

        listHotelsPresenter = new ListHotelsPresenter(this);
        listHotelsPresenter.getHotels();

        filtros = findViewById(R.id.sFilter);

        ArrayAdapter<CharSequence> spinAdapter = ArrayAdapter.createFromResource(this,
                R.array.filter, android.R.layout.simple_spinner_dropdown_item);
        filtros.setAdapter(spinAdapter);

        filtros.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getSelectedItem().toString().equals("--Mas Destacados--")){
                    Intent navegar = new Intent(getBaseContext(), FilterActivity.class);
                    navegar.putExtra("opcion", 1);
                    filtros.setSelection(0);
                    startActivity(navegar);
                }
                else if (parent.getSelectedItem().toString().equals("--Por Precio (Ascendente)--")){
                    Intent navegar2 = new Intent(getBaseContext(), FilterActivity.class);
                    navegar2.putExtra("opcion", 2);
                    filtros.setSelection(0);
                    startActivity(navegar2);
                }
                else if (parent.getSelectedItem().toString().equals("--Por Precio (Descendente)--")){
                    Intent navegar3 = new Intent(getBaseContext(), FilterActivity.class);
                    navegar3.putExtra("opcion", 3);
                    filtros.setSelection(0);
                    startActivity(navegar3);
                }
                else if (parent.getSelectedItem().toString().equals("--Por Puntuacion--")){
                    Intent navegar4 = new Intent(getBaseContext(), FilterActivity.class);
                    navegar4.putExtra("opcion", 4);
                    filtros.setSelection(0);
                    startActivity(navegar4);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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