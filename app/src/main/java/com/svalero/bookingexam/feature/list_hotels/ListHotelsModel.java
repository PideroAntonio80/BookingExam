package com.svalero.bookingexam.feature.list_hotels;

import android.os.AsyncTask;

import com.svalero.bookingexam.data.Hotel;
import com.svalero.bookingexam.utils.Post;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class ListHotelsModel implements ListHotelsContract.Model{

    private static final String URL = "http://192.168.1.142:8090/BookingWeb/Controller";
    private ArrayList<Hotel> listArrayHotels;
    OnLstHotelsListener onLstHotelsListener;

    @Override
    public void getHotelsWS(OnLstHotelsListener onLstHotelsListener) {
        this.onLstHotelsListener = onLstHotelsListener;
        HashMap<String,String> param = new HashMap<>();
        param.put("ACTION", "HOTEL.FIND_ALL");
        DataCollector dataCollector = new DataCollector(param);
        dataCollector.execute(URL);
    }

    class DataCollector extends AsyncTask<String, Integer, ArrayList<Hotel>> {
        private HashMap<String, String> parametros = null;

        public DataCollector( HashMap<String, String> parametros) {
            super();
            this.parametros = parametros;
        }

        @Override
        protected ArrayList<Hotel> doInBackground(String... params) {
            String url_select = params[0];
            try {
                Post post = new Post();
                //JSONObject objectHotels = post.getServerDataGetObject(URL);
                JSONArray lstHotels = post.getServerDataPost(parametros,url_select);
                listArrayHotels = Hotel.getArrayListFromJSON(lstHotels);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return listArrayHotels;
        }

        @Override
        protected void onPostExecute(ArrayList<Hotel> lstArrayHotels) {
            if(lstArrayHotels!=null && lstArrayHotels.size()>0){
                onLstHotelsListener.resolve(lstArrayHotels);

            }else{
                onLstHotelsListener.reject("Error al traer los datos del Servidor.");
            }

        }
    }
}
