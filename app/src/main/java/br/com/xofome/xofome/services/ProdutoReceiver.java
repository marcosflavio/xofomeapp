package br.com.xofome.xofome.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import br.com.xofome.xofome.R;
import br.com.xofome.xofome.activities.ListarProdutosActivity;
import livroandroid.lib.utils.NotificationUtil;

/**
 * Created by marcosf on 07/11/2016.
 */

public class ProdutoReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context,UpdateProductListService.class));
        Intent i = new Intent(context, ListarProdutosActivity.class);

        NotificationUtil.create(context,1,i, R.mipmap.ic_launcher,
                "Lista de produtos atualizada!", "Venha conferir!");

    }
}
