package com.svalero.bookingexam.feature.custom_search;

import android.os.AsyncTask;

import com.svalero.bookingexam.BuildConfig;
import com.svalero.bookingexam.data.Hotel;
import com.svalero.bookingexam.utils.Post;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchModel implements SearchContract.Model{
    private ArrayList<Hotel> listHotels;
    private OnSearchHotelsListener onSearchHotelsListener;
    private final String URL = BuildConfig.URL_SERVER + "Controller";

    @Override
    public void searchHotelsWS(OnSearchHotelsListener onSearchHotelsListener, Hotel hotel) {
        this.onSearchHotelsListener = onSearchHotelsListener;

        HashMap<String, String> param = new HashMap<>();
        param.put("ACTION", "HOTEL.FIND_SOME");
        param.put("LOCATION", hotel.getNombre_localidad());

        UnderTask underTask = new UnderTask(param);
        underTask.execute(URL);
    }

    class UnderTask extends AsyncTask<String, Integer, ArrayList<Hotel>> {
        private HashMap<String, String> parametros = null;

        public UnderTask( HashMap<String, String> parametros) {
            super();
            this.parametros = parametros;
        }

        @Override
        protected ArrayList<Hotel> doInBackground(String... params) {
            String url_select = params[0];
            try {
                Post post = new Post();
                JSONArray result = post.getServerDataPost(parametros,url_select);
                listHotels = Hotel.getArrayListFromJSON(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return listHotels;
        }

        @Override
        protected void onPostExecute(ArrayList<Hotel> hoteles) {
            if(hoteles!=null && hoteles.size()>0){
                onSearchHotelsListener.resolve(hoteles);

            }else{
                onSearchHotelsListener.reject("Error al traer los datos del Servidor.");
            }
        }
    }
}
