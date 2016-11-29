package br.com.xofome.xofome.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import br.com.xofome.xofome.constantes.HTTP;
import br.com.xofome.xofome.model.Pedido;

/**
 * Created by marcosf on 29/11/2016.
 */

public class PedidoTask extends AsyncTask<Pedido,Integer, Integer> {

    private Context context;
    private static final String TAG = "pedidoTASK";
    private Integer status = 0;
    private Pedido pedidoRetornado;

    public PedidoTask (Context context){
        this.context = context;
    }

    @Override
    protected void onPreExecute() {

        Toast.makeText(context, "Salvando pedido...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostExecute(Integer integer) {

        if(integer.equals(200)){
            Toast.makeText(context, "Pedido criado com sucesso!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Falha na conexão!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected Integer doInBackground(Pedido... pedidos) {


        return null;
    }

    //Thread para salvar o pedido
    class SavePedido implements Runnable {
        private Pedido pedido;

        public SavePedido (Pedido pedido){
            this.pedido = pedido;
        }
        public Pedido getPedido(){
            return this.pedido;
        }

        public void run() {
            HttpURLConnection request = null;
            try {
                //A URL que enviaremos o request
                URL reqUrl = new URL(HTTP.REQUEST_SAVE_PEDIDO);
                request = (HttpURLConnection) (reqUrl.openConnection());
                Gson gson = new Gson();
                String post = gson.toJson(pedido);

                request.setDoOutput(true);
                request.setDoInput (true);
                //Adiciona o tamanho do conteudo do dados do post
                request.addRequestProperty("Content-Length", Integer.toString(post.length()));
                //Adiciona o tipo de conteudo do request
                request.setRequestMethod("POST");
                request.addRequestProperty("Content-Type", "application/json");
                request.connect();
                //Aqui é escrito os nossos dados de request
                DataOutputStream writer = new DataOutputStream(request.getOutputStream());
                //OutputStreamWriter writer = new OutputStreamWriter(request.getOutputStream());
                writer.writeBytes(post);
                //writer.writeBytes(post);
                writer.flush();
                writer.close();
                status = request.getResponseCode();

            } catch (MalformedURLException e) {
                Log.w("ErroNet", "Erro de MalFormed salvarPedido");
                e.printStackTrace();
            } catch (IOException e) {
                // Log.w("ErroNet", "Erro de IO salvarPedido");
                e.printStackTrace();
            }
            request.disconnect();
        }
    }

    //Thread para retornar um dado pedido e salvar no bd
    class ReturnPedido implements Runnable {

        public void run() {
            HttpURLConnection urlConnection = null;
            BufferedReader in = null;

            try {
                //criar o webService
                URL url = new URL(HTTP.REQUEST_FIND_PEDIDO_BY_USER_AND_STATUS +pedidoRetornado.getUsuario()+"/"+
                        pedidoRetornado.getStatus());

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

                pedidoRetornado = gson.fromJson(response,Pedido.class);


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

    //salva o pedido retornado com a lista do singleton no bd local
    //Pega o pedido retornado, seta nos itens do singleton
    //salva os itens na web
    //retorna os itens de um dado pedido e salva no baanco
    //dá clear na lista singleton
}
