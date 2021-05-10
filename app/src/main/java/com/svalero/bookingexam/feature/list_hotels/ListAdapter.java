package com.svalero.bookingexam.feature.list_hotels;

import android.content.Context;
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

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.HotelListViewHolder> {
    private ArrayList<Hotel> lstHotel;
    private float estrellasCategoria;

    public static class HotelListViewHolder extends RecyclerView.ViewHolder{

        public ImageView fotoHotel;
        public TextView nombreHotel;
        public TextView nombreLocalidad;
        public TextView puntuacion;
        public TextView precio;
        public RatingBar estrellas;
        public CardView rowList;
        public ImageView iconFav;
        public boolean push = false;

        public View view;

        public HotelListViewHolder(View v){
            super(v);
            this.view = v;
            rowList = v.findViewById(R.id.rowListCard);
            fotoHotel = (ImageView) v.findViewById(R.id.ivFoto);
            nombreHotel = (TextView) v.findViewById(R.id.tvNombre);
            nombreLocalidad = (TextView) v.findViewById(R.id.tvNombreLocalidad);
            puntuacion = (TextView) v.findViewById(R.id.tvPuntuacion);
            precio = (TextView) v.findViewById(R.id.tvPrecio);
            estrellas = (RatingBar) v.findViewById(R.id.rbEstrellas);
            iconFav = (ImageView) v.findViewById(R.id.ivIconFav);

            iconFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    push = !push;
                    if(push) {
                        iconFav.setImageResource(R.drawable.ic_row_card_list_favourite_full);
                    } else {
                        iconFav.setImageResource(R.drawable.ic_row_card_list_favourite_gap);
                    }
                }
            });
        }
    }

    public ListAdapter(ArrayList<Hotel> lstHotel) {
        this.lstHotel = lstHotel;
    }

    @NonNull
    @Override
    public HotelListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_card, parent,false);

        return new HotelListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelListViewHolder holder, int position) {
        Hotel hotel = lstHotel.get(position);
        String urlImage = BuildConfig.URL_SERVER + "images/" + hotel.getFoto() + ".png";
        Picasso.get().load(urlImage).into(holder.fotoHotel);
        holder.nombreHotel.setText(hotel.getNombre());
        holder.nombreLocalidad.setText(hotel.getNombre_localidad());
        holder.puntuacion.setText(String.valueOf(hotel.getPuntuacion()));
        holder.precio.setText(String.valueOf(hotel.getPrecio_medio()));
        estrellasCategoria = hotel.getEstrellas();
        holder.estrellas.setRating(estrellasCategoria);
        holder.view.setOnClickListener(v -> {
            Intent intent = new Intent(holder.view.getContext(), DescriptionActivity.class);
            intent.putExtra("my_hotel", hotel);
            holder.view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return lstHotel.size();
    }
}
