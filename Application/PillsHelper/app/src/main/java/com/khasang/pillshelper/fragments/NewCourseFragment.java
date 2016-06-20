package com.khasang.pillshelper.fragments;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.khasang.pillshelper.AlarmReceiver;
import com.khasang.pillshelper.PillsCourseService;
import com.khasang.pillshelper.R;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewCourseFragment extends Fragment {

    final static int RQS_TIME = 1;
    private TextView mTimeTextView;

    TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener(){
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Calendar calNow = Calendar.getInstance();
            Calendar calSet = (Calendar) calNow.clone();

            calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calSet.set(Calendar.MINUTE, minute);
            calSet.set(Calendar.SECOND, 0);
            calSet.set(Calendar.MILLISECOND, 0);

            if (calSet.compareTo(calNow) <= 0) {
                // Если выбранное время на сегодня прошло,
                // то переносим на завтра
                calSet.add(Calendar.DATE, 1);
            }
            setAlarm(calSet);
        }
    };



    private PendingIntent mPendingIntent;

    public NewCourseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_course, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTimeTextView = (TextView)getActivity().findViewById(R.id.textViewAlarmPrompt);

        Button openTimeDialogButton = (Button)getActivity().findViewById(R.id.butttonShowDialog);
        openTimeDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTimeTextView.setText("");
                openTimePickerDialog(true);
            }
        });

        // Кнопка отмены сигнализации
        Button cancelButton = (Button)getActivity().findViewById(R.id.buttonCancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                cancelAlarm();
            }
        });
    }
    private void setAlarm(Calendar targetCal) {
        mTimeTextView.setText("Сигнализация установлена на ");
        mTimeTextView.append(String.valueOf(targetCal.getTime()));

        Intent intent = new Intent(getActivity(), PillsCourseService.class);
        intent.putExtra(PillsCourseService.EXTRA_MESSAGE,
                getResources().getString(R.string.button_respons));
        getActivity().startService(intent);
    }



    private void cancelAlarm() {
        mTimeTextView.setText("Сигнализация отменена!");

        Intent intent = new Intent(getActivity(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getActivity(), RQS_TIME, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }

    private void openTimePickerDialog(boolean is24h) {
        Calendar calendar = Calendar.getInstance();

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), onTimeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), is24h);
        timePickerDialog.setTitle("Выберите время");
        timePickerDialog.show();
    }
}
