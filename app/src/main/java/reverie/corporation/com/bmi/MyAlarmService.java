package reverie.corporation.com.bmi;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;


public class MyAlarmService extends Service
{
    private NotificationManager mManager;
    @Override
    public IBinder onBind(Intent arg0)
    {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public void onCreate()
    {
        // TODO Auto-generated method stub
        super.onCreate();
    }
    @SuppressWarnings("static-access")
    @Override
    public void onStart(Intent intent, int startId)
    {
        super.onStart(intent, startId);
        long when = System.currentTimeMillis();
        mManager = (NotificationManager) this.getApplicationContext().getSystemService(this.getApplicationContext().NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(this.getApplicationContext(),MainActivity.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingNotificationIntent = PendingIntent.getActivity( this.getApplicationContext(),0, intent1,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(
                this.getApplicationContext()).setSmallIcon(R.drawable.app_icon)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(getString(R.string.range_18_25))
                .setWhen(when)
                .setContentIntent(pendingNotificationIntent)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
        mManager.notify(0,mNotifyBuilder.build());

    }
    @Override
    public void onDestroy()
    {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

}