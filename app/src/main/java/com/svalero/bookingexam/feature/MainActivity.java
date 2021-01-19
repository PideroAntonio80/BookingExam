package com.svalero.bookingexam.feature;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.bookingexam.R;
import com.svalero.bookingexam.feature.custom_search.SearchActivity;
import com.svalero.bookingexam.feature.list_booked.BookActivity;
import com.svalero.bookingexam.feature.list_hotels.ListHotelsActivity;
import com.svalero.bookingexam.utils.customui.DatePickerFragment;

public class MainActivity extends AppCompatActivity {

    private Button dateIn;
    private Button dateOut;
    private Button buscar;
    private Spinner location;
    private Spinner menu;
    private String localidad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initListeners();

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), SearchActivity.class);
                intent.putExtra("nombre_localidad", localidad);
                startActivity(intent);
            }
        });

    }

    public void initView() {
        dateIn = findViewById(R.id.bDateIn);
        dateOut = findViewById(R.id.bDateOut);
        buscar = findViewById(R.id.bBuscar);
        location = findViewById(R.id.sLocation);
        menu = findViewById(R.id.sMenu);
    }

    public void initListeners() {
        dateIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment calendarioIn = DatePickerFragment.newInstance(new DatePickerFragment.OnDateChangeListener() {
                    @Override
                    public void onDateChange(int year, int month, int dayOfMonth) {
                        String dateFormatted = dayOfMonth + "/" + (month + 1) + "/" + year;
                        dateIn.setText(dateFormatted);
                    }
                });
                calendarioIn.show(getSupportFragmentManager(), "fechaIda");
            }
        });

        dateOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment calendarioOut = DatePickerFragment.newInstance(new DatePickerFragment.OnDateChangeListener() {
                    @Override
                    public void onDateChange(int year, int month, int dayOfMonth) {
                        String dateFormatted = dayOfMonth + "/" + (month + 1) + "/" + year;
                        dateOut.setText(dateFormatted);
                    }
                });
                calendarioOut.show(getSupportFragmentManager(), "fechaVuelta");
            }
        });

        ArrayAdapter<CharSequence> spinAdapterMenu = ArrayAdapter.createFromResource(this,
                R.array.menu, android.R.layout.simple_spinner_dropdown_item);
        menu.setAdapter(spinAdapterMenu);

        menu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getSelectedItem().toString().equals("--Lista completa de hoteles--")){
                    Intent navegar = new Intent(getBaseContext(), ListHotelsActivity.class);
                    menu.setSelection(0);
                    startActivity(navegar);
                }
                else if (parent.getSelectedItem().toString().equals("--Los 10 mas reservados--")){
                    Intent navegar2 = new Intent(getBaseContext(), BookActivity.class);
                    menu.setSelection(0);
                    startActivity(navegar2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> spinAdapter = ArrayAdapter.createFromResource(this,
                R.array.localidades, android.R.layout.simple_spinner_dropdown_item);
        location.setAdapter(spinAdapter);

        location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getSelectedItem().toString().equals("Benasque")){
                    localidad = "Benasque";
                }
                else if (parent.getSelectedItem().toString().equals("Bielsa")){
                    localidad = "Bielsa";
                }
                else if (parent.getSelectedItem().toString().equals("Torla")){
                    localidad = "Torla";
                }
                else if (parent.getSelectedItem().toString().equals("Plan")){
                    localidad = "Plan";
                }
                else if (parent.getSelectedItem().toString().equals("Canfranc")){
                    localidad = "Canfranc";
                }
                else if (parent.getSelectedItem().toString().equals("Anso")){
                    localidad = "Anso";
                }
                else if (parent.getSelectedItem().toString().equals("Hecho")){
                    localidad = "Hecho";
                }
                else if (parent.getSelectedItem().toString().equals("Jaca")){
                    localidad = "Jaca";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}