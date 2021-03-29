package com.svalero.bookingexam.feature.custom_search;

import com.svalero.bookingexam.data.models.Hotel;

import java.util.ArrayList;

public interface SearchContract {
    interface View {
        void success(ArrayList<Hotel> hotels);
        void error(String message);
    }

    interface Presenter {
        void searchHotels(Hotel hotel);
    }

    interface Model {
        void searchHotelsWS(SearchContract.Model.OnSearchHotelsListener onSearchHotelsListener, Hotel hotel);
        /*Programación Reactiva (Callback)*/
        interface OnSearchHotelsListener{
            void resolve(ArrayList<Hotel> hotels);
            void reject(String error);
        }
    }
}
