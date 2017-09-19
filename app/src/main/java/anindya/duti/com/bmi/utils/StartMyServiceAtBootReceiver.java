package anindya.duti.com.bmi.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class StartMyServiceAtBootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            Intent serviceIntent = new Intent("reverie.corporation.com.bmi.utils.MyReceiver");
            context.startService(serviceIntent);
        }
    }
}
