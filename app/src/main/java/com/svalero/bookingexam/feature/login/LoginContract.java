package com.svalero.bookingexam.feature.login;

import com.svalero.bookingexam.data.User;

public interface LoginContract {
    interface View {
        void successLogin(User user);
        void failureLogin(String message);
    }

    interface Presenter {
        void getUser(User user);
    }

    interface Model {
        void getUserService(LoginContract.Model.OnLoginUserListener onLoginUserListener, User user);
        interface OnLoginUserListener {
            void onFinished(User user);
            void onFailure(String error);
        }
    }
}
