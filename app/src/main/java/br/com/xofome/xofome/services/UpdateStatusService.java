package br.com.xofome.xofome.services;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by marcosf on 07/11/2016.
 */

public class UpdateStatusService extends IntentService {

    private static final String TAG = "service";

    public UpdateStatusService() {
        super("UpdateStatusService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // faz um random
        // de acordo com o random, muda o status
        // usa o durma a cada random
        //inves de criar um pedido forçadamente
        //usar o daomemory.getall que retorna só um
        // faz o mesmo que fiz na outra service, só que utilizando o ProdutoServiceMemory
        //modifica o status, usa um swipe na tela de acompanhar pedido e um alarm tbm
        //usa a técnica do onResume()
        //criar o serviceMemoryPEDIDO!!
    }

    private void durma (){
        try{
            Thread.sleep(6000);
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
