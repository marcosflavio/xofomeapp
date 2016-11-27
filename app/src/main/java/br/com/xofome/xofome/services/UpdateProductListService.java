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
    private Boolean atualizar = false;
    public UpdateProductListService() {
        super("UpdateProductListService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        List<Produto> produtos = new ArrayList<>();

        //chamo o CompareDBTask.execute(atualizar)
        //se atualizar for verdadeiro, chamo o Updalist.execute(produtos)

        //o resto continua da mesma forma
        ProdutoService.setListProdutos(produtos, this);

        sendBroadcast(new Intent("Update_complete"));
        stopSelf();
    }
}
