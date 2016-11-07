package br.com.xofome.xofome.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by marcosf on 07/11/2016.
 */

public class UpdateProductListService extends IntentService {

    public UpdateProductListService() {
        super("UpdateProductListService");
    }

    private static final int MAX = 120;
    private static final String TAG = "service";
    private boolean running;

    @Override
    protected void onHandleIntent(Intent intent) {

        running = true;
        //Método executado em uma thread implicida
        //ao terminar, chamar o stopSelf()
        int count = 0;
        while(running && count < MAX){
            teste();
            Log.d(TAG, "Service atualizar lista executando.." + count);
            count++;
        }

        Log.d(TAG,"Service fim");
    }

    private void teste (){
        try{
            //simulando
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //ao encerrar o servico, altero o running pra sair da thread
        running = false;
        Log.d(TAG,"Service List produtos onDestroy");
    }
}
