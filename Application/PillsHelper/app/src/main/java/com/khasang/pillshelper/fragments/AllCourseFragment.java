package com.khasang.pillshelper.fragments;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.khasang.pillshelper.R;
import com.khasang.pillshelper.db.PillsDBHelper;
import com.khasang.pillshelper.db.model.Course;

import java.util.ArrayList;
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

        mLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new CourseAdapter(PillsDBHelper.getInstance().getCourses());
        mRecyclerView.setAdapter(mAdapter);
    }

    static class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {
        private List<Course> courses;
        private List<Course> allCourses;

        public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
                , View.OnLongClickListener
        {
            public TextView mTextView;
            private Course item;

            public ViewHolder(TextView v) {
                super(v);
                mTextView = v;
                mTextView.setOnClickListener(this);
                mTextView.setOnLongClickListener(this);
            }

            public void setItem(Course item){
                this.item = item;
                //mTextView.setText(item.toString());
                mTextView.setText(item.getDrug().getName());
            }

            @Override
            public void onClick(View v) {
                // TODO: 04.06.16 открытые конкретного курса
                Snackbar.make(v, "Opening this course", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }

            @Override
            public boolean onLongClick(View v) {
                // TODO: удаление конкретного курса
                Snackbar.make(v, "WARNING! Removing this course", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            }
        }

        public CourseAdapter(List<Course> courses) {
            this.courses = courses;
            this.allCourses = courses;
        }

        @Override
        public CourseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.course_item, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.setItem(courses.get(position));
        }

        @Override
        public int getItemCount() {
            return courses.size();
        }
    }

}
