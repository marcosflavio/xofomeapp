package br.com.xofome.xofome.tasks;

import android.content.Context;
import android.location.Location;
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
import java.util.ArrayList;
import java.util.List;

import br.com.xofome.xofome.constantes.HTTP;
import br.com.xofome.xofome.model.ItemPedido;
import br.com.xofome.xofome.model.ItemPedidoSingleton;
import br.com.xofome.xofome.model.Pedido;
import br.com.xofome.xofome.model.Usuario;
import br.com.xofome.xofome.services.ItemPedidoService;
import br.com.xofome.xofome.services.LocationService;
import br.com.xofome.xofome.services.PedidoService;
import br.com.xofome.xofome.services.UsuarioService;

/**
 * Created by marcosf on 29/11/2016.
 */

public class PedidoTask extends AsyncTask<  Pedido,Integer, Integer> {

    private Context context;
    private static final String TAG = "pedidoTASK";
    private Integer status = 0;
    private Pedido pedidoRetornado;
    private List<ItemPedido> itensRetornados = new ArrayList<ItemPedido>();
    private LocationService locationService;

    public PedidoTask (Context context){
        this.context = context;

        locationService = new LocationService(context);
    }

    @Override
    protected void onPreExecute() {
        Toast.makeText(context, "Salvando pedido...", Toast.LENGTH_SHORT).show();

        locationService.doConnect();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        locationService.doDisconnect();
        if(integer.equals(200)){
            Toast.makeText(context, "Pedido criado com sucesso!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Falha na conexão!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected Integer doInBackground(Pedido... pedidos) {
        pedidoRetornado = pedidos[0];
        pedidoRetornado.setIdPedido(0);

        Location endereco = locationService.getLocation();
        pedidoRetornado.setLatitude(String.valueOf(endereco.getLatitude()));
        pedidoRetornado.setLongitude(String.valueOf(endereco.getLongitude()));

        savePedido(pedidoRetornado);

        if(status.equals(200)) {
            Log.w(TAG, "Status 200");
            retornePedido();

            ItemPedidoSingleton itemPedidoSingleton = ItemPedidoSingleton.getInstancia();
            List<ItemPedido> itens = itemPedidoSingleton.getListItens();

            //Salva o pedido no banco local com seus itens
            //pedidoRetornado.setItensPedido(itens);
            PedidoService service = new PedidoService();
            service.save(pedidoRetornado, context);

            //Pega o pedido retornado, seta nos itens do singleton
            for(int i = 0; i < itens.size(); i++){
                itens.get(i).setPedido(pedidoRetornado);

                Log.w(TAG, itens.get(i).getPedido().toString());
                Log.w(TAG, "Setando o pedido retornado");
            }

            //salva os itens na web
            for(int i = 0; i < itens.size(); i++){
                ItemPedido item = itens.get(i);
                saveItemPedido(item);
                Log.w(TAG, "Salvando os itens na web");
            }

            //retorna os itens de um dado pedido e salva no banco
            retorneItensPedido();
            //Salva os itens no bd

            for(int k = 0; k < itensRetornados.size(); k++) {
                ItemPedidoService itemService = new ItemPedidoService();
                itemService.save(itensRetornados.get(k),context);
            }

            //dá clear na lista singleton
            itemPedidoSingleton.clear();
        }

        return status;
    }

    //Salvar o pedido
    public void savePedido (Pedido pedido)
    {
            HttpURLConnection request = null;
            try {
                Log.w(TAG, "Entrei no SavePedido");
                //A URL que enviaremos o request
                URL reqUrl = new URL(HTTP.REQUEST_SAVE_PEDIDO);
                request = (HttpURLConnection) (reqUrl.openConnection());
                Gson gson = new Gson();

                UsuarioService serviceUser = new UsuarioService(context);
                Usuario u = serviceUser.getUsuario();
                pedido.setUsuario(u);
                String post = gson.toJson(pedido);

                request.setDoOutput(true);
                request.setDoInput (true);
                //Adiciona o tamanho do conteudo do dados do post
                request.addRequestProperty("Content-Length", Integer.toString(post.length()));
                //Adiciona o tipo de conteudo do request

                Log.w(TAG, ">>>>>>........." + post.toString());

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


    //Thread para retornar um dado pedido e salvar no bd
        public void retornePedido() {
            HttpURLConnection urlConnection = null;
            BufferedReader in = null;

            try {
                Log.w(TAG, "Entrei no returnPedido");

                Log.w(TAG, HTTP.REQUEST_FIND_PEDIDO_BY_USER_AND_STATUS +pedidoRetornado.getUsuario().getEmail()+"/"+
                        pedidoRetornado.getStatus());
                URL url = new URL(HTTP.REQUEST_FIND_PEDIDO_BY_USER_AND_STATUS +pedidoRetornado.getUsuario().getEmail()+"/"+
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

    //Thread para salvar o itemPedido
    public void saveItemPedido(ItemPedido itemPedido){
            HttpURLConnection request = null;
            try {
                Log.w(TAG, "Entrei no SaveItem");
                //A URL que enviaremos o request
                URL reqUrl = new URL(HTTP.REQUEST_SAVE_ITEM_PEDIDO);
                request = (HttpURLConnection) (reqUrl.openConnection());
                Gson gson = new Gson();
                String post = gson.toJson(itemPedido);

                Log.w(TAG, ">>>>" + post.toString());

                request.setDoOutput(true);
                request.setDoInput (true);

                //Adiciona o tamanho do conteudo do dados do post
                request.addRequestProperty("Content-Length", Integer.toString(post.length()));
                //Adiciona o tipo de conteudo do request
                request.setRequestMethod("POST");
                request.addRequestProperty("Content-Type", "application/json;charset=UTF-8");
                request.connect();
                //Aqui é escrito os nossos dados de request
                DataOutputStream writer = new DataOutputStream(request.getOutputStream());
                //OutputStreamWriter writer = new OutputStreamWriter(request.getOutputStream());
                writer.writeBytes(post);
                //writer.writeBytes(post);
                writer.flush();
                writer.close();
                status = request.getResponseCode();
                Log.e(TAG, "SAVE ITEM PEDIDO >>>>>" + status);

            } catch (MalformedURLException e) {
                Log.w("ErroNet", "Erro de MalFormed salvarPedido");
                e.printStackTrace();
            } catch (IOException e) {
                // Log.w("ErroNet", "Erro de IO salvarPedido");
                e.printStackTrace();
            }
            request.disconnect();
        }

    //Thread para retornar os itens de um dado pedido
        public void retorneItensPedido() {
            HttpURLConnection urlConnection = null;
            BufferedReader in = null;

            try {
                Log.w(TAG, "Entrei no ReturnItensPedido");
                URL url = new URL(HTTP.REQUEST_FIND_ITENS_BY_PEDIDO + pedidoRetornado.getIdPedido());

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
                itensRetornados = (List<ItemPedido>) gson.fromJson(response,new TypeToken<List<ItemPedido>>(){}.getType());

            } catch (MalformedURLException ex) {
                Log.e(TAG, ex.getMessage());
                ex.printStackTrace();
                Log.e(TAG, ex.toString());
            } catch (IOException ex) {
                ex.printStackTrace();
                Log.e(TAG, ex.getMessage());
                Log.e(TAG, ex.toString());
            } finally {

                urlConnection.disconnect();
                try {
                    in.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Log.e(TAG, ex.getMessage());
                    Log.e(TAG, ex.toString());
                }

            }

        }
}
