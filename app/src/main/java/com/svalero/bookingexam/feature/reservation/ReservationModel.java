package com.svalero.bookingexam.feature.reservation;

import android.os.AsyncTask;

import com.svalero.bookingexam.data.BookingRoom;
import com.svalero.bookingexam.utils.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ReservationModel implements ReservationContract.Model {
    ArrayList<BookingRoom> lista;
    private OnBookingRoomListener onBookingRoomListener;
    private String URL = "http://192.168.1.142:8090/BookingWeb/Controller";

    @Override
    public void makeReservationWS(OnBookingRoomListener onBookingRoomListener, BookingRoom bookingRoom) {
        this.onBookingRoomListener = onBookingRoomListener;

        HashMap<String, String> param = new HashMap<>();
        param.put("ACTION", "BOOKINGROOM.INSERT");
        param.put("USER", String.valueOf(bookingRoom.getIdUser()));
        param.put("ROOM", String.valueOf(bookingRoom.getIdRoom()));

        UnderTask underTask = new UnderTask(param);
        underTask.execute(URL);
    }

    class UnderTask extends AsyncTask<String, Integer, Boolean> {
        private HashMap<String, String> parametros = null;

        public UnderTask( HashMap<String, String> parametros) {
            super();
            this.parametros = parametros;
        }

        @Override
        protected Boolean doInBackground(String... params) {
            String url_select = params[0];
            try {
                Post post = new Post();
                JSONArray result = post.getServerDataPost(parametros,url_select);
                lista = BookingRoom.getArrayFromJSON(result) ;
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
