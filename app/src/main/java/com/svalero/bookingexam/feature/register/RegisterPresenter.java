package com.svalero.bookingexam.feature.register;

import com.svalero.bookingexam.data.User;

public class RegisterPresenter implements RegisterContract.Presenter {
    private RegisterContract.View vista;
    private RegisterModel registerModel;

    public RegisterPresenter(RegisterContract.View vista) {
        this.vista = vista;
        this.registerModel = new RegisterModel();
    }

    @Override
    public void makeRegister(User user) {
        this.registerModel.makeRegisterWS(new RegisterContract.Model.OnRegisterListener() {
            @Override
            public void onFinished(String message) {
                vista.success(message);
            }

            @Override
            public void onFailure(String error) {
                vista.failure(error);
            }
        }, user);
    }
}
