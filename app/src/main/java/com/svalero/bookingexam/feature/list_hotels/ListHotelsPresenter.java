package com.svalero.bookingexam.feature.list_hotels;

import com.svalero.bookingexam.data.Hotel;
import com.svalero.bookingexam.network.BookingNetwork;

import java.util.ArrayList;

public class ListHotelsPresenter implements ListHotelsContract.Presenter{

    private BookingNetwork network;
    private ListHotelsContract.View vista;

    public ListHotelsPresenter(ListHotelsContract.View vista) {
        this.vista = vista;
        this.network = new BookingNetwork();
    }

    @Override
    public void getHotels() {
        network.getAllHotels(new ListHotelsContract.Model.OnLstHotelsListener() {
            @Override
            public void resolve(ArrayList<Hotel> hotels) {
                vista.success(hotels);
            }

            @Override
            public void reject(String error) {
                vista.error(error);
            }
        });
    }
}
