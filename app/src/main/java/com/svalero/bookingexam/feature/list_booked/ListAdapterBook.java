package com.svalero.bookingexam.feature.list_booked;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
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
    private float estrellasCategoriaBooks;

    public static class BookViewHolder extends RecyclerView.ViewHolder {

        public ImageView fotoHotel;
        public TextView nombreHotel;
        public TextView nombreLocalidad;
        public RatingBar estrellasBooks;
        public TextView puntuacionBooks;
        public ImageView iconFavBooks;
        public TextView reservasBooks;
        public TextView precioBooks;
        public CardView rowList;
        public boolean pushBooks = false;

        public View view;

        public BookViewHolder(View v){
            super(v);
            this.view = v;
            rowList = v.findViewById(R.id.rowBookHotelsListCard);
            fotoHotel = (ImageView) v.findViewById(R.id.ivFotoBook);
            nombreHotel = (TextView) v.findViewById(R.id.tvNombreBook);
            nombreLocalidad = (TextView) v.findViewById(R.id.tvNombreLocalidadBook);
            estrellasBooks = (RatingBar) v.findViewById(R.id.rbEstrellasBook);
            puntuacionBooks = (TextView) v.findViewById(R.id.tvPuntuacionBook);
            reservasBooks = (TextView) v.findViewById(R.id.tvNumberBooks);
            precioBooks = (TextView) v.findViewById(R.id.tvPrecioBook);
            iconFavBooks = (ImageView) v.findViewById(R.id.ivIconFavBook);

            iconFavBooks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pushBooks = !pushBooks;
                    if(pushBooks) {
                        iconFavBooks.setImageResource(R.drawable.ic_row_card_list_favourite_full);
                    } else {
                        iconFavBooks.setImageResource(R.drawable.ic_row_card_list_favourite_gap);
                    }
                }
            });
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
        holder.puntuacionBooks.setText(String.valueOf(hotel.getPuntuacion()));
        holder.reservasBooks.setText(String.valueOf(hotel.getNumeroReservas()));
        holder.nombreLocalidad.setText(hotel.getNombre_localidad());
        holder.precioBooks.setText(String.valueOf(hotel.getPrecio_medio()));
        estrellasCategoriaBooks = hotel.getEstrellas();
        holder.estrellasBooks.setRating(estrellasCategoriaBooks);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.view.getContext(), DescriptionActivity.class);
                intent.putExtra("my_hotel", hotel);
                holder.view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstHotel.size();
    }
}
