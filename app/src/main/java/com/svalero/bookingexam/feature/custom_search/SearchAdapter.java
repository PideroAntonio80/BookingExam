package com.svalero.bookingexam.feature.custom_search;

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

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.HotelSearchViewHolder> {
    private ArrayList<Hotel> lstHotel;
    private float estrellasCategoria;
    public static String numPers;
    public static String fechaIn;
    public static String fechaOut;

    public static class HotelSearchViewHolder extends RecyclerView.ViewHolder {

        public ImageView fotoHotel;
        public TextView nombreHotel;
        public TextView nombreLocalidad;
        public TextView puntuacion;
        public TextView precio;
        public RatingBar estrellas;
        public CardView rowListCard;
        public ImageView iconFav;
        public boolean push = false;

        public View view;

        public HotelSearchViewHolder(View v){
            super(v);
            this.view = v;
            rowListCard = v.findViewById(R.id.rowListCard);
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

    public SearchAdapter(ArrayList<Hotel> lstHotel, String numPers, String fechaIn, String fechaOut) {
        this.lstHotel = lstHotel;
        SearchAdapter.numPers = numPers;
        SearchAdapter.fechaIn = fechaIn;
        SearchAdapter.fechaOut = fechaOut;
    }

    @NonNull
    @Override
    public SearchAdapter.HotelSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_card, parent,false);

        return new SearchAdapter.HotelSearchViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.HotelSearchViewHolder holder, int position) {
        Hotel hotel = lstHotel.get(position);
        String urlImage = BuildConfig.URL_SERVER + "images/" + hotel.getFoto() + ".png";
        Picasso.get().load(urlImage).into(holder.fotoHotel);
        holder.nombreHotel.setText(hotel.getNombre());
        holder.nombreLocalidad.setText(hotel.getNombre_localidad());
        holder.puntuacion.setText(String.valueOf(hotel.getPuntuacion()));
        holder.precio.setText(String.valueOf(hotel.getPrecio_medio()));
        estrellasCategoria = hotel.getEstrellas();
        holder.estrellas.setRating(estrellasCategoria);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.view.getContext(), DescriptionActivity.class);
                intent.putExtra("my_hotel", hotel);
                intent.putExtra("num_person", SearchAdapter.numPers);
                intent.putExtra("fecha_in", SearchAdapter.fechaIn);
                intent.putExtra("fecha_out", SearchAdapter.fechaOut);
                holder.view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstHotel.size();
    }
}
