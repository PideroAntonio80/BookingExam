package com.svalero.bookingexam.network;

import com.svalero.bookingexam.data.Hotel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BookingApi {

@GET("Controller?ACTION=HOTEL.FIND_ALL")
    Call<List<Hotel>> getAllHotels();
}
