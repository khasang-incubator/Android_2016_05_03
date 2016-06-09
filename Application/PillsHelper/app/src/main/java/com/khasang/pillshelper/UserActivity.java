package com.khasang.pillshelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.khasang.pillshelper.db.model.Course;
import com.khasang.pillshelper.db.model.Drug;

import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import java.util.Arrays;
import java.util.List;

public class UserActivity extends AppCompatActivity {
    public static final String DRUG_LIST = "drug_list";

    private RecyclerView container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        container = (RecyclerView) findViewById(R.id.coursesView);
        container.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        Course c1 = Course.createCourse(new Drug(25), new LocalDateTime(), null, Arrays.asList(new LocalTime()), 2);
        Course c2 = Course.createCourse(new Drug(187), new LocalDateTime(), null, Arrays.asList(new LocalTime()), 2);
        Course c3 = Course.createCourse(new Drug(245), new LocalDateTime(), null, Arrays.asList(new LocalTime()), 2);
        Course c4 = Course.createCourse(new Drug(903), new LocalDateTime(), null, Arrays.asList(new LocalTime()), 2);

//        CourseCollection cc1 = new CourseCollection("Courses 1", Arrays.asList(c1, c2));
//        CourseCollection cc2 = new CourseCollection("Courses 2", Arrays.asList(c3, c4));
//        List<CourseCollection> collections = Arrays.asList(cc1, cc2);
//
//        CourseAdapter adapter = new CourseAdapter(this, collections);
//        container.setAdapter(adapter);
    }

//    private List<Course> getCourseList() {
//        int[] drugListId = getIntent().getIntArrayExtra(DRUG_LIST);
//        List<Course> courses = new ArrayList<>();
//
//        for (int id : drugListId) {
//            Course course = new Course(id);
//            long currentTime = Calendar.getInstance().getTimeInMillis();
//            course.setPeriod(currentTime, currentTime + 1200000);
//            course.setInterval(86400);
//            course.setParts(new int[]{Course.MORNING, Course.EVENING});
//            courses.add(new Course(id));
//        }
//
//        return courses;
//    }
//
//    private void createContent(List<Course> courses) {
//
//        for (Course course : courses) {
//            add(course);
//        }
//
//    }
//
//    private void add(Course course) {
//        container.addView(new CourseView(this, course));
//    }
}
