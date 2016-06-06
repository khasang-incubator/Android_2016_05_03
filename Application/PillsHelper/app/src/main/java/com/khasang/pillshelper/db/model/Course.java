package com.khasang.pillshelper.db.model;

import android.util.Log;

import com.khasang.pillshelper.db.PillsDBHelper;

import org.joda.time.DateTimeField;
import org.joda.time.Duration;
import org.joda.time.Instant;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Course {
    private int courseID;

    private Drug drug;

    /**
     * The date of begin of treatment
     */
    private Instant startDate;

    /**
     * The date of end of treatment
     */
    private Instant endDate;

    /**
     * List of time(without date) which represents intraday timestamps for take drug
     */
    private List<LocalTime> takingTime;

    /**
     * The interval which represents duration between drug takes by days
     * for instance, intervalInDays = 1 means that need to take drug every day
     * if intervalInDays = 1 - take drug every 5th day, etc
     */
    private int intervalInDays;

    public Course(int courseID, Drug drug, Instant startDate, Instant endDate, List<LocalTime> takingTime, int intervalInDays){
        this.courseID = courseID;
        this.drug = drug;
        this.startDate = startDate;
        this.endDate = endDate;
        this.takingTime = takingTime;
        this.intervalInDays = intervalInDays;
    }

    public static class Adoption implements Comparable<Adoption>{
        public Instant timestamp;
        public Drug drug;
        public Adoption(Instant timestamp, Drug drug){
            this.timestamp = timestamp;
            this.drug = drug;
        }
        @Override
        public int compareTo(Adoption another) {
            return timestamp.compareTo(another.timestamp);
        }
    }

    public static Course createCourse(Drug drug, Instant startDate, Instant endDate, List<LocalTime> takingTime, int intervalInDays){
        return PillsDBHelper.getInstance().addCourse(drug, startDate, endDate, takingTime, intervalInDays);
    }

    public static void deleteCourse(Course course){
        deleteCourse(course.courseID);
    }

    public static void deleteCourse(int courseID){
        PillsDBHelper.getInstance().deleteCourse(courseID);
    }

    public static List<Adoption> getAllAdoptionsByPeriod(Instant begin, Instant end){
        List<Adoption> result = new ArrayList<>();
        List<Course> courses = PillsDBHelper.getInstance().getCourses();
        for(Course course: courses){
            List<Instant> schedule = course.getSchedule(begin, end);
            for(Instant instant: schedule){
                result.add(new Adoption(instant, course.getDrug()));
            }
        }
        Collections.sort(result);
        return result;
    }

    /**
     * Get list of instants(in other words timestamps) which represent
     * the schedule taking drugs limited begin date and end date
     * @param begin
     * @param end
     * @return instants
     */
    public List<Instant> getSchedule(Instant begin, Instant end){
        List<Instant> instants = new ArrayList<>();
        Instant currentInstant = startDate;
        Instant endInstant = min(endDate, end);
        Duration step = Duration.standardDays(intervalInDays);
        while(!currentInstant.isAfter(endInstant)){
            if(!currentInstant.isBefore(begin)) {
                for (LocalTime time : takingTime) {
                    Instant instant = time.toDateTime(currentInstant).toInstant();
                    if(!instant.isBefore(startDate)) {
                        instants.add(instant);
                    }
                }
            }
            currentInstant = currentInstant.plus(step);
        }
        return instants;
    }

    private Instant min(Instant a, Instant b){
        return (a == null) ? b: (a.isAfter(b) ? b: a);
    }

    public Drug getDrug(){
        return this.drug;
    }

    public int getID(){
        return this.courseID;
    }

    public int getCourseID() {
        return courseID;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public List<LocalTime> getTakingTime() {
        return takingTime;
    }

    public int getIntervalInDays() {
        return intervalInDays;
    }
}