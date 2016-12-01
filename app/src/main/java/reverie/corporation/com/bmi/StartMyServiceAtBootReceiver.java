package reverie.corporation.com.bmi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by it-6 on 10/17/2016.
 */
public class StartMyServiceAtBootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            Intent serviceIntent = new Intent("android.bmi.r.bmiindex.MyReceiver");
            context.startService(serviceIntent);
        }
    }
}
