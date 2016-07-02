package com.khasang.pillshelper.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khasang.pillshelper.AdoptionAdapter;
import com.khasang.pillshelper.AdoptionGroup;
import com.khasang.pillshelper.R;
import com.khasang.pillshelper.db.model.Course;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment {


    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        fillFragment(view);
        return view;
    }

    void fillFragment(View view) {
        RecyclerView mRecyclerView;
        RecyclerView.LayoutManager mLayoutManager;
        AdoptionAdapter mAdapter;

        mRecyclerView = (RecyclerView) view.findViewById(R.id.courses_view);

        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        List<AdoptionGroup> groups = getData();

        mAdapter = new AdoptionAdapter(getActivity(), groups);
        mRecyclerView.setAdapter(mAdapter);
    }
    private List<AdoptionGroup> getData(){
        List<AdoptionGroup> result = new ArrayList<>();
        List<Course.Adoption> todayAdoptions = Course.getAdoptionsForToday();
        List<Course.Adoption> nightAdoprions = new ArrayList<>();
        List<Course.Adoption> morningAdoprions = new ArrayList<>();
        List<Course.Adoption> afternoonAdoprions = new ArrayList<>();
        List<Course.Adoption> eveningAdoprions = new ArrayList<>();
        for(Course.Adoption adoption: todayAdoptions){
            int hourOfDay = adoption.timestamp.getHourOfDay();
            if(hourOfDay < 6){
                nightAdoprions.add(adoption);
            }else if(hourOfDay < 12){
                morningAdoprions.add(adoption);
            }else if(hourOfDay < 18){
                afternoonAdoprions.add(adoption);
            }else if(hourOfDay < 24){
                eveningAdoprions.add(adoption);
            }
        }
        result.add(new AdoptionGroup(AdoptionGroup.Type.NIGHT, nightAdoprions));
        result.add(new AdoptionGroup(AdoptionGroup.Type.MORNING, morningAdoprions));
        result.add(new AdoptionGroup(AdoptionGroup.Type.AFTERNOON, afternoonAdoprions));
        result.add(new AdoptionGroup(AdoptionGroup.Type.EVENING, eveningAdoprions));
        return result;
    }
}
