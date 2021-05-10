package com.svalero.bookingexam.network;

import com.svalero.bookingexam.data.BookingRoom;
import com.svalero.bookingexam.data.Hotel;
import com.svalero.bookingexam.data.Room;
import com.svalero.bookingexam.data.User;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BookingApi {

    @GET("Controller?ACTION=HOTEL.FIND_ALL")
    Call<List<Hotel>> getAllHotels();

    @GET("Controller?ACTION=HOTEL.FIND_SOME")
    Call<List<Hotel>> getHotelByLocation(@Query("LOCATION") String location);

    @GET("Controller?ACTION=ROOM.FIND_SOME")
    Call<List<Room>> getRoomsByHotel(@Query("HOTEL") String hotelName);

    @GET("Controller?ACTION=HOTEL.FIND_BY_BOOKS")
    Call<List<Hotel>> getTenBookHotels();

    @GET("Controller?ACTION=USER.LOGIN")
    Call<User> identifyUser(@Query("EMAIL") String email, @Query("PASSWORD") String password);

    @POST("Controller?ACTION=USER.ADD")
    Call<User> registerUser(@Query("NOMBRE") String nombre,
                            @Query("APELLIDOS") String apellidos,
                            @Query("EMAIL") String email,
                            @Query("PASSWORD") String password);

    @POST("Controller?ACTION=BOOKINGROOM.INSERT")
    Call<BookingRoom> doBookingRoom(@Query("USER") int idUser,
                                    @Query("ROOM") int idRoom,
                                    @Query("NIGHTS") int nights,
                                    @Query("PRIZE") double prize,
                                    @Query("PERSONS") int persons,
                                    @Query("DATEIN") Date dateIn,
                                    @Query("DATEOUT") Date dateOut);
}


