package com.svalero.bookingexam.feature.description;

import com.svalero.bookingexam.data.Hotel;

public class DescriptionContract {

    interface View {
        void showHotel(Hotel hotel);
    }

    interface Presenter {
        void deliverHotel(Hotel hotel);
        void getOneHotel(Hotel hotel);
    }

    interface Model {
        void getOneHotelFromList(Hotel hotel);
    }
}
