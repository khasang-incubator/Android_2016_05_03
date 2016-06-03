package com.khasang.pillshelper.db.model;

import org.joda.time.Duration;
import org.joda.time.Instant;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private int courseID;
    private Drug drug;
    private Instant startDate;
    private Instant endDate;
    private List<LocalTime> takingTime;
    private int intervalInDays;

    public Course(int courseID, Drug drug, Instant startDate, Instant endDate, List<LocalTime> takingTime, int intervalInDays){
        this.drug = drug;
        this.startDate = startDate;
        this.endDate = endDate;
        this.takingTime = takingTime;
        this.intervalInDays = intervalInDays;
    }

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