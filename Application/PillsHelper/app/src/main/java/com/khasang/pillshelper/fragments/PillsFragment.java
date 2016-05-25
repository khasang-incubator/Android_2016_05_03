package com.khasang.pillshelper.fragments;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.khasang.pillshelper.MainActivity;
import com.khasang.pillshelper.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PillsFragment extends Fragment implements TextWatcher {

    private AutoCompleteTextView mAutoComplete;
    private TextView textView;

    final String[] mContacts = { "Analgin", "Anareksin", "Anaprolon", "Paracetamol", "Aspirin", "Creatin",
            "Prostamol", "Viagra" };

    public PillsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pills, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView = (TextView)getActivity().findViewById(R.id.tv_pills_frag);

        mAutoComplete = (AutoCompleteTextView) getActivity().findViewById (R.id.autoCompleteTextView);
        mAutoComplete.addTextChangedListener(this);
        mAutoComplete.setAdapter(new ArrayAdapter(getActivity(),
                android.R.layout.simple_dropdown_item_1line, mContacts));
        mAutoComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        textView.setText(mAutoComplete.getText());

    }



}
