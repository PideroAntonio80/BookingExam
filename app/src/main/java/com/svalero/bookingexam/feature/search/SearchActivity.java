package com.svalero.bookingexam.feature.search;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.svalero.bookingexam.R;

public class SearchActivity extends AppCompatActivity implements SearchContract.View{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }
}