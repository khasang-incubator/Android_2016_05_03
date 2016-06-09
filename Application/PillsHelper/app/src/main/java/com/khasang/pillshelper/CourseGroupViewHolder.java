package com.khasang.pillshelper;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.khasang.pillshelper.db.model.CourseGroup;

/**
 * Created by aleksandrlihovidov on 08.06.16.
 */
public class CourseGroupViewHolder extends ParentViewHolder {
    TextView mTextView;
    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public CourseGroupViewHolder(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView.findViewById(R.id.tv_collection_item);
    }

    public void bind(CourseGroup courseCollection) {
        mTextView.setText(courseCollection.getName());
    }
}
