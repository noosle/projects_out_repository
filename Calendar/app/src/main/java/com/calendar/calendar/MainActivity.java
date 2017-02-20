package com.calendar.calendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.calendar.calendar.calendar_data.MonthFragment;
import com.calendar.calendar.utils.CalendarManager;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ViewPager pager;
    private static final int PAGE_COUNT = 14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        pager = (ViewPager) findViewById(R.id.pager);
        PagerAdapter pagerAdapter = new CalendarPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        pager.setCurrentItem(CalendarManager.getCalendar().get(Calendar.MONTH) + 1);

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    CalendarManager.getCalendar().set(Calendar.YEAR, CalendarManager.getCalendar().get(Calendar.YEAR) - 1);
                    pager.setCurrentItem(12, false);
                }
                if (position == 13) {
                    CalendarManager.getCalendar().set(Calendar.YEAR, CalendarManager.getCalendar().get(Calendar.YEAR) + 1);
                    pager.setCurrentItem(1, false);
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


    }

    private class CalendarPagerAdapter extends FragmentPagerAdapter {

        public CalendarPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return MonthFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0)
                return CalendarManager.getMonthNameById(getApplicationContext(), 11) + " " + (CalendarManager.getCalendar().get(Calendar.YEAR) - 1);
            if (position == 13)
                return CalendarManager.getMonthNameById(getApplicationContext(), 0) + " " + (CalendarManager.getCalendar().get(Calendar.YEAR) + 1);
            return CalendarManager.getMonthNameById(getApplicationContext(), position - 1) + " " + CalendarManager.getCalendar().get(Calendar.YEAR);
        }

    }


}
