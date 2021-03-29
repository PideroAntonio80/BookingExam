package com.svalero.bookingexam.feature.reservation;

import com.svalero.bookingexam.data.models.BookingRoom;

public class ReservationContract {
    interface View {
        void success(String message);
        void failure(String message);
    }

    interface Presenter {
        void makeReservation(BookingRoom bookingRoom);
    }

    interface Model {
        interface OnBookingRoomListener {
            void onFinished(String message);
            void onFailure(String error);
        }
        void makeReservationWS(OnBookingRoomListener onBookingRoomListener, BookingRoom bookingRoom);

    }
}
