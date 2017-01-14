package br.com.xofome.xofome.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import br.com.xofome.xofome.constantes.HTTP;
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
        if(integer.equals(200)){
            Toast.makeText(context, "Usuário salvo com sucesso!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Falha na conexão!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPreExecute() {
        Toast.makeText(context, "Salvando usuário...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Integer doInBackground(Usuario... usuarios) {
        Integer status = 0;
        HttpURLConnection request = null;
        try {
            Usuario u = usuarios[0];
            //A URL que enviaremos o request
            URL reqUrl = new URL(HTTP.REQUEST_SAVE_USER);
            request = (HttpURLConnection) (reqUrl.openConnection());
            Gson gson = new Gson();
            String post = gson.toJson(u);

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
        return status;
    }
}
