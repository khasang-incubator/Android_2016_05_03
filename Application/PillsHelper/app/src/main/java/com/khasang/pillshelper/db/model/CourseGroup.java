package com.khasang.pillshelper.db.model;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.List;

/**
 * Created by aleksandrlihovidov on 10.06.16.
 */
public class CourseGroup implements ParentListItem {
    private List courses;
    private String mName;

    public CourseGroup(String name, List courses) {
        this.courses = courses;
        mName = name;
    }

    @Override
    public List<?> getChildItemList() {
        return courses;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    public String getName() {
        return mName;
    }
}
