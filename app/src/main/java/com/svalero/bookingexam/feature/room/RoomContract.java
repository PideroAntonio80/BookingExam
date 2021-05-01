package com.svalero.bookingexam.feature.room;

import com.svalero.bookingexam.data.Hotel;
import com.svalero.bookingexam.data.Room;

import java.util.ArrayList;

public class RoomContract {
    interface View {
        void success(ArrayList<Room> rooms);
        void error(String message);
    }

    interface Presenter {
        void getRooms(Hotel hotel);
    }

    interface Model {
        /*Me tienes que mandar el Callback, camino de retorno*/
        void getRoomsWS(RoomContract.Model.OnLstRoomsListener onLstRoomsListener, Hotel hotel);
        /*Programación Reactiva (Callback)*/
        interface OnLstRoomsListener{
            void resolve(ArrayList<Room> room);
            void reject(String error);
        }
    }
}
