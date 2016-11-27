package br.com.xofome.xofome.tasks;
import android.content.Context;
import android.os.AsyncTask;

import br.com.xofome.xofome.dao.ProdutoDAO;

/**
 * Created by marcosf on 27/11/2016.
 */

public class CompareDBTask extends AsyncTask<Integer,Integer, Integer> {

    private Context context;

    public CompareDBTask(Context context){
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
    }

    @Override
    protected Integer doInBackground(Integer... integers) {
        int atualiza = integers[0];
        ProdutoDAO dao = new ProdutoDAO(context);
        return null;
    }
}
