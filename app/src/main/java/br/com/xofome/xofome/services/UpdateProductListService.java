package br.com.xofome.xofome.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.com.xofome.xofome.constantes.HTTP;
import br.com.xofome.xofome.dao.ProdutoDAO;
import br.com.xofome.xofome.model.Produto;
import br.com.xofome.xofome.tasks.CompareDBTask;

/**
 * Created by marcosf on 07/11/2016.
 */

public class UpdateProductListService extends IntentService {

    private static final String TAG = "service";
    private long atualizar = 0;
    private long count = 0;

    public UpdateProductListService() {
        super("UpdateProductListService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        List<Produto> produtos = new ArrayList<>();

        //chamo o CompareDBTask.execute(atualizar)
        ProdutoDAO dao = new ProdutoDAO(this.getApplicationContext());
        atualizar = dao.getTaskCount();
        BuscaCountBD buscaCountBD = new BuscaCountBD();
        buscaCountBD.run();
        if (atualizar < count) {
            Log.w(TAG, "A lista precisa ser atualizada!!");

            atualizar = count - atualizar;


            sendBroadcast(new Intent("Update_complete"));
            stopSelf();
            //chamo o método que atualiza o coiso.
        } else {
            Log.w(TAG, "A lista não precisa ser atualizada!!");
            sendBroadcast(new Intent("Update_complete"));
            stopSelf();
        }
    }

    public void getCountFromWeb() {

    }

    //Thread que faz a requisição via http do tamanho do bd
    class BuscaCountBD implements Runnable {
        public void run() {
            HttpURLConnection urlConnection = null;
            BufferedReader in = null;

            try {
                URL url = new URL(HTTP.REQUEST_COMPARE_BD);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoOutput(false);
                urlConnection.setRequestMethod("GET");

                in = new BufferedReader(new InputStreamReader(
                        urlConnection.getInputStream()));

                String response = "";

                String inputLine;

                while ((inputLine = in.readLine()) != null)
                    response += inputLine;

                Log.e(TAG, "Resposta >>>>>> " + response);
                count = Long.valueOf(response);
                Log.e(TAG, "Count >>>>>> " + count);

            } catch (MalformedURLException ex) {
                Log.e(TAG, ex.getMessage());
                Log.e(TAG, ex.toString());
            } catch (IOException ex) {
                Log.e(TAG, ex.getMessage());
                Log.e(TAG, ex.toString());
            } finally {

                urlConnection.disconnect();
                try {
                    in.close();
                } catch (IOException ex) {
                    Log.e(TAG, ex.getMessage());
                    Log.e(TAG, ex.toString());
                }

            }

        }
    }
    //thread que atualiza a lista de acordo com os produtos que precisam ser atualizados
    class updateList implements Runnable {
        public void run() {
            HttpURLConnection urlConnection = null;
            BufferedReader in = null;

            try {
                URL url = new URL(HTTP.REQUEST_UPDATE_BD);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoOutput(false);
                urlConnection.setRequestMethod("GET");

                in = new BufferedReader(new InputStreamReader(
                        urlConnection.getInputStream()));

                String response = "";

                String inputLine;

                while ((inputLine = in.readLine()) != null)
                    response += inputLine;

                Log.e(TAG, "Resposta >>>>>> " + response);
                count = Long.valueOf(response);
                Log.e(TAG, "Count >>>>>> " + count);

            } catch (MalformedURLException ex) {
                Log.e(TAG, ex.getMessage());
                Log.e(TAG, ex.toString());
            } catch (IOException ex) {
                Log.e(TAG, ex.getMessage());
                Log.e(TAG, ex.toString());
            } finally {

                urlConnection.disconnect();
                try {
                    in.close();
                } catch (IOException ex) {
                    Log.e(TAG, ex.getMessage());
                    Log.e(TAG, ex.toString());
                }

            }

        }
    }
}
