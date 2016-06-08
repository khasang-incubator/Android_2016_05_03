package com.khasang.pillshelper.notification;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import com.khasang.pillshelper.R;

public class NotificationHelper {

    private static final int REQUEST_CODE = 0;

    public static void scheduleNotification(Context context, Notification notification, int delay) {

        Intent notificationIntent = new Intent(context, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, REQUEST_CODE, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }

    public static Notification getNotification(Context context, String content) {
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle("Scheduled Notification");
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.red_pill);
        return builder.build();
    }

    public static void refreshNotification(Context context){
        deleteCurrentNotification(context);
        setNextNotification(context);
    }

    public static void deleteCurrentNotification(Context context){
        Intent intent = new Intent(context, NotificationPublisher.class);
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, REQUEST_CODE, intent, 0);
        alarmManager.cancel(pendingIntent);
    }

    public static void setNextNotification(Context context){
        NotificationHelper.scheduleNotification(context, NotificationHelper.getNotification(context, "Прими что нибудь"), 10000);
    }
}