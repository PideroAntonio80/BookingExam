package com.svalero.bookingexam.feature;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.bookingexam.R;
import com.svalero.bookingexam.feature.custom_search.SearchActivity;
import com.svalero.bookingexam.feature.list_booked.BookActivity;
import com.svalero.bookingexam.feature.list_hotels.ListHotelsActivity;
import com.svalero.bookingexam.utils.customui.DatePickerFragment;

import java.sql.Date;

public class MainActivity extends AppCompatActivity {

    private EditText personas;
    private Button dateIn;
    private Button dateOut;
    private Button buscar;
    private Spinner location;
    private Spinner menu;
    private String localidad;
    private String numPerson;
    private String dateStart;
    private String dateEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initListeners();

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (personas.getText().equals("") || personas.getText().equals("0")) {
                    info("Debes indicar un numero de personas mayor que 0");
                    return;
                }
                numPerson = String.valueOf(personas.getText());
                Intent intent = new Intent(getBaseContext(), SearchActivity.class);
                intent.putExtra("nombre_localidad", localidad);
                intent.putExtra("numero_personas", numPerson);
                intent.putExtra("fecha_entrada", dateStart);
                intent.putExtra("fecha_salida", dateEnd);
                startActivity(intent);
                System.out.println(numPerson);
            }
        });

    }

    public void info(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void initView() {
        personas = findViewById(R.id.etNumPerson);
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

                        String dateFormatted;

                        if(dayOfMonth < 10 && month < 9) {
                            dateFormatted = year + "-0" + (month + 1) + "-0" + dayOfMonth;
                        } else if (dayOfMonth < 10 && month >= 9) {
                            dateFormatted = year + "-" + (month + 1) + "-0" + dayOfMonth;
                        } else if (dayOfMonth >= 10 && month < 9) {
                            dateFormatted = year + "-0" + (month + 1) + "-" + dayOfMonth;
                        } else {
                            dateFormatted = year + "-" + (month + 1) + "-" + dayOfMonth;
                        }

                        dateIn.setText(dateFormatted);
                        dateStart = String.valueOf(dateIn.getText());
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

                        String dateFormatted;

                        if(dayOfMonth < 10 && month < 9) {
                            dateFormatted = year + "-0" + (month + 1) + "-0" + dayOfMonth;
                        } else if (dayOfMonth < 10 && month >= 9) {
                            dateFormatted = year + "-" + (month + 1) + "-0" + dayOfMonth;
                        } else if (dayOfMonth >= 10 && month < 9) {
                            dateFormatted = year + "-0" + (month + 1) + "-" + dayOfMonth;
                        } else {
                            dateFormatted = year + "-" + (month + 1) + "-" + dayOfMonth;
                        }

                        dateOut.setText(dateFormatted);
                        dateEnd = String.valueOf(dateOut.getText());
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