package com.svalero.bookingexam.feature.filter;

import android.content.Context;
import android.content.Intent;
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

public class FilterAdapter  extends RecyclerView.Adapter<FilterAdapter.FilterViewHolder>{
    private ArrayList<Hotel> lstHotelsFilter;

    public static class FilterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView ivFoto;
        public TextView nombreHotel;
        public TextView nombreLocalidad;
        public TextView categoria;
        public TextView puntuacion;
        public TextView precio;
        public Context context;
        public LinearLayout row;

        public FilterViewHolder(View v) {
            super(v);
            context = v.getContext();
            row = v.findViewById(R.id.rowList);
            ivFoto = (ImageView) v.findViewById(R.id.ivFoto);
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
            context.startActivity(intent);
        }
    }

    public FilterAdapter(ArrayList<Hotel> lstHotelsFilter) {
        this.lstHotelsFilter = lstHotelsFilter;
    }
    @NonNull
    @Override
    public FilterAdapter.FilterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list, parent,false);
        return new FilterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterAdapter.FilterViewHolder holder, int position) {
        Hotel hotel = lstHotelsFilter.get(position);
        String urlImage = "http://192.168.1.142:8090/BookingWeb/images/" + hotel.getFoto() + ".png";
        Picasso.get().load(urlImage).into(holder.ivFoto);
        holder.nombreHotel.setText(hotel.getNombre());
        holder.nombreLocalidad.setText(hotel.getNombre_localidad());
        holder.categoria.setText(String.valueOf(hotel.getEstrellas()));
        holder.puntuacion.setText(String.valueOf(hotel.getPuntuacion()));
        holder.precio.setText(String.valueOf(hotel.getPrecio_medio()));
    }

    @Override
    public int getItemCount() {
        return lstHotelsFilter.size();
    }
}
