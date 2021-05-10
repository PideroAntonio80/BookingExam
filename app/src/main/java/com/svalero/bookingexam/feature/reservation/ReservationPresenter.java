package com.svalero.bookingexam.feature.reservation;

import com.svalero.bookingexam.data.BookingRoom;
import com.svalero.bookingexam.network.BookingNetwork;

public class ReservationPresenter implements ReservationContract.Presenter {
    private ReservationContract.View vista;
    private BookingNetwork network;
    //private ReservationModel reservationModel;

    public ReservationPresenter(ReservationContract.View vista) {
        this.vista = vista;
        this.network = new BookingNetwork();
        //this.reservationModel = new ReservationModel();
    }

    @Override
    public void makeReservation(BookingRoom bookingRoom) {
        network.doBookingRoom(new ReservationContract.Model.OnBookingRoomListener() {
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

    /*@Override
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
    }*/
}
