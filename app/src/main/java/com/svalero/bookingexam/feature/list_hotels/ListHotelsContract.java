package com.svalero.bookingexam.feature.list_hotels;

import com.svalero.bookingexam.data.Hotel;

import java.util.ArrayList;

public interface ListHotelsContract {
    interface View {
        void success(ArrayList<Hotel> hotels);
        void error(String message);
    }

    interface Presenter {
        void getHotels();
    }

    interface Model {
        void getHotelsWS(OnLstHotelsListener onLstHotelsListener);
        interface OnLstHotelsListener{
            void resolve(ArrayList<Hotel> hotels);
            void reject(String error);
        }
    }
}
