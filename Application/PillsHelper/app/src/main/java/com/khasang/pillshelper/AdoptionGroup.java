package com.khasang.pillshelper;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.khasang.pillshelper.db.model.Course;

import java.util.List;

public class AdoptionGroup implements ParentListItem {

    public enum Type {
        NIGHT,
        MORNING,
        AFTERNOON,
        EVENING
    }

    List<Course.Adoption> adoptions;
    Type type;

    public AdoptionGroup(Type type, List<Course.Adoption> adoptions){
        this.type = type;
        this.adoptions = adoptions;
    }

    @Override
    public List<?> getChildItemList() {
        return adoptions;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
