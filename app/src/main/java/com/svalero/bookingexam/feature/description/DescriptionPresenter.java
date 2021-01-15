package com.svalero.bookingexam.feature.description;

import com.svalero.bookingexam.data.Hotel;

public class DescriptionPresenter implements DescriptionContract.Presenter {

    private DescriptionActivity vista;
    private DescriptionModel descriptionModel;

    public DescriptionPresenter(DescriptionActivity vista) {
        this.vista = vista;
        this.descriptionModel = new DescriptionModel(this);
    }

    @Override
    public void deliverHotel(Hotel hotel) {
        if(vista != null) {
            vista.showHotel(hotel);
        }
    }

    @Override
    public void getOneHotel(Hotel hotel) {
        if(descriptionModel != null) {
            descriptionModel.getOneHotelFromList(hotel);
        }
    }
}
