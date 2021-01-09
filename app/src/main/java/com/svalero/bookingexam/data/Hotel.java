package com.svalero.bookingexam.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Hotel {

    private static final String ID_HOTEL = "idHotel";
    private static final String NOMBRE = "nombre";
    private static final String DESCRIPTION = "descripcion";
    private static final String ID_LOCALIDAD = "id_location";

    private int idHotel;
    private String nombre;
    private String descripcion;
    private int idLocation;
    private String foto;
    private static ArrayList<Hotel> list;

    public int getIdHotel() {
        return idHotel;
    }
    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public int getIdLocation() {
        return idLocation;
    }
    public void setIdLocation(int idLocation) {
        this.idLocation = idLocation;
    }
    public String getFoto() {
        return foto;
    }
    public void setFoto(String foto) {
        this.foto = foto;
    }

    public static ArrayList<Hotel> getArrayListFromJSON(JSONArray listHotels) {
        list = null;
        try {
            if(listHotels != null && listHotels.length() > 0) {
                list = new ArrayList<Hotel>();
            }

            for (int i = 0; i < listHotels.length(); i++) {
                JSONObject jsonObject = listHotels.getJSONObject(i);
                Hotel hotel = new Hotel();

                hotel.setIdHotel(jsonObject.getInt(ID_HOTEL));
                hotel.setNombre(jsonObject.getString(NOMBRE));
                hotel.setDescripcion(jsonObject.getString(DESCRIPTION));
                hotel.setIdLocation(jsonObject.getInt(ID_LOCALIDAD));

                list.add(hotel);

            }

        } catch (JSONException je) {
            je.printStackTrace();
        }

        return list;
    }
}
