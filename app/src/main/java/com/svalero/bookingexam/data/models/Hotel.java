package com.svalero.bookingexam.data.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Hotel {

    private static final String ID_HOTEL = "idHotel";
    private static final String NOMBRE = "nombre";
    private static final String DESCRIPTION = "descripcion";
    private static final String URL_FOTO = "url_foto";
    private static final String ID_LOCALIDAD = "id_location";
    private static final String NOMBRE_LOCALIDAD = "nombre_location";
    private static final String NUMERO_RESERVAS = "numReservas";
    private static final String PUNTUACION = "puntuacion";
    private static final String PRECIO_MEDIO = "precio_medio";
    private static final String ESTRELLAS = "estrellas";

    private int idHotel;
    private String nombre;
    private String descripcion;
    private int idLocation;
    private String foto;
    private String nombre_localidad;
    private int numeroReservas;
    private double puntuacion;
    private double precio_medio;
    private int estrellas;

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
    public String getNombre_localidad() {
        return nombre_localidad;
    }
    public void setNombre_localidad(String nombre_localidad) {
        this.nombre_localidad = nombre_localidad;
    }
    public int getNumeroReservas() {
        return numeroReservas;
    }
    public void setNumeroReservas(int numeroReservas) {
        this.numeroReservas = numeroReservas;
    }
    public double getPuntuacion() {
        return puntuacion;
    }
    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }
    public double getPrecio_medio() {
        return precio_medio;
    }
    public void setPrecio_medio(double precio_medio) {
        this.precio_medio = precio_medio;
    }
    public int getEstrellas() {
        return estrellas;
    }
    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }

    public Hotel() {
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
                hotel.setFoto(jsonObject.getString(URL_FOTO));
                hotel.setIdLocation(jsonObject.getInt(ID_LOCALIDAD));
                hotel.setNombre_localidad(jsonObject.getString(NOMBRE_LOCALIDAD));
                hotel.setNumeroReservas(jsonObject.getInt(NUMERO_RESERVAS));
                hotel.setPuntuacion(jsonObject.getDouble(PUNTUACION));
                hotel.setPrecio_medio(jsonObject.getDouble(PRECIO_MEDIO));
                hotel.setEstrellas(jsonObject.getInt(ESTRELLAS));

                list.add(hotel);

            }

        } catch (JSONException je) {
            je.printStackTrace();
        }

        return list;
    }

    public static ArrayList<Hotel> getList() {
        return list;
    }

    public static ArrayList<Hotel> getListaReservados() {
        Collections.sort(list, new Comparator<Hotel>() {
            @Override
            public int compare(Hotel h1, Hotel h2) {
                return new Integer(h2.getNumeroReservas()).compareTo(new Integer(h1.getNumeroReservas()));
            }
        });
        return list;
    }

    public static ArrayList<Hotel> getListaDestacados() {
        Collections.sort(list, new Comparator<Hotel>() {
            @Override
            public int compare(Hotel h1, Hotel h2) {
                return new Integer(h2.getEstrellas()).compareTo(new Integer(h1.getEstrellas()));
            }
        });
        return list;
    }

    public static ArrayList<Hotel> getListaPrecioAsc() {
        Collections.sort(list, new Comparator<Hotel>() {
            @Override
            public int compare(Hotel h1, Hotel h2) {
                return new Double(h1.getPrecio_medio()).compareTo(new Double(h2.getPrecio_medio()));
            }
        });
        return list;
    }

    public static ArrayList<Hotel> getListaPrecioDesc() {
        Collections.sort(list, new Comparator<Hotel>() {
            @Override
            public int compare(Hotel h1, Hotel h2) {
                return new Double(h2.getPrecio_medio()).compareTo(new Double(h1.getPrecio_medio()));
            }
        });
        return list;
    }

    public static ArrayList<Hotel> getListaPuntos() {
        Collections.sort(list, new Comparator<Hotel>() {
            @Override
            public int compare(Hotel h1, Hotel h2) {
                return new Double(h2.getPuntuacion()).compareTo(new Double(h1.getPuntuacion()));
            }
        });
        return list;
    }

}
