package br.com.xofome.xofome.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * Created by marcosf on 09/11/2016.
 */

public class AlarmUtil {

    //agendar o alarme pra data/hora
    public static void schedule(Context context, Intent intent, long triggerAtMillis){
        PendingIntent p = PendingIntent.getBroadcast(context,1,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarme = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarme.set(AlarmManager.RTC_WAKEUP, triggerAtMillis,p);
    }
    //agendar o alarme repetidamente
    public static void scheduleRepeat(Context context, Intent intent, long triggerMillis, long intervalMillis){
        PendingIntent p = PendingIntent.getBroadcast(context,1,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarme = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarme.setInexactRepeating(AlarmManager.RTC_WAKEUP, triggerMillis, intervalMillis, p);
    }
    //cancelar o alarme
    public static void cancel(Context context, Intent intent){
        AlarmManager alarme = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent p = PendingIntent.getBroadcast(context,1,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        alarme.cancel(p);
    }

}
