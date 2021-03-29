package com.svalero.bookingexam.feature.login;

import com.svalero.bookingexam.data.models.User;

public interface LoginContract {
    interface View {
        void successLogin(User user);
        void failureLogin(String message);
    }

    interface Presenter {
        void getUser(User user);
    }

    interface Model {
        /*Programación Reactiva*/
        interface OnLoginUserListener {
            void onFinished(User user);
            void onFailure(String error);
        }
        void getUserService(OnLoginUserListener onLoginUserListener, User user);
    }
}
