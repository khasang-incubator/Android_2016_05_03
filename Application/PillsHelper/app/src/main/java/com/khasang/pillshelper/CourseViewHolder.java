package com.khasang.pillshelper;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.khasang.pillshelper.db.model.Course;

/**
 * Created by aleksandrlihovidov on 08.06.16.
 */
public class CourseViewHolder extends ChildViewHolder {
    private TextView mTextView;
    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public CourseViewHolder(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView.findViewById(R.id.tv_course_item);
    }

    public void bind(Course course) {
        mTextView.setText(course.getDrug().getName());
    }
}
