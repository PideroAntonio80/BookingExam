package com.svalero.bookingexam.feature.filter;

import com.svalero.bookingexam.data.models.Hotel;

import java.util.ArrayList;

public class FilterModel implements FilterContract.Model{

    private FilterPresenter filterPresenter;
    private ArrayList<Hotel> lstFilterDestacados;
    private ArrayList<Hotel> lstFilterPrecioAsc;
    private ArrayList<Hotel> lstFilterPrecioDesc;
    private ArrayList<Hotel> lstFilterPuntos;

    public FilterModel(FilterPresenter filterPresenter) {
        this.filterPresenter = filterPresenter;
    }

    @Override
    public void getXDestacadosFromList() {
        lstFilterDestacados = Hotel.getListaDestacados();
        System.out.println(lstFilterDestacados.get(0).getNombre());
        if(lstFilterDestacados != null) {
            filterPresenter.getBackXDestacados(lstFilterDestacados);
        }
    }

    @Override
    public void getXPrecioFromListAsc() {
        lstFilterPrecioAsc = Hotel.getListaPrecioAsc();
        if(lstFilterPrecioAsc != null) {
            filterPresenter.getBackXPrecioAsc(lstFilterPrecioAsc);
        }
    }

    @Override
    public void getXPrecioFromListDesc() {
        lstFilterPrecioDesc = Hotel.getListaPrecioDesc();
        if(lstFilterPrecioDesc != null) {
            filterPresenter.getBackXPrecioDesc(lstFilterPrecioDesc);
        }
    }

    @Override
    public void getXPuntosFromList() {
        lstFilterPuntos = Hotel.getListaPuntos();
        if(lstFilterPuntos != null) {
            filterPresenter.getBackXPuntos(lstFilterPuntos);
        }
    }
}
