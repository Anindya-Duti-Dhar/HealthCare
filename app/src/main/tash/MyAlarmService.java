package reverie.corporation.com.bmi.utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import reverie.corporation.com.bmi.MainActivity;
import reverie.corporation.com.bmi.R;


public class MyAlarmService extends Service
{
    private static final int NOTIFICATION_ID = 1;
    private NotificationManager notificationManager;
    private PendingIntent pendingIntent;

    @Override
    public IBinder onBind(Intent arg0)
    {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Context context = this.getApplicationContext();
        notificationManager = (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
        Intent mIntent = new Intent(this, MainActivity.class);
        pendingIntent = PendingIntent.getActivity(context, 0, mIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("Bananas");
        builder.setContentText("get your bananas");
        builder.setSmallIcon(R.drawable.app_icon);
        builder.setContentIntent(pendingIntent);
        builder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});

        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());

        // this was being executed but didn't really do anything
        return super.onStartCommand(intent, flags, startId);
    }
}