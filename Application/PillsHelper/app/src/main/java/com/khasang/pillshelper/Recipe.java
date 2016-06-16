package com.khasang.pillshelper;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.List;

/**
 * Created by Mike on 09.06.2016.
 */
public class Recipe  implements ParentListItem {

    private String mName;

    // a recipe contains several ingredients
    private List mIngredients;

    public Recipe(String name, List ingredients) {
        mIngredients = ingredients;
        mName = name;
    }

    @Override
    public List getChildItemList() {
        return mIngredients;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }

    public String getName() {
        return mName;
    }
}