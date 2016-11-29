package br.com.xofome.xofome.services;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;

import java.util.Random;

/**
 * Created by marcosf on 07/11/2016.
 */

public class UpdateStatusService extends IntentService {

    private static final String TAG = "service";
    private final Random mGenerator = new Random();

    public UpdateStatusService() {
        super("UpdateStatusService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String status [] = {"Finalizado","Recebido", "Em espera", "Preparando","Pronto"," Em entrega","Finalizado"};
      //  PedidoSingleton p = PedidoSingleton.getInstancia();
       // p.setStatus(status[getRandomNumber()]);
        durma();
        sendBroadcast(new Intent("Update_status_complete"));
        stopSelf();
    }

    private int getRandomNumber() {
        return mGenerator.nextInt(6);
    }

    private void durma (){
        try{
            Thread.sleep(6000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
