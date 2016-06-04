package com.khasang.pillshelper.fragments;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import com.khasang.pillshelper.R;
import java.util.ArrayList;


public class NewCourseFragment extends Fragment {

    private Context context;
    private String[] words = {"Аспирин", "Аскорбин", "Спазмалгон", "Асмоапо", "Апосмо", "асмамооо", "аывпыавп", "Апекууаи", "Аквсмм", "апуцйфвва"};
    private AutoCompleteTextView autoCompleteTextView;
    private RadioGroup group;
    private Spinner spinner;
    private ArrayList<CheckBox> days;
    private String[] daysArray;

    public NewCourseFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_course, container, false);

        context = view.getContext();
        group = (RadioGroup) view.findViewById(R.id.group);
        autoCompleteTextView = (AutoCompleteTextView) view.findViewById(R.id.autoComplete);
        spinner = (Spinner) view.findViewById(R.id.spinner);
        daysArray = getResources().getStringArray(R.array.days);
        days = new ArrayList<>();

        for (int i = 0; i < 7; i++){
            CheckBox box = new CheckBox(context);
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





        autoCompleteTextView.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line,words));
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



        return view;
    }


}