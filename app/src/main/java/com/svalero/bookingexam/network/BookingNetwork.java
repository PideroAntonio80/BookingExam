package com.svalero.bookingexam.network;

import com.svalero.bookingexam.BuildConfig;
import com.svalero.bookingexam.data.Hotel;
import com.svalero.bookingexam.feature.list_hotels.ListHotelsContract;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookingNetwork {
    private Retrofit retrofit;

    public BookingNetwork() {

        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.URL_SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void getAllHotels(ListHotelsContract.Model.OnLstHotelsListener listener) {

        BookingApi api = retrofit.create(BookingApi.class);
        api.getAllHotels().enqueue(new Callback<List<Hotel>>() {
            @Override
            public void onResponse(Call<List<Hotel>> call, Response<List<Hotel>> response) {
                listener.resolve(new ArrayList<>(response.body()));
            }

            @Override
            public void onFailure(Call<List<Hotel>> call, Throwable t) {
                listener.reject("Error al traer los datos del Servidor.");
            }
        });
    }
}
