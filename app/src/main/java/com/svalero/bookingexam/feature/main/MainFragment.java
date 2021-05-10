package com.svalero.bookingexam.feature.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.svalero.bookingexam.R;
import com.svalero.bookingexam.feature.custom_search.SearchActivity;
import com.svalero.bookingexam.feature.list_booked.BookActivity;
import com.svalero.bookingexam.feature.list_hotels.view.ListHotelsActivity;
import com.svalero.bookingexam.utils.customui.DatePickerFragment;

public class MainFragment extends Fragment {
    private View view;
    private TextInputLayout numPersonas;
    private TextInputEditText personas;
    private Button dateIn;
    private Button dateOut;
    private Button buscar;
    private Spinner location;
    private String localidad;
    private String numPerson;
    private String dateStart;
    private String dateEnd;
    private LayoutInflater menuInflater;

    private static String TAG = MainFragment.class.getSimpleName();

    public MainFragment() {
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_main, container, false);

        initView();

        initListeners();

        Log.d(TAG, "onCreateView: página busqueda");

        return view;
    }

    public void initView() {
        numPersonas = view.findViewById(R.id.tilNumPerson);
        personas = view.findViewById(R.id.tietNumPerson);
        dateIn = view.findViewById(R.id.bDateIn);
        dateOut = view.findViewById(R.id.bDateOut);
        buscar = view.findViewById(R.id.bBuscar);
        location = view.findViewById(R.id.sLocation);
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
                calendarioIn.show(getChildFragmentManager(), "fechaIda");
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
                calendarioOut.show(getChildFragmentManager(), "fechaVuelta");
            }
        });

        ArrayAdapter<CharSequence> spinAdapter = ArrayAdapter.createFromResource(view.getContext(),
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

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (String.valueOf(personas.getText()).equals("") || String.valueOf(personas.getText()).equals("0")) {
                    numPersonas.setError("Campo obligatorio");
                    return;
                }
                numPerson = String.valueOf(personas.getText());
                Intent intent = new Intent(view.getContext(), SearchActivity.class);
                intent.putExtra("nombre_localidad", localidad);
                intent.putExtra("numero_personas", numPerson);
                intent.putExtra("fecha_entrada", dateStart);
                intent.putExtra("fecha_salida", dateEnd);
                startActivity(intent);
            }
        });

        personas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numPersonas.setError(null);
            }
        });
    }
}