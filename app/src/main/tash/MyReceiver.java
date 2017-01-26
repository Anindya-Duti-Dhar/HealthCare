package reverie.corporation.com.bmi.utils;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver{

    NotificationManager notificationManager;

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Intent service1 = new Intent(context, MyAlarmService.class);
        context.startService(service1);

    }

}
