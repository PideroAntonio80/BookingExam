package com.svalero.bookingexam.feature.custom_search;

import com.svalero.bookingexam.data.Hotel;

import java.util.ArrayList;

public class SearchPresenter implements SearchContract.Presenter{
    private SearchContract.View vista;
    private SearchModel searchModel;

    public SearchPresenter(SearchContract.View vista) {
        this.vista = vista;
        this.searchModel = new SearchModel();
    }

    @Override
    public void searchHotels(Hotel hotel) {
        this.searchModel.searchHotelsWS(new SearchContract.Model.OnSearchHotelsListener() {
            @Override
            public void resolve(ArrayList<Hotel> hotels) {
                vista.success(hotels);
            }

            @Override
            public void reject(String error) {
                vista.error(error);
            }
        }, hotel);
    }
}
