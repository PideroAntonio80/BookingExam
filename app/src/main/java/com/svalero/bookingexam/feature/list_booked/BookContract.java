package com.svalero.bookingexam.feature.list_booked;

import com.svalero.bookingexam.data.Hotel;

import java.util.ArrayList;

public class BookContract {
    interface View {
        void successBookedHotels(ArrayList<Hotel> hotels);
        void error(String message);
    }

    interface Presenter {
        void getBookedHotels();
    }

    interface Model {
        /*Me tienes que mandar el Callback, camino de retorno*/
        void getBookedHotelsWS(BookContract.Model.OnBookListener onBookListener);
        /*Programación Reactiva (Callback)*/
        interface OnBookListener{
            void resolve(ArrayList<Hotel> hotels);
            void reject(String error);
        }


    }
}
