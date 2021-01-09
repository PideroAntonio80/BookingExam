package com.svalero.bookingexam.feature.filter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.svalero.bookingexam.R;

public class FilterActivity extends AppCompatActivity implements FilterContract.View{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
    }
}