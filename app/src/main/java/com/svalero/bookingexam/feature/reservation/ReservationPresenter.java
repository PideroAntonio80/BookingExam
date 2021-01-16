package com.svalero.bookingexam.feature.reservation;

import com.svalero.bookingexam.data.BookingRoom;

public class ReservationPresenter implements ReservationContract.Presenter {
    private ReservationContract.View vista;
    private ReservationModel reservationModel;

    public ReservationPresenter(ReservationContract.View vista) {
        this.vista = vista;
        this.reservationModel = new ReservationModel();
    }


    @Override
    public void makeReservation(BookingRoom bookingRoom) {
        this.reservationModel.makeReservationWS(new ReservationContract.Model.OnBookingRoomListener() {
            @Override
            public void onFinished(String message) {
                vista.success(message);
            }

            @Override
            public void onFailure(String error) {
                vista.failure(error);
            }
        }, bookingRoom);
    }
}
