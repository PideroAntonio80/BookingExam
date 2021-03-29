package com.svalero.bookingexam.feature.custom_search;

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
import com.svalero.bookingexam.BuildConfig;
import com.svalero.bookingexam.R;
import com.svalero.bookingexam.data.models.Hotel;
import com.svalero.bookingexam.feature.description.DescriptionActivity;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.HotelSearchViewHolder> {
    private ArrayList<Hotel> lstHotel;
    public static String numPers;
    public static String fechaIn;
    public static String fechaOut;

    /*Tantos elementos como objetos quiera mostrar en la fila*/
    public static class HotelSearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView fotoHotel;
        public TextView nombreHotel;
        public TextView nombreLocalidad;
        public TextView categoria;
        public TextView puntuacion;
        public TextView precio;
        public Context context;
        public LinearLayout rowList;

        public HotelSearchViewHolder(View v){
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
            intent.putExtra("num_person", SearchAdapter.numPers);
            intent.putExtra("fecha_in", SearchAdapter.fechaIn);
            intent.putExtra("fecha_out", SearchAdapter.fechaOut);
            context.startActivity(intent);
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list, parent,false);

        return new SearchAdapter.HotelSearchViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.HotelSearchViewHolder holder, int position) {
        Hotel hotel = lstHotel.get(position);
        String urlImage = BuildConfig.URL_SERVER + "images/" + hotel.getFoto() + ".png";
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
