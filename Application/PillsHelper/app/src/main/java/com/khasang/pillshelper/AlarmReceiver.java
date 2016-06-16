package com.khasang.pillshelper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Mike on 16.06.2016.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Дзинь-дзинь! Пора пить пилюлю!",
                Toast.LENGTH_LONG).show();
    }
}
