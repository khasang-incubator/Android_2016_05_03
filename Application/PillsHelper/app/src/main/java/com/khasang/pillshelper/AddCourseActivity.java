package com.khasang.pillshelper;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.ArrayList;

public class AddCourseActivity extends AppCompatActivity {

    private Context context;
    private String[] words = {"Аспирин", "Аскорбин", "Спазмалгон", "Асмоапо", "Апосмо", "асмамооо", "аывпыавп", "Апекууаи", "Аквсмм", "апуцйфвва"};
    private AutoCompleteTextView autoCompleteTextView;
    private RadioGroup group;
    private Spinner spinner;
    private ArrayList<CheckBox> days;
    private String[] daysArray;
    private TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        group = (RadioGroup) this.findViewById(R.id.group);
        autoCompleteTextView = (AutoCompleteTextView) this.findViewById(R.id.autoComplete);
        spinner = (Spinner) this.findViewById(R.id.spinner);
        daysArray = getResources().getStringArray(R.array.days);
        days = new ArrayList<>();
        timePicker = (TimePicker) this.findViewById(R.id.timePicker);

        timePicker.setIs24HourView(true);

        for (int i = 0; i < 7; i++){
            CheckBox box = new CheckBox(this);
            box.setText(daysArray[i]);
            days.add(box);
        }

        for (CheckBox day : days){
            group.addView(day);
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0){
                    for (CheckBox day : days){
                        day.setChecked(true);
                        day.setClickable(false);
                    }
                }else {
                    for (CheckBox day : days){
                        day.setChecked(false);
                        day.setClickable(true);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        autoCompleteTextView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,words));
        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}

