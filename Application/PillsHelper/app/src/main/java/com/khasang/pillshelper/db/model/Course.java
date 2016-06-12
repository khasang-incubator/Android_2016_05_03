package com.khasang.pillshelper.db.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.khasang.pillshelper.db.PillsDBHelper;

import org.joda.time.Instant;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Course {
    private int courseID;

    /**
     * The Drug for this course (one course - one drug)
     */
    private Drug drug;

    /**
     * The date of begin of treatment
     */
    private LocalDateTime startDate;

    /**
     * The date of end of treatment
     */
    private LocalDateTime endDate;

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

    public Course(int courseID, Drug drug, LocalDateTime startDate, LocalDateTime endDate,
                  List<LocalTime> takingTime, int intervalInDays){
        this.courseID = courseID;
        this.drug = drug;
        this.startDate = startDate;
        this.endDate = endDate;
        this.takingTime = takingTime;
        this.intervalInDays = intervalInDays;
    }

    /**
     * Course.Adoption class represents adoption which contains notices what drug to take and at what time
     */
    public static class Adoption implements Comparable<Adoption>{
        public LocalDateTime timestamp;
        public Drug drug;
        public Adoption(LocalDateTime timestamp, Drug drug){
            this.timestamp = timestamp;
            this.drug = drug;
        }
        @Override
        public int compareTo(@NonNull Adoption another) {
            return timestamp.compareTo(another.timestamp);
        }
    }

    public static Course createCourse(Drug drug, LocalDateTime startDate, LocalDateTime endDate, List<LocalTime> takingTime, int intervalInDays){
        return PillsDBHelper.getInstance().addCourse(drug, startDate, endDate, takingTime, intervalInDays);
    }

    public static void deleteCourse(Course course){
        deleteCourse(course.courseID);
    }

    public static void deleteCourse(int courseID){
        PillsDBHelper.getInstance().deleteCourse(courseID);
    }

    /**
     * Get list of Adoption, which represents
     * the schedule taking drugs (for all courses) limited begin date and end date
     * @param begin begin date
     * @param end end date
     * @return list of Adoption
     */
    public static List<Adoption> getAllAdoptionsByPeriod(LocalDateTime begin, LocalDateTime end){
        List<Adoption> result = new ArrayList<>();
        List<Course> courses = PillsDBHelper.getInstance().getCourses();
        for(Course course: courses){
            List<LocalDateTime> schedule = course.getSchedule(begin, end);
            for(LocalDateTime localDateTime: schedule){
                result.add(new Adoption(localDateTime, course.getDrug()));
            }
        }
        Collections.sort(result);
        return result;
    }

    /**
     * Get list of Adoption, which represents
     * the schedule taking drugs (for all courses) for a certain day
     * @param day certain day as LocalDate
     * @return list of Adoption
     */
    public static List<Adoption> getAdoptionsForDay(LocalDate day){
        return getAllAdoptionsByPeriod(day.toDateTimeAtStartOfDay().toLocalDateTime(), day.toDateTimeAtStartOfDay().toLocalDateTime().plusDays(1));
    }

    /**
     * Get list of Adoption, which represents
     * the schedule taking drugs (for all courses) for today
     * @return list of Adoption
     */
    public static List<Adoption> getAdoptionsForToday(){
        return getAdoptionsForDay(LocalDate.now());
    }

    /**
     * Get list of LocalDateTime, which represents
     * the schedule taking drug (for this course) limited begin date and end date
     * @param begin begin date
     * @param end end date
     * @return list of LocalDateTime
     */
    public List<LocalDateTime> getSchedule(LocalDateTime begin, LocalDateTime end){
        List<LocalDateTime> localDateTimes = new ArrayList<>();
        LocalDateTime currentLocalDateTime = startDate;
        LocalDateTime endLocalDateTime = min(endDate, end);
        while(!currentLocalDateTime.isAfter(endLocalDateTime)){
            for (LocalTime time : takingTime) {
                Instant baseInstant = currentLocalDateTime.toDateTime().toInstant();
                LocalDateTime localDateTime = time.toDateTime(baseInstant).toLocalDateTime();
                if(!localDateTime.isBefore(startDate) && !localDateTime.isBefore(begin)) {
                    localDateTimes.add(localDateTime);
                }
            }
            currentLocalDateTime = currentLocalDateTime.plusDays(intervalInDays);
        }
        return localDateTimes;
    }

    private LocalDateTime min(LocalDateTime a, LocalDateTime b){
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

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public List<LocalTime> getTakingTime() {
        return takingTime;
    }

    public int getIntervalInDays() {
        return intervalInDays;
    }
}