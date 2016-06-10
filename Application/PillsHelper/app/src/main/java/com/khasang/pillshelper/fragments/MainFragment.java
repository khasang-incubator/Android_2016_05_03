package com.khasang.pillshelper.fragments;

import android.os.Bundle;
import android.app.Fragment;
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

import java.util.Arrays;
import java.util.List;

public class MainFragment extends Fragment {
    private List<Course> allCourses;
    private CourseAdapter adapter;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allCourses = PillsDBHelper.getInstance().getCourses();

        CourseGroup group1 = new CourseGroup("Recipe 1", allCourses);

        adapter = new CourseAdapter(getActivity(), Arrays.asList(group1));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.current_courses);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        return v;
    }

}
