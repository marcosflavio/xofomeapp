package br.com.xofome.xofome.services;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by marcosf on 07/11/2016.
 */

public class UpdateStatusService extends IntentService {


    public UpdateStatusService() {
        super("UpdateStatusService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {


    }
}
