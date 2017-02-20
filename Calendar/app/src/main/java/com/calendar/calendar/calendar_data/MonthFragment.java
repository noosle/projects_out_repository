package com.calendar.calendar.calendar_data;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.calendar.calendar.R;
import com.calendar.calendar.utils.CalendarManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MonthFragment extends Fragment {

    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";

    int pageNumber;

    public static MonthFragment newInstance(int page) {
        MonthFragment monthFragment = new MonthFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        monthFragment.setArguments(arguments);
        return monthFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.month_fragment, null);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 7);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);

        TextView daysCount = (TextView) view.findViewById(R.id.daysCount);
        TextView workDaysCount = (TextView) view.findViewById(R.id.workDaysCount);
        TextView weekendDaysCount = (TextView) view.findViewById(R.id.weekendDaysCount);
        TextView fortyHours = (TextView) view.findViewById(R.id.fortyHours);
        TextView thirtySixCount = (TextView) view.findViewById(R.id.thirtySixCount);
        TextView twentyFourCount = (TextView) view.findViewById(R.id.twentyFourCount);
        TextView currentQuartal = (TextView) view.findViewById(R.id.quartal);
        TextView currentHalfYear = (TextView) view.findViewById(R.id.halfYear);
        TextView currentYear = (TextView) view.findViewById(R.id.year);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, pageNumber - 1);
        calendar.set(Calendar.YEAR, CalendarManager.getCalendar().get(Calendar.YEAR));
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        int startDayInMonth = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        Calendar gregCalendar = new GregorianCalendar(year, month, 1);
        int daysInMonth = gregCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        ArrayList<Day> days = new ArrayList<>();
        if (startDayInMonth < 7) {
            for (int i = 0; i < startDayInMonth - 1; i++) {
                days.add(new Day(0, false, false));
            }
        }

        for (int i = 1; i < daysInMonth + 1; i++) {
            if (startDayInMonth > 0)
                calendar.set(Calendar.DAY_OF_WEEK, i + startDayInMonth - 2);
            else
                calendar.set(Calendar.DAY_OF_WEEK, i - 1);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek == 5
                    || dayOfWeek == 6)
                days.add(new Day(i, true, false));
            else {
                if (CalendarManager.checkIsWeekend(month, i)) {
                    days.add(new Day(i, true, false));
                } else {
                    if (CalendarManager.checkIsWeekend(month, i + 1)) {
                        days.add(new Day(i, false, true));
                    } else {
                        days.add(new Day(i, false, false));
                    }
                }

            }
        }

        CalendarAdapter calendarAdapter = new CalendarAdapter(getContext(), days);
        recyclerView.setAdapter(calendarAdapter);

        int weekendsDays = getWeekendsDays(days, daysInMonth);
        int workDays = daysInMonth - weekendsDays;

        daysCount.setText(getString(R.string.days_count) + " " + daysInMonth);
        workDaysCount.setText(getString(R.string.work_days_count) + " " + workDays);
        weekendDaysCount.setText(getString(R.string.weekends_days_count) + " " + weekendsDays);
        fortyHours.setText(getString(R.string.forty_hours) + " " + Math.round(workDays * 8 - getPreWeekendsDays(days, daysInMonth)));
        thirtySixCount.setText(getString(R.string.thirty_six_hours) + " " +  Math.round(workDays * 7.2 - getPreWeekendsDays(days, daysInMonth)));
        twentyFourCount.setText(getString(R.string.twenty_four_hours) + " " +  Math.round(workDays * 4.8 - getPreWeekendsDays(days, daysInMonth)));
        if (month < 6) {
            currentHalfYear.setText(getString(R.string.current_half_year) + " 1");
            if (month < 3)
                currentQuartal.setText(getString(R.string.current_quartal) + " 1");
            else
                currentQuartal.setText(getString(R.string.current_quartal) + " 2");
        } else {
            currentHalfYear.setText(getString(R.string.current_half_year) + " 2");
            if (month < 9)
                currentQuartal.setText(getString(R.string.current_quartal) + " 3");
            else
                currentQuartal.setText(getString(R.string.current_quartal) + " 4");
        }
        currentYear.setText(getString(R.string.current_year) + " " + year);

        return view;
    }

    private int getWeekendsDays(ArrayList<Day> days, int daysInMonth) {
        int weekendsDays = 0;
        for (int i = 0; i < daysInMonth; i++) {
            if (days.get(i).isWeekend())
                weekendsDays++;
        }
        return weekendsDays;
    }
    private int getPreWeekendsDays(ArrayList<Day> days, int daysInMonth) {
        int preWeekendsDays = 0;
        for (int i = 0; i < daysInMonth; i++) {
            if (days.get(i).isPreWeekend())
                preWeekendsDays++;
        }
        return preWeekendsDays;
    }
}