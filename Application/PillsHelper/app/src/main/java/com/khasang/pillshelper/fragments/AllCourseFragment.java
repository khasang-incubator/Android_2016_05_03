package com.khasang.pillshelper.fragments;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.khasang.pillshelper.R;
import com.roomorama.caldroid.CaldroidFragment;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllCourseFragment extends Fragment {

    public  final  static  String
            DIALOG_TITLE  =  " dialogTitle " ,
            MONTH  =  " month " ,
            YEAR  =  " year " ,
            SHOW_NAVIGATION_ARROWS  =  " showNavigationArrows " ,
            DISABLE_DATES  =  " disableDates " ,
            SELECTED_DATES  =  " selectedDates " ,
            MIN_DATE  =  " minDate " ,
            MAX_DATE  =  " maxDate " ,
            ENABLE_SWIPE  =  " enableSwipe " ,
            START_DAY_OF_WEEK  =  " startDayOfWeek " ,
            SIX_WEEKS_IN_CALENDAR  =  " sixWeeksInCalendar " ,
            ENABLE_CLICK_ON_DISABLED_DATES  =  " enableClickOnDisabledDates " ,
            SQUARE_TEXT_VIEW_CELL  =  " squareTextViewCell " ,
            THEME_RESOURCE  =  " themeResource " ;

    private CalendarView calendarView;

    public AllCourseFragment() {
        // Required empty public constructor
    }
    private AllCourseFragment frAllCorse;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_course, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        calendarView = (CalendarView)getView(). findViewById(R.id.calendarView);

        CaldroidFragment caldroidFragment = new CaldroidFragment();
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        caldroidFragment.setArguments(args);
        frAllCorse = new AllCourseFragment();

        FragmentTransaction t = getFragmentManager().beginTransaction();
        t.replace(R.id.cal, caldroidFragment);
        t.commit();

    }




}
