package com.svalero.bookingexam.feature.filter;

import com.svalero.bookingexam.data.Hotel;

import java.util.ArrayList;

public class FilterPresenter implements FilterContract.Presenter{

    private FilterActivity filterActivity;
    private FilterModel filterModel;

    public FilterPresenter(FilterActivity filterActivity) {
        this.filterActivity = filterActivity;
        this.filterModel = new FilterModel(this);
    }

    @Override
    public void getXDestacados() {
        filterModel.getXDestacadosFromList();
    }

    @Override
    public void getBackXDestacados(ArrayList<Hotel> listaDestacados) {
        filterActivity.successDestacados(listaDestacados);
    }

    @Override
    public void getXPrecioAsc() {
        filterModel.getXPrecioFromListAsc();
    }

    @Override
    public void getXPrecioDesc() {
        filterModel.getXPrecioFromListDesc();
    }

    @Override
    public void getBackXPrecioAsc(ArrayList<Hotel> listaPrecioAsc) {
        filterActivity.successPrecioAsc(listaPrecioAsc);
    }

    @Override
    public void getBackXPrecioDesc(ArrayList<Hotel> listaPrecioDesc) {
        filterActivity.successPrecioDesc(listaPrecioDesc);
    }

    @Override
    public void getXPuntos() {
        filterModel.getXPuntosFromList();
    }

    @Override
    public void getBackXPuntos(ArrayList<Hotel> listaPuntos) {
        filterActivity.successPuntos(listaPuntos);
    }
}
