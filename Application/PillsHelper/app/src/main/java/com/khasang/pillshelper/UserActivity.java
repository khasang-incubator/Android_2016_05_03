package com.khasang.pillshelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UserActivity extends AppCompatActivity {
    public static final String DRUG_LIST = "drug_list";

    private ViewGroup container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        container = (ViewGroup) findViewById(R.id.coursesView);

        createContent(getCourseList());
    }

    private List<Course> getCourseList() {
        int[] drugListId = getIntent().getIntArrayExtra(DRUG_LIST);
        List<Course> courses = new ArrayList<>();

        for (int id : drugListId) {
            Course course = new Course(id);
            long currentTime = Calendar.getInstance().getTimeInMillis();
            course.setPeriod(currentTime, currentTime + 1200000);
            course.setInterval(86400);
            course.setParts(new int[]{Course.MORNING, Course.EVENING});
            courses.add(new Course(id));
        }

        return courses;
    }

    private void createContent(List<Course> courses) {

        for (Course course : courses) {
            add(course);
        }

    }

    private void add(Course course) {
        container.addView(new CourseView(this, course));
    }
}
