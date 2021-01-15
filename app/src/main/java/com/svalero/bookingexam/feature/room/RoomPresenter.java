package com.svalero.bookingexam.feature.room;

import com.svalero.bookingexam.data.Hotel;
import com.svalero.bookingexam.data.Room;

import java.util.ArrayList;

public class RoomPresenter implements RoomContract.Presenter {

    private RoomContract.View vista;
    private RoomModel roomModel;

    public RoomPresenter(RoomContract.View vista) {
        this.vista = vista;
        roomModel = new RoomModel(this);
    }

    @Override
    public void getRooms(Hotel hotel) {
        roomModel.getRoomsWS(new RoomContract.Model.OnLstRoomsListener() {
            @Override
            public void resolve(ArrayList<Room> room) {
                vista.success(room);
            }

            @Override
            public void reject(String error) {
                vista.error(error);
            }
        }, hotel);
    }
}
