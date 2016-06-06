package com.khasang.pillshelper.db.model;

import org.joda.time.Duration;
import org.joda.time.Instant;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

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
        this.drug = drug;
        this.startDate = startDate;
        this.endDate = endDate;
        this.takingTime = takingTime;
        this.intervalInDays = intervalInDays;
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
        while(currentInstant.isBefore(endInstant)){
            if(!currentInstant.isBefore(begin)) {
                for (LocalTime time : takingTime) {
                    instants.add(time.toDateTime(currentInstant).toInstant());
                }
            }
            currentInstant = currentInstant.plus(step);
        }
        return instants;
    }

    private Instant min(Instant a, Instant b){
        return (a == null) ? b: (a.isAfter(b) ? b: a);
    }
}