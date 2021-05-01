package com.svalero.bookingexam.feature.list_booked;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.svalero.bookingexam.BuildConfig;
import com.svalero.bookingexam.R;
import com.svalero.bookingexam.data.Hotel;
import com.svalero.bookingexam.feature.description.DescriptionActivity;

import java.util.ArrayList;

public class ListAdapterBook extends RecyclerView.Adapter<com.svalero.bookingexam.feature.list_booked.ListAdapterBook.BookViewHolder> {
    private ArrayList<Hotel> lstHotel;

    /*Tantos elementos como objetos quiera mostrar en la fila*/
    public static class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView fotoHotel;
        public TextView nombreHotel;
        public TextView nombreLocalidad;
        public TextView reservas;
        public Context context;
        public CardView rowList;

        public BookViewHolder(View v){
            super(v);
            context = v.getContext();
            rowList = v.findViewById(R.id.rowBookHotelsListCard);
            fotoHotel = (ImageView) v.findViewById(R.id.ivFotoBook);
            nombreHotel = (TextView) v.findViewById(R.id.tvNombre);
            nombreLocalidad = (TextView) v.findViewById(R.id.tvNombreLocalidad);
            reservas = (TextView) v.findViewById(R.id.tvEstrellas);

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, DescriptionActivity.class);
            intent.putExtra("nombre_hotel", nombreHotel.getText());
            context.startActivity(intent);
        }
    }

    public ListAdapterBook(ArrayList<Hotel> lstHotel) {
        this.lstHotel = lstHotel;
    }

    @NonNull
    @Override
    public com.svalero.bookingexam.feature.list_booked.ListAdapterBook.BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_book_hotels_list_card, parent,false);

        return new com.svalero.bookingexam.feature.list_booked.ListAdapterBook.BookViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull com.svalero.bookingexam.feature.list_booked.ListAdapterBook.BookViewHolder holder, int position) {
        Hotel hotel = lstHotel.get(position);
        String urlImage = BuildConfig.URL_SERVER + "images/" + hotel.getFoto() + ".png";
        Picasso.get().load(urlImage).into(holder.fotoHotel);
        holder.nombreHotel.setText(hotel.getNombre());
        holder.nombreLocalidad.setText(hotel.getNombre_localidad());
        holder.reservas.setText(String.valueOf(hotel.getNumeroReservas()));

    }

    @Override
    public int getItemCount() {
        return lstHotel.size();
    }
}
