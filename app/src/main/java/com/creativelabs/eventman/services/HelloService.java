package com.creativelabs.eventman.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.ParseException;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.provider.CalendarContract;
import android.util.Log;
import android.widget.Toast;
import android.os.Process;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.creativelabs.eventman.HomeActivity;
import com.creativelabs.eventman.R;
import com.creativelabs.eventman.classes.EventItem;
import com.creativelabs.eventman.entity.EventItemEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HelloService  extends Service {

    MediaPlayer mp;
    EventItemEntity entity;

    private Timer timer;

    private static final String CHANNEL_ID = "NOTIFICATION_CHANNEL_ID";

    @Override
    public void onCreate() {
        entity = new EventItemEntity(getApplicationContext());

        timer = new Timer();
        Log.i("SERVICE", "Service Created");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();
        Log.d("SERVICE_START", "STARTED");


        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    getItems();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 2000);
        return START_STICKY;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void getItems () {
        // get all events for particular date

        LocalDateTime currentDate = LocalDateTime.now();
        Log.d("SERVICE_START", currentDate.toLocalDate().toString());

        List<EventItem> items = entity.getEventsByDte(currentDate.toLocalDate().toString());

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String text = currentDate.format(dateTimeFormatter);
        LocalDateTime parsedDateTime = LocalDateTime.parse(text, dateTimeFormatter);
        for (EventItem event : items) {

            Log.d("CURRENT_DATE", parsedDateTime + "");
            String eventDateTime = event.getStartDate() + " " + event.getStartTime();
            LocalDateTime eventActualTime = LocalDateTime.parse(eventDateTime, dateTimeFormatter);

            Log.d("EVENT_DATE", eventActualTime + "");


            long minutes = ChronoUnit.MINUTES.between(parsedDateTime, eventActualTime);
            Log.d("SERVICE_START_MIN", minutes + "");
            if (minutes == 15) {
                // generate notification;
                createNotification(event.getId(), event.getEventName());
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // We don't provide binding, so return null
        return null;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
//        mp.stop();

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

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
        notificationManager.notify((int) id, notification);
        createNotificationChannel();
    }


    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getApplicationContext().getString(R.string.channel_name);
            String description = getApplicationContext().getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; we can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getApplicationContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
