package com.khasang.pillshelper.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.khasang.pillshelper.db.PillsDBHelper;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

public class Job extends BroadcastReceiver {

    private static final int REQUEST_CODE = 1;

    private static Context context;

    public static void start(Context context){
        Job.context = context.getApplicationContext();
        scheduleNextRun(getStartDayByLocalDateTime(LocalDateTime.now()).plusDays(1));
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        PillsDBHelper.init(context);
        NotificationHelper.refreshNotification();
        scheduleNextRun(getStartDayByLocalDateTime(LocalDateTime.now()).plusDays(1));
    }

    private static void scheduleNextRun(LocalDateTime time){
        Intent notificationIntent = new Intent(context, Job.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, REQUEST_CODE, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        long mills = time.toDateTime().toInstant().getMillis();
        alarmManager.set(AlarmManager.RTC, mills, pendingIntent);
    }

    private static LocalDateTime getStartDayByLocalDateTime(LocalDateTime localDateTime){
        LocalDate localDate = localDateTime.toLocalDate();
        LocalTime midnight = new LocalTime(0, 0);
        return localDate.toLocalDateTime(midnight);
    }
}
