package com.svalero.bookingexam.data.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Location {

    private static final String ID_LOCATION = "idLocation";
    private static final String NOMBRE = "nombre";

    private int idLocation;
    private String nombre;
    private static ArrayList<Location> list;

    public int getIdLocation() {
        return idLocation;
    }
    public void setIdLocation(int idLocation) {
        this.idLocation = idLocation;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static ArrayList<Location> getArrayListFromJSON(JSONArray listLocations) {
        list = null;
        try {
            if(listLocations != null && listLocations.length() > 0) {
                list = new ArrayList<Location>();
            }

            for (int i = 0; i < listLocations.length(); i++) {
                JSONObject jsonObject = listLocations.getJSONObject(i);
               Location location = new Location();

                location.setIdLocation(jsonObject.getInt(ID_LOCATION));
                location.setNombre(jsonObject.getString(NOMBRE));

                list.add(location);

            }

        } catch (JSONException je) {
            je.printStackTrace();
        }

        return list;
    }
}
