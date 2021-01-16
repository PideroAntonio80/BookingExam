package com.svalero.bookingexam.feature.reservation;

import com.svalero.bookingexam.data.BookingRoom;
import com.svalero.bookingexam.data.Hotel;
import com.svalero.bookingexam.data.Room;
import com.svalero.bookingexam.data.User;
import com.svalero.bookingexam.feature.login.LoginContract;

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
