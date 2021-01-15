package com.svalero.bookingexam.feature.list_booked;

import com.svalero.bookingexam.data.Hotel;
import com.svalero.bookingexam.feature.list_hotels.ListHotelsContract;
import com.svalero.bookingexam.feature.list_hotels.ListHotelsModel;

import java.util.ArrayList;

public class BookPresenter implements BookContract.Presenter {

    private BookModel bookModel;
    private BookContract.View vista;

    public BookPresenter(BookContract.View vista) {
        this.vista = vista;
        this.bookModel = new BookModel(this);
    }
    @Override
    public void getBookedHotels() {
        bookModel.getBookedHotelsWS(new BookContract.Model.OnBookListener() {

            @Override
            public void resolve(ArrayList<Hotel> hotels) {
                vista.successBookedHotels(hotels);
            }

            @Override
            public void reject(String error) {
                vista.error("Problemas al traer los datos.");
            }
        });

    }
}
