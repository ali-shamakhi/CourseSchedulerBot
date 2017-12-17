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
        String weekDay;
        switch (weekMinute / (24 * 60)) {
            case 0:
                weekDay = "Sat";
                break;
            case 1:
                weekDay = "Sun";
                break;
            case 2:
                weekDay = "Mon";
                break;
            case 3:
                weekDay = "Tue";
                break;
            case 4:
                weekDay = "Wed";
                break;
            case 5:
                weekDay = "Thu";
                break;
            case 6:
                weekDay = "Fri";
                break;
            default:
                throw new IllegalArgumentException("weekMinute must be in range [0, " + (7 * 24 * 60 - 1) + "]");
        }
        int dayMinute = weekMinute % (24 * 60);
        int hour = dayMinute / 60;
        int minute = dayMinute % 60;
        return weekDay + " " + String.format("%02d", hour) + ":" + String.format("%02d", minute);
    }
}
