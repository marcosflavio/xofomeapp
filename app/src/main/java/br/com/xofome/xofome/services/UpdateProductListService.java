package br.com.xofome.xofome.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

import br.com.xofome.xofome.model.Produto;

/**
 * Created by marcosf on 07/11/2016.
 */

public class UpdateProductListService extends IntentService {

    private static final String TAG = "service";

    public UpdateProductListService() {
        super("UpdateProductListService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG,"Entrei no onHandleIntent");
        List<Produto> produtos = produtosVindosDoServerStub();

        ProdutoService.setListProdutos(produtos, this);

        sendBroadcast(new Intent("Update_complete"));
    }

    private List<Produto> produtosVindosDoServerStub(){

        Log.d(TAG,"Entrei no produtosVindosDoServerStub");

        List<Produto> produtos = new ArrayList<>();

        for(int i = 0; i < 3; i ++){

            Log.d(TAG,"Entrei no primeiro for");
            produtos.add(new Produto("Produto " +i, 22f, "Descrição " + i, 0));
            durma();
        }

        for(int j = 0; j < 3; j ++){
            Log.d(TAG,"Entrei no segundo for");

            produtos.add(new Produto("Produto " +j, 22f, "Descrição " + j, 1));
            durma();
        }

        return produtos;
    }

    private void durma (){
        try{
            Log.d(TAG,"Entrei na thread durma");

            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"Service List produtos onDestroy");
    }
}
