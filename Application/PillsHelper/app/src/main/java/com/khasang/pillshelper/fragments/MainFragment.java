package com.khasang.pillshelper.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khasang.pillshelper.AdoptionAdapter;
import com.khasang.pillshelper.AdoptionGroup;
import com.khasang.pillshelper.R;
import com.khasang.pillshelper.db.model.Course;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends android.support.v4.app.Fragment{

    public MainFragment(){
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.pager, container, false);

        DayScheduleAdapter mAdapter = new DayScheduleAdapter(getActivity().getSupportFragmentManager());
        ViewPager mViewPager = (ViewPager) view.findViewById(R.id.pager);
        mViewPager.setAdapter(mAdapter);
        return view;
    }

    public static class DayScheduleAdapter extends FragmentStatePagerAdapter {

        public DayScheduleAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            android.support.v4.app.Fragment fragment = new DaySchedule();
            Bundle args = new Bundle();
            args.putInt(DaySchedule.DAY, position + 1);
            fragment.setArguments(args);

            return fragment;
        }

        @Override
        public int getCount() {
            return 100;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return LocalDate.now().plusDays(position).toString();
        }
    }

    public static class DaySchedule extends android.support.v4.app.Fragment {

        private static final String DAY = "DAY";

        private static int day = 0;

        public DaySchedule() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_main, container, false);
            Bundle bundle = getArguments();
            day = bundle.getInt("DAY");
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

        private List<AdoptionGroup> getData() {
            List<AdoptionGroup> result = new ArrayList<>();
            List<Course.Adoption> todayAdoptions = Course.getAdoptionsForDay(LocalDate.now().plusDays(day));
            List<Course.Adoption> nightAdoprions = new ArrayList<>();
            List<Course.Adoption> morningAdoprions = new ArrayList<>();
            List<Course.Adoption> afternoonAdoprions = new ArrayList<>();
            List<Course.Adoption> eveningAdoprions = new ArrayList<>();
            for (Course.Adoption adoption : todayAdoptions) {
                int hourOfDay = adoption.timestamp.getHourOfDay();
                if (hourOfDay < 6) {
                    nightAdoprions.add(adoption);
                } else if (hourOfDay < 12) {
                    morningAdoprions.add(adoption);
                } else if (hourOfDay < 18) {
                    afternoonAdoprions.add(adoption);
                } else if (hourOfDay < 24) {
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
}