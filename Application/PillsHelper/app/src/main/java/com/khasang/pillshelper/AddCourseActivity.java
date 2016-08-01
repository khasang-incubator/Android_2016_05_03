package com.khasang.pillshelper;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.khasang.pillshelper.db.PillsDBHelper;
import com.khasang.pillshelper.db.model.Course;
import com.khasang.pillshelper.db.model.Drug;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddCourseActivity extends AppCompatActivity {

    private Context context;
    private AutoCompleteTextView autoCompleteTextView;
    private RadioGroup group;
    private Spinner spinner;
    private ArrayList<CheckBox> days;
    private String[] daysArray;
    private TimePicker timePicker;
    private EditText number_of_takes;
    private LinearLayout time_container;
    private static Button buttonStartDate;
    private static Button buttonEndDate;
    private static Button currentButton;
    private Button save;


    private static Drug drug;
    private static LocalDateTime startDate = LocalDateTime.now();
    private static LocalDateTime endDate = null;
    private static List<LocalTime> takingTime = new ArrayList<LocalTime>();
    private static Integer intervalInDays = 1;

    public void onClick(View v) {
        if(drug != null && startDate != null && takingTime != null && takingTime.size() > 0
                && intervalInDays != null && intervalInDays > 0) {
            Course.createCourse(drug, startDate, endDate, takingTime, intervalInDays);
            Toast.makeText(getBaseContext(), "Сохранено!", Toast.LENGTH_LONG).show();
            this.finish();
        }
        else{
            Toast.makeText(getBaseContext(), "Заполните все поля!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        buttonStartDate = (Button) findViewById(R.id.start_date);
        buttonStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new StartDatePickFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });
        buttonEndDate = (Button) findViewById(R.id.end_date);
        buttonEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new EndDatePickFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        autoCompleteTextView = (AutoCompleteTextView) this.findViewById(R.id.autoComplete);
        autoCompleteTextView.setAdapter(new ArrayAdapter<Drug>(this, android.R.layout.simple_dropdown_item_1line, PillsDBHelper.getInstance().getAllDrugs()));
        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                drug = PillsDBHelper.getInstance().getDrugByName(s.toString());
            }
        });

        time_container = (LinearLayout) findViewById(R.id.time_container);

        number_of_takes = (EditText) findViewById(R.id.number_of_takes);
        number_of_takes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("grol", s.toString());
                takingTime = new ArrayList<LocalTime>();
                time_container.removeAllViews();
                LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                int count = s.toString().equals("") ? 1: Integer.valueOf(s.toString());
                for(int i = 0; i < count; i++){
                    Button btnNew = new Button(getBaseContext());
                    btnNew.setText("08:00");
                    time_container.addView(btnNew, lParams);
                    btnNew.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Button button = (Button) v;
                            currentButton = button;
                            DialogFragment newFragment = new TimePickFragment();
                            newFragment.show(getSupportFragmentManager(), "timePicker");
                        }
                    });
                }
            }
        });
    }

    public static class TimePickFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Dialog picker = new TimePickerDialog(getActivity(), this, 8, 0, true);
            picker.setTitle(getResources().getString(R.string.choose_time));
            return picker;
        }

        @Override
        public void onStart() {
            super.onStart();
            Button nButton =  ((AlertDialog) getDialog()).getButton(DialogInterface.BUTTON_POSITIVE);
            nButton.setText(getResources().getString(R.string.ready));

        }
        @Override
        public void onTimeSet(TimePicker view, int hours, int minute) {
            LocalTime localTime = new LocalTime(hours, minute);
            currentButton.setText(localTime.toString("HH:mm"));
            if(!takingTime.contains(localTime)){
                takingTime.add(localTime);
            }
        }

    }

    public static class StartDatePickFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            Dialog picker = new DatePickerDialog(getActivity(), this, year, month, day);
            picker.setTitle("Выберите дату начала курса");
            return picker;
        }

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            LocalDateTime localDateTime = new LocalDateTime(year, monthOfYear, dayOfMonth, 0, 0);
            buttonStartDate.setText(localDateTime.toString("yyyy.MM.dd"));
            startDate = localDateTime;
        }
    }

    public static class EndDatePickFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            Dialog picker = new DatePickerDialog(getActivity(), this, year, month, day);
            picker.setTitle("Выберите дату окончания курса");
            return picker;
        }

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            LocalDateTime localDateTime = new LocalDateTime(year, monthOfYear, dayOfMonth, 0, 0);
            buttonEndDate.setText(localDateTime.toString("yyyy.MM.dd"));
            endDate = localDateTime;
        }
    }
}

