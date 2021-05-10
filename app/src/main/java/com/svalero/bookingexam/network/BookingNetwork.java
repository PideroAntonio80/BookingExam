package com.svalero.bookingexam.network;

import android.util.Log;

import com.svalero.bookingexam.BuildConfig;
import com.svalero.bookingexam.data.BookingRoom;
import com.svalero.bookingexam.data.Hotel;
import com.svalero.bookingexam.data.Room;
import com.svalero.bookingexam.data.User;
import com.svalero.bookingexam.feature.custom_search.SearchContract;
import com.svalero.bookingexam.feature.list_booked.BookContract;
import com.svalero.bookingexam.feature.list_hotels.ListHotelsContract;
import com.svalero.bookingexam.feature.login.LoginContract;
import com.svalero.bookingexam.feature.register.RegisterContract;
import com.svalero.bookingexam.feature.reservation.ReservationContract;
import com.svalero.bookingexam.feature.room.RoomContract;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookingNetwork {
    private Retrofit retrofit;

    private static String TAG = BookingNetwork.class.getSimpleName();

    public BookingNetwork() {

        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.URL_SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void getAllHotels(ListHotelsContract.Model.OnLstHotelsListener listener) {
        BookingApi api = retrofit.create(BookingApi.class); // Uno para todos? BookingApi api (variable de clase)
        api.getAllHotels().enqueue(new Callback<List<Hotel>>() {
            @Override
            public void onResponse(Call<List<Hotel>> call, Response<List<Hotel>> response) {
                if(response != null && response.body() != null) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                    listener.resolve(new ArrayList<>(response.body()));
                }
            }

            @Override
            public void onFailure(Call<List<Hotel>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t.fillInStackTrace());
                t.printStackTrace();
                listener.reject("Error al traer los datos del Servidor.");
            }
        });
    }

    public void getHotelsByLocation(SearchContract.Model.OnSearchHotelsListener listener, String location) {
        BookingApi api = retrofit.create(BookingApi.class);
        api.getHotelByLocation(location).enqueue(new Callback<List<Hotel>>() {

            @Override
            public void onResponse(Call<List<Hotel>> call, Response<List<Hotel>> response) {
                if(response != null && response.body() != null) {
                    listener.resolve(new ArrayList<>(response.body()));
                }
            }

            @Override
            public void onFailure(Call<List<Hotel>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t.fillInStackTrace());
                t.printStackTrace();
                listener.reject("Error al traer los datos del Servidor.");
            }
        });
    }

    public void getRoomsByHotel(RoomContract.Model.OnLstRoomsListener listener, String hotelName) {
        BookingApi api = retrofit.create(BookingApi.class);
        api.getRoomsByHotel(hotelName).enqueue(new Callback<List<Room>>() {
            @Override
            public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {
                if(response != null && response.body() != null) {
                    listener.resolve(new ArrayList<>(response.body()));
                }
            }

            @Override
            public void onFailure(Call<List<Room>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t.fillInStackTrace());
                t.printStackTrace();
                listener.reject("Error al traer los datos del Servidor.");
            }
        });
    }

    public void getTenBookHotels(BookContract.Model.OnBookListener listener) {
        BookingApi api = retrofit.create(BookingApi.class);
        api.getTenBookHotels().enqueue(new Callback<List<Hotel>>() {
            @Override
            public void onResponse(Call<List<Hotel>> call, Response<List<Hotel>> response) {
                if(response != null && response.body() != null) {
                    listener.resolve(new ArrayList<>(response.body()));
                }
            }

            @Override
            public void onFailure(Call<List<Hotel>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t.fillInStackTrace());
                t.printStackTrace();
                listener.reject("Error al traer los datos del Servidor.");
            }
        });
    }

    public void identifyUser(LoginContract.Model.OnLoginUserListener listener, User user) {
        BookingApi api = retrofit.create(BookingApi.class);
        api.identifyUser(user.getEmail(), user.getPassword()).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response != null && response.body() != null) {
                    listener.onFinished(response.body());
                } else listener.onFailure("Error al validar datos.");
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t.fillInStackTrace());
                t.printStackTrace();
                listener.onFailure("Error al validar datos.");
            }
        });
    }

    public void registerUser(RegisterContract.Model.OnRegisterListener listener, User newUser) {
        BookingApi api = retrofit.create(BookingApi.class);
        api.registerUser(newUser.getName(), newUser.getSureName(), newUser.getEmail(), newUser.getPassword()).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                listener.onFinished("¡Bienvenido " + response.body().getName() + ", Inicia Sesion ahora!");
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t.fillInStackTrace());
                t.printStackTrace();
                listener.onFailure("Error al registrar nuevo usuario.");
            }
        });
    }

    public void doBookingRoom(ReservationContract.Model.OnBookingRoomListener listener, BookingRoom bookingRoom) {
        BookingApi api = retrofit.create(BookingApi.class);
        api.doBookingRoom(bookingRoom.getIdUser(),
                bookingRoom.getIdRoom(),
                bookingRoom.getNoches(),
                bookingRoom.getPrecio(),
                bookingRoom.getNumPerson(),
                bookingRoom.getDateIn(),
                bookingRoom.getDateOut()).enqueue(new Callback<BookingRoom>() {
            @Override
            public void onResponse(Call<BookingRoom> call, Response<BookingRoom> response) {
                listener.onFinished("¡Gracias, Buen Viaje!");
            }

            @Override
            public void onFailure(Call<BookingRoom> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t.fillInStackTrace());
                t.printStackTrace();
                listener.onFailure("Fallo al relizar la reserva");
            }
        });
    }
}
