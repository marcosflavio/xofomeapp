package br.com.xofome.xofome.tasks;

import android.os.AsyncTask;

import java.util.List;

import br.com.xofome.xofome.model.Produto;

/**
 * Created by marcosf on 27/11/2016.
 */

public class UpdateListTask extends AsyncTask<List<Produto>,Integer, Void> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {

        super.onPostExecute(aVoid);
    }

    @Override
    protected Void doInBackground(List<Produto>... lists) {
        return null;
    }
}
