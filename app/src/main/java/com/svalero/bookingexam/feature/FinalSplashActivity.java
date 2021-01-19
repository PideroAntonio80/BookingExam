package com.svalero.bookingexam.feature;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.svalero.bookingexam.R;

public class FinalSplashActivity extends AppCompatActivity {
    private String message;
    private TextView adios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_splash);

        adios = findViewById(R.id.tvMensajeFinal);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            message = bundle.getString("exito");
        }

        adios.setText(message);

        final Handler handler = new Handler();
        handler.postDelayed(
                new Runnable() { // Interface
                    @Override
                    public void run() {

                        Intent intent = new Intent(
                                getBaseContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }, 4000);
    }
}