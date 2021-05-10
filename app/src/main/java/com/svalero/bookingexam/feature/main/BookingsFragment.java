package com.svalero.bookingexam.feature.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.svalero.bookingexam.R;

public class BookingsFragment extends Fragment {

    private View view;

    public BookingsFragment() {
    }

    public static BookingsFragment newInstance() {
        BookingsFragment fragment = new BookingsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_bookings, container, false);
        return view;
    }
}