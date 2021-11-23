package com.creativelabs.eventman.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.ParseException;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.creativelabs.eventman.HomeActivity;
import com.creativelabs.eventman.R;

public class ForegroundService extends Service {

    private static final String CHANNEL_ID = "NOTIFICATION_CHANNEL_ID_2";

    @Override
    public void onCreate() {
        Log.i("SERVICE", "Service Created");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("SERVICE_START", "STARTED");

        createNotification(123, "'Notification'");
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {

        stopForeground(false);
        Log.d("SERVICE_START", "STOPPED");
    }

    private void createNotification(long id, String eventName) {

        Log.d("SERVICE_NOTIFICATION", "START");
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), (int) id,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);


        Notification notification = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_account_circle_24)
                .setContentTitle("Remainder!")
                .setContentText("You have an event in 15 minutes : " +  eventName)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setLights(Color.argb(255, 234, 146, 21), 1000, 10000)
                .setContentIntent(pendingIntent)
                .build();

//        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
//        notificationManager.notify((int) id, notification);
        createNotificationChannel();
        startForeground((int) id, notification);
    }


    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getApplicationContext().getString(R.string.channel_name);
            String description = getApplicationContext().getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; we can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getApplicationContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


}
