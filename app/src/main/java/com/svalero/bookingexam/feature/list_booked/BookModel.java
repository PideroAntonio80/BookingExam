package com.svalero.bookingexam.feature.list_booked;

import android.os.AsyncTask;

import com.svalero.bookingexam.BuildConfig;
import com.svalero.bookingexam.data.models.Hotel;
import com.svalero.bookingexam.utils.Post;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class BookModel implements BookContract.Model {
    private static final String URL = BuildConfig.URL_SERVER + "Controller";
    private ArrayList<Hotel> ListHotels;
    private ArrayList<Hotel> listArrayBookedHotels;
    BookContract.Model.OnBookListener onBookListener;
    BookPresenter bookPresenter;

    public BookModel(BookPresenter bookPresenter) {
        this.bookPresenter = bookPresenter;
    }

    @Override
    public void getBookedHotelsWS(OnBookListener onBookListener) {
        this.onBookListener = onBookListener;
        HashMap<String,String> param = new HashMap<>();
        param.put("ACTION", "HOTEL.FIND_ALL");
        BookModel.DataCollectorBook dataCollectorBook = new BookModel.DataCollectorBook(param);
        dataCollectorBook.execute(URL);
    }

    class DataCollectorBook extends AsyncTask<String, Integer, ArrayList<Hotel>> {
        private HashMap<String, String> parametros = null;

        public DataCollectorBook( HashMap<String, String> parametros) {
            super();
            this.parametros = parametros;
        }

        @Override
        protected ArrayList<Hotel> doInBackground(String... params) {
            String url_select = params[0];
            try {
                Post post = new Post();
                JSONArray lstHotels = post.getServerDataPost(parametros,url_select);
                ListHotels = Hotel.getArrayListFromJSON(lstHotels);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ListHotels;
        }

        @Override
        protected void onPostExecute(ArrayList<Hotel> lstArrayHotels) {
            if(lstArrayHotels!=null && lstArrayHotels.size()>0){
                listArrayBookedHotels = Hotel.getListaReservados();
                if(listArrayBookedHotels != null) {
                    ArrayList<Hotel> listTenHotels = new ArrayList<>();
                    for (int i = 0; i < 10; i++) {
                        listTenHotels.add(listArrayBookedHotels.get(i));
                    }
                    onBookListener.resolve(listTenHotels);
                }

            }else{
                onBookListener.reject("Error al traer los datos del Servidor.");
            }

        }
    }
}
