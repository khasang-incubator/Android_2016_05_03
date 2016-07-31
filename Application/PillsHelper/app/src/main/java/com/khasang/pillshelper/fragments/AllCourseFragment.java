package com.khasang.pillshelper.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.khasang.pillshelper.DrugActivity;
import com.khasang.pillshelper.R;
import com.khasang.pillshelper.db.PillsDBHelper;
import com.khasang.pillshelper.db.model.Course;
import com.khasang.pillshelper.db.model.Drug;

import java.util.List;


public class AllCourseFragment extends android.support.v4.app.Fragment {

    private static Context context;
    private RecyclerView mRecyclerView;
    private CoursesAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public AllCourseFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_all_course, container, false);

        context = view.getContext();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.drug_list);
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new CoursesAdapter(PillsDBHelper.getInstance().getCourses());
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    static class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.ViewHolder>{

        private List<Course> courseList;

        CoursesAdapter(List<Course> courseList){
            this.courseList = courseList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.drug_item, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.setItem(courseList.get(position));
        }

        @Override
        public int getItemCount() {
            return courseList.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            public TextView mTextView;
            private Course item;

            public ViewHolder(TextView v) {
                super(v);
                mTextView = v;
                mTextView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DrugActivity.class);
                //TODO call activity for edit will be here
                intent.putExtra(DrugActivity.DRUG_ID, item.getDrug().getId());
                context.startActivity(intent);
            }

            public void setItem(Course item) {
                this.item = item;
                mTextView.setText(item.getDrug().getName());
            }
        }
    }
}
