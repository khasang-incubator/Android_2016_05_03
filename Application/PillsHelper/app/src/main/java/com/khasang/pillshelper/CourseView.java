package com.khasang.pillshelper;

import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.khasang.pillshelper.db.PillsDBHelper;

/**
 * Created by aleksandrlihovidov on 02.06.16.
 */
public class CourseView extends TableLayout {
    private int imageWidth;
    private int imageHeight;
    private Course course;

    public CourseView(Context context, Course course) {
        super(context);

        this.course = course;

        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        imageWidth = metrics.widthPixels / 10;
        imageHeight = imageWidth;

        setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        );
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        createContent();
    }

    private TextView createTextViewWithText(CharSequence string) {
        TextView textView = new TextView(getContext());
        textView.setGravity(Gravity.CENTER);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setText(string);

        return textView;
    }

    private ImageView createImage() {
        ImageView imageView = new ImageView(getContext());
        imageView.setMaxWidth(imageHeight);
        imageView.setMaxHeight(imageHeight);
        imageView.setImageDrawable(getContext().getResources().getDrawable(R.mipmap.ic_launcher));
        imageView.setAdjustViewBounds(true);

        return imageView;
    }

    private TableRow createTableRowWithLabel(CharSequence label) {
        TableRow tableRow = new TableRow(getContext());
        tableRow.addView(
                createTextViewWithText(label),
                new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT)
        );

        tableRow.addView(createImage());
        tableRow.addView(createImage());
        tableRow.addView(createImage());
        tableRow.addView(createImage());
        tableRow.addView(createImage());
        tableRow.addView(createImage());
        tableRow.addView(createImage());

        return tableRow;
    }

    private void createContent() {
        setColumnStretchable(0, true);

        addView(createTextViewWithText(PillsDBHelper.getInstance().getDrugNameByID(course.getDrugId())));
        Space space = new Space(getContext());
        space.setMinimumHeight(16);
        addView(space);

        addView(createTableRowWithLabel(getContext().getString(R.string.morning_text)));
        addView(createTableRowWithLabel(getContext().getString(R.string.day_string)));
        addView(createTableRowWithLabel(getContext().getString(R.string.evening_string)));
        addView(createTableRowWithLabel(getContext().getString(R.string.night_string)));

        Space spaceBottom = new Space(getContext());
        spaceBottom.setMinimumHeight(26);
        addView(spaceBottom);
    }
}
