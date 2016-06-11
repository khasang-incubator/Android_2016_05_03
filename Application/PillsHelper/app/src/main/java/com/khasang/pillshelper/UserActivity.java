package com.khasang.pillshelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.khasang.pillshelper.db.PillsDBHelper;
import com.khasang.pillshelper.db.model.Course;
import com.khasang.pillshelper.db.model.CourseGroup;

import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private CoursePagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        mAdapter = new CoursePagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mAdapter);
    }

    public static class CoursePagerAdapter extends FragmentStatePagerAdapter {

        public CoursePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new DayCoursesFragment();
            Bundle args = new Bundle();
            args.putInt(DayCoursesFragment.ARG_OBJECT, position + 1);
            fragment.setArguments(args);

            return fragment;
        }

        @Override
        public int getCount() {
            return 100;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Object" + (position + 1);
        }
    }

    public static class DayCoursesFragment extends Fragment {
        public static final String ARG_OBJECT = "object";

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.day_courses_fragment, container, false);
            Bundle args = getArguments();
            ((TextView) v.findViewById(R.id.tv_day)).setText(Integer.toString(args.getInt(ARG_OBJECT)));
            RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.courses_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(new CourseAdapter(getActivity(), getCoursesList()));
            return v;
        }

        private List<CourseGroup> getCoursesList() {
            List<Course>[] courses = new List[4];
            courses[0] = new ArrayList<>();
            courses[1] = new ArrayList<>();
            courses[2] = new ArrayList<>();
            courses[3] = new ArrayList<>();

            List<Course> allCourses = PillsDBHelper.getInstance().getCourses();
            for (Course course : allCourses) {
                List<LocalTime> times = course.getTakingTime();
                for (LocalTime time : times) {
                    int t = time.getHourOfDay();
                    if (t < 6) {
                        courses[0].add(course);
                    } else if (t < 12) {
                        courses[1].add(course);
                    } else if (t < 18) {
                        courses[2].add(course);
                    } else {
                        courses[3].add(course);
                    }
                }
            }

            CourseGroup nightGroup = new CourseGroup("Night", courses[0]);
            CourseGroup morningGroup = new CourseGroup("Morning", courses[1]);
            CourseGroup dayGroup = new CourseGroup("Day", courses[2]);
            CourseGroup eveningGroup = new CourseGroup("Evening", courses[3]);

            return Arrays.asList(nightGroup, morningGroup, dayGroup, eveningGroup);
        }
    }
}
