package com.svalero.bookingexam.feature.reservation;

import android.os.AsyncTask;

import com.svalero.bookingexam.BuildConfig;
import com.svalero.bookingexam.data.BookingRoom;
import com.svalero.bookingexam.utils.Post;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class ReservationModel implements ReservationContract.Model {
    ArrayList<BookingRoom> lista;
    private OnBookingRoomListener onBookingRoomListener;
    private final String URL = BuildConfig.URL_SERVER + "Controller";

    @Override
    public void makeReservationWS(OnBookingRoomListener onBookingRoomListener, BookingRoom bookingRoom) {
        this.onBookingRoomListener = onBookingRoomListener;

        HashMap<String, String> param = new HashMap<>();
        param.put("ACTION", "BOOKINGROOM.INSERT");
        param.put("USER", String.valueOf(bookingRoom.getIdUser()));
        param.put("ROOM", String.valueOf(bookingRoom.getIdRoom()));
        param.put("NIGHTS", String.valueOf(bookingRoom.getNoches()));
        param.put("DATEIN", String.valueOf(bookingRoom.getDateIn()));
        param.put("DATEOUT", String.valueOf(bookingRoom.getDateOut()));
        param.put("PERSONS", String.valueOf(bookingRoom.getNumPerson()));
        param.put("PRIZE", String.valueOf(bookingRoom.getPrecio()));

        HashMap<String, String> paramRoom = new HashMap<>();
        paramRoom.put("ACTION", "ROOM.UPDATE");
        paramRoom.put("ROOM", String.valueOf(bookingRoom.getIdRoom()));


        UnderTask underTask = new UnderTask(param, paramRoom);
        underTask.execute(URL);
    }

    class UnderTask extends AsyncTask<String, Integer, Boolean> {
        private HashMap<String, String> parametros = null;

        private HashMap<String, String> parametrosRoom = null;

        public UnderTask(HashMap<String, String> parametros, HashMap<String, String> parametrosRoom) {
            super();
            this.parametros = parametros;
            this.parametrosRoom = parametrosRoom;
        }

        @Override
        protected Boolean doInBackground(String... params) {
            String url_select = params[0];
            String url_selectRoom = params[0];
            try {
                Post post = new Post();
                JSONArray result = post.getServerDataPost(parametros,url_select);
                //lista = BookingRoom.getArrayFromJSON(result) ;

                JSONArray result2 = post.getServerDataPost(parametrosRoom,url_selectRoom);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean resp) {
            try {
                if(resp) {
                    onBookingRoomListener.onFinished("¡Gracias, Buen Viaje!");
                }
            } catch (Exception e) {
                onBookingRoomListener.onFailure("Fallo al relizar la reserva");
            }
        }

    }
}
