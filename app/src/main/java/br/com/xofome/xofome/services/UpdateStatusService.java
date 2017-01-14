package br.com.xofome.xofome.services;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.xofome.xofome.constantes.HTTP;
import br.com.xofome.xofome.model.Pedido;
import br.com.xofome.xofome.model.Usuario;

/**
 * Created by marcosf on 07/11/2016.
 */

public class UpdateStatusService extends IntentService {

    private static final String TAG = "updateStatusService";
    private List<Pedido> pedidos = new ArrayList<Pedido>();
    private Integer status;
    public UpdateStatusService() {
        super("UpdateStatusService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        update();
        if(status.equals(200)) {

            List<Pedido> auxiliar = pedidos;

            for(int i = 0; i < auxiliar.size(); i++) {

                PedidoService.update(auxiliar.get(i),getApplicationContext());
            }

            sendBroadcast(new Intent("Update_status_complete"));
            stopSelf();
        }else {
            sendBroadcast(new Intent("Update_status_erro"));
            stopSelf();
        }

    }

    // método para atualizar os status dos pedidos
    private void update(){
        HttpURLConnection urlConnection = null;
        BufferedReader in = null;

        try {
            UsuarioService usuarioService = new UsuarioService(getApplicationContext());
            Usuario u =  usuarioService.getUsuario();
            URL url = new URL(HTTP.REQUEST_UPDATE_STATUS_PEDIDO+String.valueOf(u.getEmail()));
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
                    Gson gson = new Gson();

                    pedidos = (List<Pedido>) gson.fromJson(response,new TypeToken<List<Pedido>>(){}.getType());
                    status = urlConnection.getResponseCode();
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



