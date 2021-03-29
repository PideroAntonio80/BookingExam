package com.svalero.bookingexam.feature.register;

import com.svalero.bookingexam.data.models.User;

public interface RegisterContract {
    interface View {
        void success(String message);
        void failure(String message);
    }

    interface Presenter {
        void makeRegister(User user);
    }

    interface Model {
        interface OnRegisterListener {
            void onFinished(String message);
            void onFailure(String error);
        }
        void makeRegisterWS(RegisterContract.Model.OnRegisterListener onRegisterListener, User user);
    }
}
