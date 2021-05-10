package com.svalero.bookingexam.feature.room;

import com.svalero.bookingexam.data.Hotel;
import com.svalero.bookingexam.data.Room;
import com.svalero.bookingexam.network.BookingNetwork;

import java.util.ArrayList;

public class RoomPresenter implements RoomContract.Presenter {

    private RoomContract.View vista;
    private BookingNetwork network;

    public RoomPresenter(RoomContract.View vista) {
        this.vista = vista;
        this.network = new BookingNetwork();
    }

    @Override
    public void getRooms(String nombreHotel) {
        network.getRoomsByHotel(new RoomContract.Model.OnLstRoomsListener() {
            @Override
            public void resolve(ArrayList<Room> room) {
                vista.success(room);
            }

            @Override
            public void reject(String error) {
                vista.error(error);
            }
        }, nombreHotel);
    }
}
