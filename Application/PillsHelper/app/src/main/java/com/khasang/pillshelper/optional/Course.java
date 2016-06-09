package com.khasang.pillshelper.optional;

/**
 * Created by aleksandrlihovidov on 01.06.16.
 */
public class Course {
    public static final int MORNING = 1;
    public static final int DAY = 2;
    public static final int EVENING = 4;
    public static final int NIGHT = 8;

    public static final int START = 0;
    public static final int END = 1;

    private int drugId;
    private long[] period;
    private long interval;
    private int parts;

    public Course(int drugId) {
        this.drugId = drugId;
    }

    public int getDrugId() {
        return drugId;
    }

    public void setPeriod(long start, long end) {
        period = new long[2];
        period[START] = start;
        period[END] = end;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public void setParts(int[] p) {
        int result = 0;
        for (int i : p) {
            result += i;
        }
        parts = result;
    }
}
