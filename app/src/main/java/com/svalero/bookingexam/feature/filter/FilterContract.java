package com.svalero.bookingexam.feature.filter;

import com.svalero.bookingexam.data.models.Hotel;

import java.util.ArrayList;

public interface FilterContract {
    interface View {
        void successDestacados(ArrayList<Hotel> hotelDestacados);
        void successPrecioAsc(ArrayList<Hotel> hotelPrecioAsc);
        void successPrecioDesc(ArrayList<Hotel> hotelPrecioDesc);
        void successPuntos(ArrayList<Hotel> hotelPuntos);
        void error(String message);
    }

    interface Presenter {
        void getXDestacados();
        void getBackXDestacados(ArrayList<Hotel> listaDestacados);

        void getXPrecioAsc();
        void getXPrecioDesc();
        void getBackXPrecioAsc(ArrayList<Hotel> listaPrecioAsc);
        void getBackXPrecioDesc(ArrayList<Hotel> listaPrecioDesc);

        void getXPuntos();
        void getBackXPuntos(ArrayList<Hotel> listaPuntos);
    }

    interface Model {
        void getXDestacadosFromList();
        void getXPrecioFromListAsc();
        void getXPrecioFromListDesc();
        void getXPuntosFromList();
    }
}
