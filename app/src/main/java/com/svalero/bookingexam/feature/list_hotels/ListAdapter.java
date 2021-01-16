package com.svalero.bookingexam.feature.list_hotels;

import android.content.Context;
import android.content.Intent;
import android.util.EventLogTags;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.svalero.bookingexam.R;
import com.svalero.bookingexam.data.Hotel;
import com.svalero.bookingexam.feature.description.DescriptionActivity;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.HotelListViewHolder> {
    private ArrayList<Hotel> lstHotel;

    /*Tantos elementos como objetos quiera mostrar en la fila*/
    public static class HotelListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView fotoHotel;
        public TextView nombreHotel;
        public TextView nombreLocalidad;
        public TextView categoria;
        public TextView puntuacion;
        public TextView precio;
        public Context context;
        public LinearLayout rowList;

        public HotelListViewHolder(View v){
            super(v);
            context = v.getContext();
            rowList = v.findViewById(R.id.rowList);
            fotoHotel = (ImageView) v.findViewById(R.id.ivFoto);
            nombreHotel = (TextView) v.findViewById(R.id.tvNombre);
            nombreLocalidad = (TextView) v.findViewById(R.id.tvNombreLocalidad);
            categoria = (TextView) v.findViewById(R.id.tvEstrellas);
            puntuacion = (TextView) v.findViewById(R.id.tvPuntuacion);
            precio = (TextView) v.findViewById(R.id.tvPrecio);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, DescriptionActivity.class);
            intent.putExtra("nombre_hotel", nombreHotel.getText());
            intent.putExtra("nombre_localidad", nombreLocalidad.getText());
            context.startActivity(intent);
        }
    }

    public ListAdapter(ArrayList<Hotel> lstHotel) {
        this.lstHotel = lstHotel;
    }

    @NonNull
    @Override
    public HotelListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list, parent,false);

        return new HotelListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelListViewHolder holder, int position) {
        Hotel hotel = lstHotel.get(position);
        String urlImage = "http://192.168.1.142:8090/BookingWeb/images/" + hotel.getFoto() + ".png";
        Picasso.get().load(urlImage).into(holder.fotoHotel);
        holder.nombreHotel.setText(hotel.getNombre());
        holder.nombreLocalidad.setText(hotel.getNombre_localidad());
        holder.categoria.setText(String.valueOf(hotel.getEstrellas()));
        holder.puntuacion.setText(String.valueOf(hotel.getPuntuacion()));
        holder.precio.setText(String.valueOf(hotel.getPrecio_medio()));

    }

    @Override
    public int getItemCount() {
        return lstHotel.size();
    }
}
