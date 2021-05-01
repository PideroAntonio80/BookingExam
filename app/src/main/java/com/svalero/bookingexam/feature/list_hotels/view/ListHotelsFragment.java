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

public class ListHotelsFragment extends Fragment {

    private View view;

    private RecyclerView recycler;
    private RecyclerView.LayoutManager lManager;

    private ArrayList<Hotel> hotels;

    private static final String EXTRA_LIST = "param1";

    public ListHotelsFragment() {
    }

    public static ListHotelsFragment newInstance(ArrayList<Hotel> hotels) {
        ListHotelsFragment fragment = new ListHotelsFragment();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_LIST, hotels);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            hotels = (ArrayList<Hotel>) getArguments().getSerializable(EXTRA_LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_hotels, container, false);

        initComponents();

        loadData(hotels);

        return view;
    }

    public void initComponents() {
        recycler = view.findViewById(R.id.rvLista);
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