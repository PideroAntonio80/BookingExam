package com.svalero.bookingexam.data.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class BookingRoom {

    private static final String ID_ROOM = "idRoom";

    private int idBookinRoom, numPerson, idRoom, idUser, noches;
    private Date dateIn, dateOut;
    private double precio;

    public BookingRoom() {
    }
    public int getIdUser() {
        return idUser;
    }
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    public int getIdBookinRoom() {
        return idBookinRoom;
    }
    public void setIdBookinRoom(int idBookinRoom) {
        this.idBookinRoom = idBookinRoom;
    }
    public int getNumPerson() {
        return numPerson;
    }
    public void setNumPerson(int numPerson) {
        this.numPerson = numPerson;
    }
    public int getIdRoom() {
        return idRoom;
    }
    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }
    public Date getDateIn() {
        return dateIn;
    }
    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }
    public Date getDateOut() {
        return dateOut;
    }
    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }
    public int getNoches() {
        return noches;
    }
    public void setNoches(int noches) {
        this.noches = noches;
        //this.noches = (int) ((dateOut.getTime() - dateIn.getTime()) / 86400000);
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public static ArrayList<BookingRoom> getArrayFromJSON (JSONArray array) {
        ArrayList<BookingRoom> list = null;
        if(array != null && array.length() > 0) {
            list = new ArrayList<BookingRoom>();
        }
        for (int i = 0; i < list.size() ; i++) {
            try {
                JSONObject jsonObject = array.getJSONObject(i);
                BookingRoom bookingRoom = new BookingRoom();

                bookingRoom.setIdRoom(jsonObject.getInt(ID_ROOM));

                list.add(bookingRoom);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return list;
    }

}
