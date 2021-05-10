package com.svalero.bookingexam.feature.custom_search;

import com.svalero.bookingexam.data.Hotel;
import com.svalero.bookingexam.network.BookingNetwork;

import java.util.ArrayList;

public class SearchPresenter implements SearchContract.Presenter{

    private SearchContract.View vista;
    private BookingNetwork network;

    public SearchPresenter(SearchContract.View vista) {
        this.vista = vista;
        this.network = new BookingNetwork();
    }

    @Override
    public void searchHotels(String location) {

        network.getHotelsByLocation(new SearchContract.Model.OnSearchHotelsListener() {
            @Override
            public void resolve(ArrayList<Hotel> hotels) {
                vista.success(hotels);
            }

            @Override
            public void reject(String error) {
                vista.error(error);
            }
        }, location);
    }
}
