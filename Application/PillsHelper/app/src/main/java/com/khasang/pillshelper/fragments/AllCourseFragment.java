package com.khasang.pillshelper.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khasang.pillshelper.CourseAdapter;
import com.khasang.pillshelper.R;
import com.khasang.pillshelper.db.PillsDBHelper;
import com.khasang.pillshelper.db.model.Course;
import com.khasang.pillshelper.db.model.CourseGroup;

import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllCourseFragment extends Fragment {

    public AllCourseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_all_course, container, false);

        fillFragment(v);

        return v;
    }

    void fillFragment(View view) {
        RecyclerView mRecyclerView;
        RecyclerView.LayoutManager mLayoutManager;
        CourseAdapter mAdapter;

        mRecyclerView = (RecyclerView) view.findViewById(R.id.CoursesRecycler);

        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        List<CourseGroup> groups = getCourseGroups();

        mAdapter = new CourseAdapter(getActivity(), groups);
        mRecyclerView.setAdapter(mAdapter);
    }

    @NonNull
    private List<CourseGroup> getCourseGroups() {
        List<Course>[] courses = new List[4];
        courses[0] = new ArrayList<>();
        courses[1] = new ArrayList<>();
        courses[2] = new ArrayList<>();
        courses[3] = new ArrayList<>();

        List<Course> allCourses = PillsDBHelper.getInstance().getCourses();
        for (Course course : allCourses) {
            List<LocalTime> times = course.getTakingTime();
            for (LocalTime time : times) {
                int t = time.getHourOfDay();
                if (t < 6) {
                    courses[3].add(course);
                } else if (t < 12) {
                    courses[0].add(course);
                } else if (t < 18) {
                    courses[1].add(course);
                } else {
                    courses[2].add(course);
                }
            }
        }

        CourseGroup morningGroup = new CourseGroup("Morning", courses[0]);
        CourseGroup dayGroup = new CourseGroup("Day", courses[1]);
        CourseGroup eveningGroup = new CourseGroup("Evening", courses[2]);
        CourseGroup nightGroup = new CourseGroup("Night", courses[3]);
        return Arrays.asList(morningGroup, dayGroup, eveningGroup, nightGroup);
    }

}
