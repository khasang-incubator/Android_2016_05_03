package com.khasang.pillshelper.notification;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.khasang.pillshelper.R;
import com.khasang.pillshelper.db.model.Course;

import org.joda.time.LocalDateTime;

import java.util.Collections;
import java.util.List;

public class NotificationHelper {

    private static final int REQUEST_CODE = 0;

    public static void init(Context context){
        refreshNotification(context);
        Job.start(context);
    }

    public static void refreshNotification(Context context){
        deleteCurrentNotification(context);
        scheduleNextNotification(context);
    }

    private static void scheduleNotification(Context context, Course.Adoption adoption) {

        Notification notification = getNotification(context, adoption);
        Intent notificationIntent = new Intent(context, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, REQUEST_CODE, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        long timestamp = adoption.timestamp.toDateTime().toInstant().getMillis();
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, timestamp, pendingIntent);
    }

    private static Notification getNotification(Context context, Course.Adoption adoption) {
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle("Pills Helper");
        builder.setContentText("Примите лекарство: " + adoption.drug.getName());
        builder.setSmallIcon(R.drawable.red_pill);
        return builder.build();
    }

    private static void deleteCurrentNotification(Context context){
        Intent intent = new Intent(context, NotificationPublisher.class);
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, REQUEST_CODE, intent, 0);
        alarmManager.cancel(pendingIntent);
    }

    private static void scheduleNextNotification(Context context){
        LocalDateTime begin = LocalDateTime.now();
        LocalDateTime end = begin.plusDays(1);
        List<Course.Adoption> adoptions = Course.getAllAdoptionsByPeriod(begin, end);
        if(adoptions.size() > 1) {
            Course.Adoption adoption = Collections.min(adoptions);
            NotificationHelper.scheduleNotification(context, adoption);
        }
    }
}