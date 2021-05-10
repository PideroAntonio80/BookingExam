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

public class PrizeDescHotelsFragment extends Fragment {

    private View view;

    private RecyclerView recycler;
    private RecyclerView.LayoutManager lManager;
    private ArrayList<Hotel> hotels;
    private ArrayList<Hotel> prizeDescHotels;

    private static String TAG = PrizeDescHotelsFragment.class.getSimpleName();

    private static final String EXTRA_PRIZE_DESC_LIST = "param1";

    public PrizeDescHotelsFragment() {
    }

    public static PrizeDescHotelsFragment newInstance(ArrayList<Hotel> hotels) {
        Log.d(TAG, "Nueva instancia de este fragment");
        PrizeDescHotelsFragment fragment = new PrizeDescHotelsFragment();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_PRIZE_DESC_LIST, hotels);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            hotels = (ArrayList<Hotel>) getArguments().getSerializable(EXTRA_PRIZE_DESC_LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_prize_desc_hotels, container, false);

        initComponents();

        prizeDescHotels = getListaPrecioDesc(hotels);

        loadData(prizeDescHotels);

        return view;
    }

    public void initComponents() {
        recycler = view.findViewById(R.id.rvListaPrizeDesc);
    }

    public void loadData(ArrayList<Hotel> hotels) {
        recycler.setHasFixedSize(true);
        lManager = new LinearLayoutManager(view.getContext());
        recycler.setLayoutManager(lManager);

        ListAdapter listAdapter = new ListAdapter(hotels);
        listAdapter.notifyDataSetChanged();
        recycler.setAdapter(listAdapter);
    }

    public ArrayList<Hotel> getListaPrecioDesc(ArrayList<Hotel> list) {
        Collections.sort(list, (h1, h2) -> new Double(h2.getPrecio_medio()).compareTo(new Double(h1.getPrecio_medio())));
        return list;
    }
}