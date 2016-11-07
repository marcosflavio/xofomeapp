package br.com.xofome.xofome.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by marcosf on 07/11/2016.
 */

public class ProdutoReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context,UpdateProductListService.class));
    }
}
