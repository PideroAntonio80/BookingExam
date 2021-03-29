package com.svalero.bookingexam.feature.room;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.bookingexam.R;
import com.svalero.bookingexam.data.models.Room;
import com.svalero.bookingexam.feature.login.LoginActivity;

import java.util.ArrayList;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {
    private ArrayList<Room> listRooms;
    public static String numPers;
    public static String fechaIn;
    public static String fechaOut;

    /*Tantos elementos como objetos quiera mostrar en la fila*/
    public static class RoomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView numeroRoom;
        public TextView nombreHotel;
        public TextView nombreLocalidad;
        public TextView capacidad;
        public TextView precio;
        public Context context;
        public LinearLayout rowRoom;

        public RoomViewHolder(View v){
            super(v);
            context = v.getContext();
            rowRoom = v.findViewById(R.id.rowRoom);
            nombreHotel = (TextView) v.findViewById(R.id.tvNombreHotelRoom);
            nombreLocalidad = (TextView) v.findViewById(R.id.tvNombreLocalidadRoom);
            numeroRoom = (TextView) v.findViewById(R.id.tvNumeroRoom);
            capacidad = (TextView) v.findViewById(R.id.tvCapacidad);
            precio = (TextView) v.findViewById(R.id.tvPrecioRoom);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, LoginActivity.class);
            intent.putExtra("room_id", String.valueOf(numeroRoom.getText()));
            intent.putExtra("nombre_hotel", nombreHotel.getText());
            intent.putExtra("nombre_localidad", nombreLocalidad.getText());
            intent.putExtra("num_person", RoomAdapter.numPers);
            intent.putExtra("fecha_in", RoomAdapter.fechaIn);
            intent.putExtra("fecha_out", RoomAdapter.fechaOut);
            intent.putExtra("precio", precio.getText());
            intent.putExtra("option", "fromRoomAdapter");
            context.startActivity(intent);
        }

    }

    public RoomAdapter(ArrayList<Room> listRooms, String numPers, String fechaIn, String fechaOut) {
        this.listRooms = listRooms;
        RoomAdapter.numPers = numPers;
        RoomAdapter.fechaIn = fechaIn;
        RoomAdapter.fechaOut = fechaOut;
    }

    @NonNull
    @Override
    public RoomAdapter.RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_room, parent,false);

        return new RoomAdapter.RoomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomAdapter.RoomViewHolder holder, int position) {
        Room room = listRooms.get(position);
        /*String urlImage = BuildConfig.URL_SERVER + "images/" + hotel.getFoto() + ".png";
        Picasso.get().load(urlImage).into(holder.fotoHotel);*/
        holder.nombreHotel.setText(room.getNombreHotel());
        holder.nombreLocalidad.setText(room.getNombreLocalidad());
        holder.numeroRoom.setText(String.valueOf(room.getIdRoom()));
        holder.capacidad.setText(String.valueOf(room.getCapacidad()));
        holder.precio.setText(String.valueOf(room.getPrecio()));
    }

    @Override
    public int getItemCount() {
        return listRooms.size();
    }
}
