package com.khasang.pillshelper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.khasang.pillshelper.db.model.Course;
import com.khasang.pillshelper.db.model.CourseGroup;

import java.util.List;

/**
 * Created by aleksandrlihovidov on 08.06.16.
 */
public class CourseAdapter extends ExpandableRecyclerAdapter<CourseGroupViewHolder, CourseViewHolder> {
    private LayoutInflater mInflater;

    /**
     * Primary constructor. Sets up {@link #mParentItemList} and {@link #mItemList}.
     * <p/>
     * Changes to {@link #mParentItemList} should be made through add/remove methods in
     * {@link ExpandableRecyclerAdapter}
     *
     * @param parentItemList List of all {@link ParentListItem} objects to be
     *                       displayed in the RecyclerView that this
     *                       adapter is linked to
     */
    public CourseAdapter(Context context, @NonNull List<? extends ParentListItem> parentItemList) {
        super(parentItemList);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public CourseGroupViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View view = mInflater.inflate(R.layout.course_group_item, parentViewGroup, false);
        return new CourseGroupViewHolder(view);
    }

    @Override
    public CourseViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View view = mInflater.inflate(R.layout.corse_item, childViewGroup, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(CourseGroupViewHolder parentViewHolder, int position, ParentListItem parentListItem) {
        CourseGroup courseGroup = (CourseGroup) parentListItem;
        parentViewHolder.bind(courseGroup);
    }

    @Override
    public void onBindChildViewHolder(CourseViewHolder childViewHolder, int position, Object childListItem) {
        Course course = (Course) childListItem;
        childViewHolder.bind(course);
    }
}
