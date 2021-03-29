package com.svalero.bookingexam.feature.description;

import com.svalero.bookingexam.data.models.Hotel;

import java.util.ArrayList;

public class DescriptionModel implements DescriptionContract.Model {

    private DescriptionPresenter descriptionPresenter;
    private ArrayList<Hotel> lista;
    private Hotel myHotel;

    public DescriptionModel(DescriptionPresenter descriptionPresenter) {
        this.descriptionPresenter = descriptionPresenter;
    }

    @Override
    public void getOneHotelFromList(Hotel hotel) {
        lista = Hotel.getList();
        myHotel = new Hotel();

        for(int i = 0; i < lista.size(); i++) {
            if(lista.get(i).getNombre().equals(hotel.getNombre())) {
                myHotel = lista.get(i);
                if(myHotel != null) {
                    descriptionPresenter.deliverHotel(myHotel);
                }
            }
        }

    }
}
