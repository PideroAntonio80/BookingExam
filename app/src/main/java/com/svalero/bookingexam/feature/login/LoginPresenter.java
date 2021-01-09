package com.svalero.bookingexam.feature.login;

import com.svalero.bookingexam.data.User;

public class LoginPresenter implements LoginContract.Presenter{
    private LoginContract.View vista;
    private LoginModel model;

    public LoginPresenter(LoginContract.View vista) {
        this.vista = vista;
        this.model = new LoginModel();
    }

    @Override
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
    }
}
