package com.svalero.bookingexam.feature.list_hotels.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.svalero.bookingexam.R;
import com.svalero.bookingexam.data.Hotel;
import com.svalero.bookingexam.feature.list_hotels.ListAdapter;

import java.util.ArrayList;
import java.util.Collections;

public class CategoryHotelsFragment extends Fragment {

    private View view;

    private RecyclerView recycler;
    private RecyclerView.LayoutManager lManager;
    private ArrayList<Hotel> hotels;
    private ArrayList<Hotel> categoryHotels;

    private static String TAG = CategoryHotelsFragment.class.getSimpleName();

    private static final String EXTRA_CATEGORY_LIST = "param1";

    public CategoryHotelsFragment() {
    }


    public static CategoryHotelsFragment newInstance(ArrayList<Hotel> hotels) {
        Log.d(TAG, "Nueva instancia de este fragment");
        CategoryHotelsFragment fragment = new CategoryHotelsFragment();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_CATEGORY_LIST, hotels);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            hotels = (ArrayList<Hotel>) getArguments().getSerializable(EXTRA_CATEGORY_LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_category_hotels, container, false);

        initComponents();

        categoryHotels = getListaDestacados(hotels);

        loadData(categoryHotels);

        return view;
    }

    public void initComponents() {
        recycler = view.findViewById(R.id.rvListaCategory);
    }

    public void loadData(ArrayList<Hotel> hotels) {
        recycler.setHasFixedSize(true);
        lManager = new LinearLayoutManager(view.getContext());
        recycler.setLayoutManager(lManager);

        ListAdapter listAdapter = new ListAdapter(hotels);
        listAdapter.notifyDataSetChanged();
        recycler.setAdapter(listAdapter);
    }

    public ArrayList<Hotel> getListaDestacados(ArrayList<Hotel> list) {
        Collections.sort(list, (h1, h2) -> new Integer(h2.getEstrellas()).compareTo(new Integer(h1.getEstrellas())));
        return list;
    }
}