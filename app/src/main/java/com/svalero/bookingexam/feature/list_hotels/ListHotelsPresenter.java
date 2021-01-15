package com.svalero.bookingexam.feature.list_hotels;

import com.svalero.bookingexam.data.Hotel;

import java.util.ArrayList;

public class ListHotelsPresenter implements ListHotelsContract.Presenter{

    private ListHotelsModel listHotelsModel;
    private ListHotelsContract.View vista;

    public ListHotelsPresenter(ListHotelsContract.View vista) {
        this.vista = vista;
        this.listHotelsModel = new ListHotelsModel(this);
    }

    @Override
    public void getHotels() {
        listHotelsModel.getHotelsWS(new ListHotelsContract.Model.OnLstHotelsListener() {
            @Override
            public void resolve(ArrayList<Hotel> hotels) {
                vista.success(hotels);
            }

            @Override
            public void reject(String error) {
                vista.error("Problemas al traer los datos.");
            }
        });
    }
}
