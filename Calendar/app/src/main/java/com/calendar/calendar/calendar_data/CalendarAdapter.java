package com.calendar.calendar.calendar_data;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.calendar.calendar.R;

import java.util.ArrayList;
import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {
    private List<Day> days;
    private Context context;

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView dayNumber;
        LinearLayout dayBackground;

        ViewHolder(View v) {
            super(v);
            dayNumber = (TextView) v.findViewById(R.id.dayNumber);
            dayBackground = (LinearLayout) v.findViewById(R.id.dayBackground);

        }
    }


    public CalendarAdapter(Context context, ArrayList<Day> days) {
        this.context = context;
        this.days = days;
    }

    @Override
    public CalendarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Day day = days.get(position);

        holder.dayNumber.setText(day.getNumber());

        if (day.isPreWeekend()) {
            holder.dayBackground.setBackgroundColor(ContextCompat.getColor(context, R.color.pre_weekend));
            return;
        }

        if (day.isWeekend()) {
            holder.dayBackground.setBackgroundColor(ContextCompat.getColor(context, R.color.weekend));
            return;
        }
        if (!day.isWeekend() && day.getNumber().length() == 0) {
            holder.dayBackground.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
            return;
        }
        if (!day.isWeekend() && day.getNumber().length() > 0) {
            holder.dayBackground.setBackgroundColor(ContextCompat.getColor(context, R.color.work_day));
            return;
        }

    }

    @Override
    public int getItemCount() {
        return days.size();
    }


}
