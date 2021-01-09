package com.svalero.bookingexam.feature.list_hotels;

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

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.HotelListViewHolder> {
    private ArrayList<Hotel> lstHotel;

    /*Tantos elementos como objetos quiera mostrar en la fila*/
    public static class HotelListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView fotoHotel;
        public TextView nombreHotel;
        public Context context;
        public LinearLayout rowList;

        public HotelListViewHolder(View v){
            super(v);
            context = v.getContext();
            rowList = v.findViewById(R.id.rowList);
            fotoHotel = (ImageView) v.findViewById(R.id.ivFoto);
            nombreHotel = (TextView) v.findViewById(R.id.tvNombre);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // Cuando esté la clase HotelDescription, descomentar esto que sigue
            /*Intent intent = new Intent(context, HotelDescription.class);
            intent.putExtra("nombre_hotel", nombreHotel.getText());
            context.startActivity(intent);*/
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
        //Picasso.get().load(hotel.getFoto()).into(holder.fotoHotel);
        holder.nombreHotel.setText(hotel.getNombre());

    }

    @Override
    public int getItemCount() {
        return lstHotel.size();
    }
}
