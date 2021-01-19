package com.svalero.bookingexam.feature.register;

import android.os.AsyncTask;

import com.svalero.bookingexam.data.User;
import com.svalero.bookingexam.utils.Post;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class RegisterModel implements RegisterContract.Model {
    ArrayList<User> lista;
    private RegisterContract.Model.OnRegisterListener onRegisterListener;
    private String URL = "http://192.168.1.142:8090/BookingWeb/Controller";

    @Override
    public void makeRegisterWS(OnRegisterListener onRegisterListener, User user) {
        this.onRegisterListener = onRegisterListener;

        HashMap<String, String> param = new HashMap<>();
        param.put("ACTION", "USER.ADD");
        param.put("NOMBRE", String.valueOf(user.getName()));
        param.put("APELLIDOS", String.valueOf(user.getSureName()));
        param.put("EMAIL", String.valueOf(user.getEmail()));
        param.put("PASSWORD", String.valueOf(user.getPassword()));

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
                lista = User.getArrayListFromJSON(result) ;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean resp) {
            try {
                if(resp) {
                    onRegisterListener.onFinished("¡Bienvenido " + lista.get(0).getName() + ", Inicia Sesion ahora!");
                }
            } catch (Exception e) {
                onRegisterListener.onFailure("Fallo al registrarse");
            }
        }

    }
}
