package com.svalero.bookingexam.feature.room;

import com.svalero.bookingexam.data.Room;

import java.util.ArrayList;

public interface RoomContract {
    interface View {
        void success(ArrayList<Room> rooms);
        void error(String message);
    }

    interface Presenter {
        void getRooms(String nombreHotel);
    }

    interface Model {
        void getRoomsWS(RoomContract.Model.OnLstRoomsListener onLstRoomsListener, String nombreHotel);
        interface OnLstRoomsListener{
            void resolve(ArrayList<Room> room);
            void reject(String error);
        }
    }
}
