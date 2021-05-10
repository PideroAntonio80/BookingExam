package com.svalero.bookingexam.feature.login;

import com.svalero.bookingexam.data.User;
import com.svalero.bookingexam.network.BookingNetwork;

public class LoginPresenter implements LoginContract.Presenter{

    private LoginContract.View vista;
    private BookingNetwork network;
    //private LoginModel model;

    public LoginPresenter(LoginContract.View vista) {
        this.vista = vista;
        this.network = new BookingNetwork();
        //this.model = new LoginModel();
    }

   @Override
    public void getUser(User user) {
        network.identifyUser(new LoginContract.Model.OnLoginUserListener() {
            @Override
            public void onFinished(User user) {
                vista.successLogin(user);
            }

            @Override
            public void onFailure(String error) {
                vista.failureLogin(error);
            }
        }, user);
    }

    /*@Override
    public void getUser(User user) {
        this.model.getUserService(new LoginContract.Model.OnLoginUserListener() {
            @Override
            public void onFinished(User user) {
                vista.successLogin(user);
            }

            @Override
            public void onFailure(String error) {
                vista.failureLogin(error);
            }
        }, user);
    }*/
}
