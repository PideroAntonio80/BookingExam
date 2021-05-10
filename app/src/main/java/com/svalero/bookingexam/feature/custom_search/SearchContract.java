package com.svalero.bookingexam.feature.custom_search;

import com.svalero.bookingexam.data.Hotel;

import java.util.ArrayList;

public interface SearchContract {
    interface View {
        void success(ArrayList<Hotel> hotels);
        void error(String message);
    }

    interface Presenter {
        void searchHotels(String location);
    }

    interface Model {
        void searchHotelsWS(SearchContract.Model.OnSearchHotelsListener onSearchHotelsListener, String location);
        interface OnSearchHotelsListener{
            void resolve(ArrayList<Hotel> hotels);
            void reject(String error);
        }
    }
}
