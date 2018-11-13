package messeger.e.messeger;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;

//import com.e.Chat_anna_and_arina.R;

public class MyService extends Service {
    NotificationManager nm;

    private NotificationManager notificationManager;
    private String sID;
    private int iID;
    private PendingIntent resultPendingIntent;

    @Override
    public void onCreate() {
        super.onCreate();
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        String msg = intent.getStringExtra("msg");
        sendNotif(msg);
        return super.onStartCommand(intent, flags, startId);
    }



    void sendNotif(String msg) {
        sID = "TESTING";
        iID = 001;

        Intent resultIntent = new Intent(this, MainActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
// Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity.class);
// Adds the Intent that starts the Activity to the top of the stack

        stackBuilder.addNextIntent(resultIntent);
        resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        NotificationCompat.Builder mbuilder = new NotificationCompat.Builder(this, sID);
        mbuilder.setSmallIcon(R.drawable.ic_launcher_background);
        mbuilder.setContentTitle("Вам новое сообщение");
        mbuilder.setDefaults(NotificationCompat.DEFAULT_ALL);
        mbuilder.setPriority(NotificationManagerCompat.IMPORTANCE_MAX);
        mbuilder.setAutoCancel(true);
        mbuilder.setContentIntent(resultPendingIntent);
        mbuilder.setContentText(msg);


        createNotificationChannel();
        notificationManager.notify(iID, mbuilder.build());
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(sID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}