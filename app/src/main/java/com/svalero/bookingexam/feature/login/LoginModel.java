package com.svalero.bookingexam.feature.login;

import android.os.AsyncTask;

import com.svalero.bookingexam.BuildConfig;
import com.svalero.bookingexam.data.models.User;
import com.svalero.bookingexam.utils.Post;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginModel implements LoginContract.Model {
    private ArrayList<User> listUsers;
    private OnLoginUserListener onLoginUserListener;
    private String URL = BuildConfig.URL_SERVER + "Controller";

    @Override
    public void getUserService(final OnLoginUserListener onLoginUserListener, User user) {
        this.onLoginUserListener = onLoginUserListener;
        HashMap<String, String> param = new HashMap<>();
        param.put("ACTION", "USER.LOGIN");
        param.put("EMAIL", user.getEmail());
        param.put("PASSWORD", user.getPassword());

        UnderTask underTask = new UnderTask(param);
        underTask.execute(URL);
    }

    class UnderTask extends AsyncTask<String, Integer, Boolean> {
        private HashMap<String, String> parametros = null;

        public UnderTask( HashMap<String, String> parametros) {
            super();
            this.parametros = parametros;
        }

        @Override
        protected Boolean doInBackground(String... params) {
            String url_select = params[0];
            try {
                Post post = new Post();
                JSONArray result = post.getServerDataPost(parametros,url_select);
                listUsers = User.getArrayListFromJSON(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean resp) {
            try {
                if(resp) {
                    onLoginUserListener.onFinished(listUsers.get(0));
                }
            } catch (Exception e) {
                onLoginUserListener.onFailure("Email o Password incorrectos");
            }
        }
    }
}
