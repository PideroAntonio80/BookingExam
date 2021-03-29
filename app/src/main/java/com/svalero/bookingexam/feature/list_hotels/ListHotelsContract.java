package com.svalero.bookingexam.feature.list_hotels;

import com.svalero.bookingexam.data.models.Hotel;

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
        /*Me tienes que mandar el Callback, camino de retorno*/
        void getHotelsWS(OnLstHotelsListener onLstHotelsListener);
        /*Programación Reactiva (Callback)*/
        interface OnLstHotelsListener{
            void resolve(ArrayList<Hotel> hotels);
            void reject(String error);
        }
    }
}
