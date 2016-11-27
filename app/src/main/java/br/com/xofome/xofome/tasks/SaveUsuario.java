package br.com.xofome.xofome.tasks;

import android.content.Context;
import android.os.AsyncTask;

import br.com.xofome.xofome.model.Usuario;

/**
 * Created by marcosf on 27/11/2016.
 */

public class SaveUsuario extends AsyncTask<Usuario, Integer, Integer> {
    private Context context;

    public SaveUsuario (Context context){
        this.context = context;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Integer doInBackground(Usuario... usuarios) {
        return null;
    }
}
