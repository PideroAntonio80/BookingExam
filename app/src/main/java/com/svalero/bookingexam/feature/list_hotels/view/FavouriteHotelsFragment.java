package com.svalero.bookingexam.feature.list_hotels.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.svalero.bookingexam.R;
import com.svalero.bookingexam.data.Hotel;
import com.svalero.bookingexam.feature.list_hotels.ListAdapter;

import java.util.ArrayList;

public class FavouriteHotelsFragment extends Fragment {

    private View view;

    private RecyclerView recycler;
    private RecyclerView.LayoutManager lManager;
    private ArrayList<Hotel> hotels;
    private ArrayList<Hotel> prizeFavouriteHotels;

    private static final String EXTRA_FAVOURITE_LIST = "param1";

    public FavouriteHotelsFragment() {
    }

    public static FavouriteHotelsFragment newInstance(ArrayList<Hotel> hotels) {
        FavouriteHotelsFragment fragment = new FavouriteHotelsFragment();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_FAVOURITE_LIST, hotels);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            hotels = (ArrayList<Hotel>) getArguments().getSerializable(EXTRA_FAVOURITE_LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_favourite_hotels, container, false);

        initComponents();

        prizeFavouriteHotels = Hotel.getListaPuntos(hotels);

        loadData(prizeFavouriteHotels);

        return view;
    }

    public void initComponents() {
        recycler = view.findViewById(R.id.rvListaFavourite);
    }

    public void loadData(ArrayList<Hotel> hotels) {
        recycler.setHasFixedSize(true);
        lManager = new LinearLayoutManager(view.getContext());
        recycler.setLayoutManager(lManager);

        ListAdapter listAdapter = new ListAdapter(hotels);
        listAdapter.notifyDataSetChanged();
        recycler.setAdapter(listAdapter);
    }
}