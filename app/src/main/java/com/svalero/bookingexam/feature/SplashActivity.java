package com.svalero.bookingexam.feature;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.svalero.bookingexam.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //SplashScreen
        /*final Handler handler = new Handler();
        handler.postDelayed(
                new Runnable() { // Interface
                    @Override
                    public void run() {
                        // Cargar la 2ª pantalla
                        Intent intent = new Intent(
                                getBaseContext(), SearchActivity.class);
                        startActivity(intent);
                    }
                }, 3000);*/
    }
}