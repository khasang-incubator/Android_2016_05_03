package com.khasang.pillshelper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.khasang.pillshelper.db.model.Course;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;

public class AdoptionAdapter extends ExpandableRecyclerAdapter<AdoptionGroupViewHolder, AdoptionItemViewHolder>{

    private LayoutInflater mInflater;

    public AdoptionAdapter(Context context, @NonNull List<? extends ParentListItem> parentItemList) {
        super(parentItemList);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public AdoptionGroupViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.adoption_group, viewGroup, false);
        return new AdoptionGroupViewHolder(view);
    }

    @Override
    public AdoptionItemViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.adoption_item, viewGroup, false);
        return new AdoptionItemViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(AdoptionGroupViewHolder parentViewHolder, int position, ParentListItem parentListItem) {
        AdoptionGroup adoptionGroup = (AdoptionGroup) parentListItem;
        int imageId = R.drawable.ic_bt_morning;
        switch (adoptionGroup.type){
            case NIGHT:
                imageId = R.drawable.ic_bt_night;
                parentViewHolder.adoptionGroupName.setText("Ночь");
                break;
            case MORNING:
                imageId = R.drawable.ic_bt_morning;
                parentViewHolder.adoptionGroupName.setText("Утро");
                break;
            case AFTERNOON:
                imageId = R.drawable.ic_bt_sun;
                parentViewHolder.adoptionGroupName.setText("День");
                break;
            case EVENING:
                imageId = R.drawable.ic_bt_evening;
                parentViewHolder.adoptionGroupName.setText("Вечер");
                break;
        }
        parentViewHolder.image.setImageResource(imageId);
    }

    @Override
    public void onBindChildViewHolder(AdoptionItemViewHolder adoptionItemViewHolder, int i, Object o) {
        Course.Adoption adoption = (Course.Adoption) o;
        adoptionItemViewHolder.timeToTake.setText(adoption.timestamp.toString("HH:mm"));
        adoptionItemViewHolder.drugName.setText(adoption.drug.getName());
    }
}
