package com.svalero.bookingexam.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Room {

    private static final String ID_ROOM = "idRoom";
    private static final String CAPACIDAD = "capacidad";
    private static final String DISPONIBLE = "disponible";
    private static final String PRECIO = "precio";
    private static final String ID_HOTEL = "hotel";
    private static final String NOMBRE_HOTEL = "nombreHotel";
    private static final String NOMBRE_LOCALIDAD = "localidad";

    private int idRoom, capacidad, idHotel;
    private double precio;
    private String disponible, nombreHotel, nombreLocalidad;

    private static ArrayList<Room> list;

    public Room(int idRoom, int capacidad, int idHotel, double precio, String disponible, String nombreHotel) {
        this.idRoom = idRoom;
        this.capacidad = capacidad;
        this.idHotel = idHotel;
        this.precio = precio;
        this.disponible = disponible;
        this.nombreHotel = nombreHotel;
    }

    public Room() {
    }

    public int getIdRoom() {
        return idRoom;
    }
    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }
    public int getCapacidad() {
        return capacidad;
    }
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    public int getIdHotel() {
        return idHotel;
    }
    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public String getDisponible() {
        return disponible;
    }
    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }
    public String getNombreHotel() {
        return nombreHotel;
    }
    public void setNombreHotel(String nombreHotel) {
        this.nombreHotel = nombreHotel;
    }
    public String getNombreLocalidad() {
        return nombreLocalidad;
    }
    public void setNombreLocalidad(String nombreLocalidad) {
        this.nombreLocalidad = nombreLocalidad;
    }

    public static ArrayList<Room> getArrayListFromJSON(JSONArray listRooms) {
        list = null;
        try {
            if(listRooms != null && listRooms.length() > 0) {
                list = new ArrayList<Room>();
            }

            for (int i = 0; i < listRooms.length(); i++) {
                JSONObject jsonObject = listRooms.getJSONObject(i);
                Room room = new Room();

                room.setIdRoom(jsonObject.getInt(ID_ROOM));
                room.setCapacidad(jsonObject.getInt(CAPACIDAD));
                room.setDisponible(jsonObject.getString(DISPONIBLE));
                room.setPrecio(jsonObject.getDouble(PRECIO));
                room.setIdHotel(jsonObject.getInt(ID_HOTEL));
                room.setNombreHotel(jsonObject.getString(NOMBRE_HOTEL));
                room.setNombreLocalidad(jsonObject.getString(NOMBRE_LOCALIDAD));

                list.add(room);
            }

        } catch (JSONException je) {
            je.printStackTrace();
        }

        return list;
    }

    public static ArrayList<Room> getList() {
        return list;
    }
}
