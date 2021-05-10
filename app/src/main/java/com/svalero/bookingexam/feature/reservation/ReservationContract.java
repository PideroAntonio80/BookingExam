package com.svalero.bookingexam.feature.reservation;

import com.svalero.bookingexam.data.BookingRoom;

public interface ReservationContract {
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
