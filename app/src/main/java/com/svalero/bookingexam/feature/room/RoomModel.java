package com.svalero.bookingexam.feature.room;

import android.os.AsyncTask;

import com.svalero.bookingexam.BuildConfig;
import com.svalero.bookingexam.data.Hotel;
import com.svalero.bookingexam.data.Room;
import com.svalero.bookingexam.utils.Post;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class RoomModel implements RoomContract.Model {
    private static final String URL = BuildConfig.URL_SERVER + "Controller";
    private ArrayList<Room> listArrayRooms;
    OnLstRoomsListener onLstRoomsListener;
    private RoomPresenter roomPresenter;

    public RoomModel(RoomPresenter roomPresenter) {
        this.roomPresenter = roomPresenter;
    }

    @Override
    public void getRoomsWS(OnLstRoomsListener onLstRoomsListener, Hotel hotel) {
        this.onLstRoomsListener = onLstRoomsListener;
        HashMap<String,String> param = new HashMap<>();
        param.put("ACTION", "ROOM.FIND_SOME");
        param.put("HOTEL", hotel.getNombre());
        RoomModel.DataCollector dataCollector = new RoomModel.DataCollector(param);
        dataCollector.execute(URL);
    }

    class DataCollector extends AsyncTask<String, Integer, ArrayList<Room>> {
        private HashMap<String, String> parametros = null;

        public DataCollector( HashMap<String, String> parametros) {
            super();
            this.parametros = parametros;
        }

        @Override
        protected ArrayList<Room> doInBackground(String... params) {
            String url_select = params[0];
            try {
                Post post = new Post();
                JSONArray result = post.getServerDataPost(parametros,url_select);
                listArrayRooms = Room.getArrayListFromJSON(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return listArrayRooms;
        }

        @Override
        protected void onPostExecute(ArrayList<Room> rooms) {
            if(rooms!=null && rooms.size()>0){
                onLstRoomsListener.resolve(rooms);

            }else{
                onLstRoomsListener.reject("Error al traer los datos del Servidor.");
            }
        }
    }
}
