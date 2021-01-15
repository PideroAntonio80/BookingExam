package com.svalero.bookingexam.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class User {
    private static final String ID_USER = "idUser";
    private static final String NOMBRE = "nombre";
    private static final String APELLIDOS = "apellidos";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    private int id;
    private String name;
    private String sureName;
    private String email;
    private String password;

    private static ArrayList<User> list;

    public User(int id, String name, String sureName, String email, String password) {
        this.id = id;
        this.name = name;
        this.sureName = sureName;
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSureName() {
        return sureName;
    }
    public void setSureName(String sureName) {
        this.sureName = sureName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public static ArrayList<User> getArrayListFromJSON(JSONArray listUsers) {
        list = null;
        try {
            if(listUsers != null && listUsers.length() > 0) {
                list = new ArrayList<User>();
            }

            for (int i = 0; i < listUsers.length(); i++) {
                JSONObject jsonObject = listUsers.getJSONObject(i);
                User user = new User();

                user.setId(jsonObject.getInt(ID_USER));
                user.setName(jsonObject.getString(NOMBRE));
                user.setSureName(jsonObject.getString(APELLIDOS));
                user.setEmail(jsonObject.getString(EMAIL));
                user.setPassword(jsonObject.getString(PASSWORD));

                list.add(user);

            }

        } catch (JSONException je) {
            je.printStackTrace();
        }

        return list;
    }
}
