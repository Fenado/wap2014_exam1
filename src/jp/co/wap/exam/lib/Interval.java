package jp.co.wap.exam.lib;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class represents interval.
 */
public class Interval {
    /**
     * It represents Time of Hour and Minute.
     */
    private static class Time{
        final int hour;
        final int minute;

        public Time(int hour, int minute){
            this.hour = hour;
            this.minute = minute;
        }

        @Override
        public String toString(){
            return String.format("%2d:%02d", hour, minute);
        }

        @Override
        public int hashCode(){
            return toString().hashCode();
        }

        @Override
        public boolean equals(Object obj){
            if(!(obj instanceof Time)){
                return false;
            }
            Time other = (Time) obj;
            return (this.hour == other.hour && this.minute == other.minute);
        }
    }

    /** Intial point of interval. */
    private final Time begin;
    /** Terminal point of interval. */
    private final Time end;

    /** Create interval from begin time (string) adn end time (string) */
    public Interval(String begin, String end){
        this.begin = toTime(begin);
        this.end = toTime(end);
    }

    /** Convert time format (string) to Time structures. */
    private static Time toTime(String timeFormatString){
        Pattern p = Pattern.compile("(\\d?\\d):([0-5]\\d)");
        Matcher m = p.matcher(timeFormatString);
        if(!m.find()) {
            throw  new IllegalArgumentException("invalid time format.");
        }

        int hour = Integer.parseInt(m.group(1));
        int minute = Integer.parseInt(m.group(2));

        return new Time(hour, minute);
    }

    /** Get interval begin string */
    public String getBegin(){
        return this.begin.toString();
    }

    /** Get interval end string. */
    public String getEnd(){
        return this.end.toString();
    }

    /** Get interval begin hour */
    public int getBeginHour(){
        return this.begin.hour;
    }

    /** Get interval begin minute */
    public int getBeginMinute(){
        return this.begin.minute;
    }

    /** Get interval end hour */
    public int getEndHour(){
        return this.end.hour;
    }

    /** Get interval end minute */
    public int getEndMinute(){
        return this.end.minute;
    }

    /**
     * Get total time (minute) from "00:00" to initial point of interval.
     */
    public int getBeginMinuteUnit(){
        return this.begin.hour * 60 + getBeginMinute();
    }

    /**
     * Get total time (minute) from "00:00" to terminal point of interval.
     */
    public int getEndMinuteUnit(){
        return getEndHour() * 60 + getEndMinute();
    }

    /**
     * Get total time on interval.
     * That is, it returns getEndMinuteUnit() - getBeginMinuteUnit()
     */
    public int getIntervalMinute(){
        return getEndMinuteUnit() - getBeginMinuteUnit();
    }

    @Override
    public int hashCode(){
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Interval)){
            return false;
        }

        Interval other = (Interval) obj;
        return (this.begin.equals(other.begin) && this.end.equals(other.end));
    }

    @Override
    public String toString(){
        return String.format("[%s-%s]", begin, end);
    }
}
