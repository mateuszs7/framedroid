package com.framedroid.framework.helpers;

import android.content.Context;

import com.framedroid.framework.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by mateu on 23/03/2017.
 */

public class TimeHelper {
    private Context mContext;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public TimeHelper(Context context) {
        this.mContext = context;
    }

    public long diff(long milis) {
        return diff(Calendar.getInstance().getTimeInMillis(), milis);
    }

    public long diff(long now, long then) {
        return now - then;
    }

    public long diff(Calendar calendar) {
        return diff(Calendar.getInstance().getTimeInMillis(), calendar.getTimeInMillis());
    }

    public long diff(Date date) {
        return diff(Calendar.getInstance().getTimeInMillis(), date.getTime());
    }

    public String diffFormat(long now, long then) {
        return diff_formate(diff(now, then));
    }

    public String diffFormat(Calendar calendar) {
        return diff_formate(diff(calendar));
    }

    public String diffFormat(Date date) {
        return diff_formate(diff(date));
    }

    private String diff_formate(long diff) {
        if (diff < 60)
            return diff + " " + mContext.getResources().getQuantityString(R.plurals.seconds_ago, (int) diff);
        long minTime = Math.round(diff / 60);
        if (minTime < 60)
            return minTime + " " + mContext.getResources().getQuantityString(R.plurals.minutes_ago, (int) minTime);
        long hourTime = Math.round(minTime / 60);
        if (hourTime < 24)
            return hourTime + " " + mContext.getResources().getQuantityString(R.plurals.hours_ago, (int) hourTime);
        long dayTime = Math.round(hourTime / 24);
        if (dayTime < 30)
            return dayTime + " " + mContext.getResources().getQuantityString(R.plurals.days_ago, (int) dayTime);
        long monthTime = Math.round(dayTime / 30);
        if (monthTime < 12)
            return monthTime + " " + mContext.getResources().getQuantityString(R.plurals.months_ago, (int) monthTime);
        long yearTime = Math.round(monthTime / 12);
        return yearTime + " " + mContext.getResources().getQuantityString(R.plurals.years_ago, (int) yearTime);
    }


    public Calendar toCalendar(String time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(convert(time));
        return calendar;
    }

    public Calendar toCalendar(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return calendar;
    }

    public Calendar toCalendar(int inSecons) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(inSecons * 1000);
        return calendar;
    }

    public void setFormat(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public String print(Calendar calendar, String format) {
        SimpleDateFormat format1 = new SimpleDateFormat(format);
        return format1.format(calendar.getTime());
    }

    public String print(Calendar calendar) {
        return sdf.format(calendar.getTime());
    }

    private Date convert(String date) {
        try {
            return sdf.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
