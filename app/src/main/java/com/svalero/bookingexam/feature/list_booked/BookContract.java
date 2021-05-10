package com.svalero.bookingexam.feature.list_booked;

import com.svalero.bookingexam.data.Hotel;

import java.util.ArrayList;

public interface BookContract {
    interface View {
        void successBookedHotels(ArrayList<Hotel> hotels);
        void error(String message);
    }

    interface Presenter {
        void getBookedHotels();
    }

    interface Model {
        void getBookedHotelsWS(BookContract.Model.OnBookListener onBookListener);

        interface OnBookListener{
            void resolve(ArrayList<Hotel> hotels);
            void reject(String error);
        }


    }
}
