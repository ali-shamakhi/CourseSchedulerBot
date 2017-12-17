package com.dblab.util;

public class Convert {
    static int getWeekMinute(String weekTime) throws IllegalArgumentException {
        weekTime = weekTime.trim();
        if (!weekTime.matches(".{3,} +[0-9]{2}:[0-9]{2}")) throw new IllegalArgumentException("Weekday and Time are not in the format:\nWeekday HH:mm");
        String[] wtPart = weekTime.split(" +");
        String weekDay = wtPart[0].toLowerCase();
        String[] hm = wtPart[1].split(":");
        int hour = Integer.parseInt(hm[0]);
        int minute = Integer.parseInt(hm[1]);

        if (hour > 23 || minute > 59) throw new IllegalArgumentException("The time " + hm + " is invalid.");

        int wm;
        if (weekDay.equals("sat") || weekDay.equals("saturday"))        wm = 0;
        else if (weekDay.equals("sun") || weekDay.equals("sunday"))     wm = 1 * 24 * 60;
        else if (weekDay.equals("mon") || weekDay.equals("monday"))     wm = 2 * 24 * 60;
        else if (weekDay.equals("tue") || weekDay.equals("tuesday"))    wm = 3 * 24 * 60;
        else if (weekDay.equals("wed") || weekDay.equals("wednesday"))  wm = 4 * 24 * 60;
        else if (weekDay.equals("thu") || weekDay.equals("thursday"))   wm = 5 * 24 * 60;
        else if (weekDay.equals("fri") || weekDay.equals("friday"))     wm = 6 * 24 * 60;
        else throw new IllegalArgumentException("Unknown weekday " + weekDay);

        wm += 60 * hour + minute;
        return wm;
    }

    static String getWeekTimeString(int weekMinute) throws IllegalArgumentException {
        // TODO: implement
        throw new IllegalArgumentException();
    }
}
