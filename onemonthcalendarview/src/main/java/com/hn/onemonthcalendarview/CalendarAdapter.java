package com.hn.onemonthcalendarview;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class CalendarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int mTodayBackgroundColor;
    private int mSelectedBackgroundColor;
    private int mDisplayMode = CustomCalendarView.DISPLAY_TYPE_SHORT;

    public CalendarAdapter(int mTodayBackgroundColor, int mSelectedBackgroundColor) {
        this.mTodayBackgroundColor = mTodayBackgroundColor;
        this.mSelectedBackgroundColor = mSelectedBackgroundColor;
    }

    private List<DataObject> dataObjects = new ArrayList<>();
    void refresh(int mode, Date date, List<Integer>selectedDates){
        dataObjects.clear();
        if(date == null){
            date = new Date();
        }
        if(mode != CustomCalendarView.DISPLAY_TYPE_FULL){
            mode = CustomCalendarView.DISPLAY_TYPE_SHORT;
        }
        mDisplayMode = mode;
        if(mode == CustomCalendarView.DISPLAY_TYPE_SHORT){
            dataObjects.addAll(CalendarUtil.buildShortData(date, selectedDates));
        }
        else{
            dataObjects.addAll(CalendarUtil.buildData(date, selectedDates));
        }
        notifyDataSetChanged();
    }
    void refresh(int mode, Date date){
        dataObjects.clear();
        if(date == null){
            date = new Date();
        }
        if(mode != CustomCalendarView.DISPLAY_TYPE_FULL){
            mode = CustomCalendarView.DISPLAY_TYPE_SHORT;
        }
        mDisplayMode = mode;
        if(mode == CustomCalendarView.DISPLAY_TYPE_SHORT){
            dataObjects.addAll(CalendarUtil.buildShortData(date, null));
        }
        else{
            dataObjects.addAll(CalendarUtil.buildData(date, null));
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_day, viewGroup, false);
        return new DayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((DayViewHolder) viewHolder).bindData(dataObjects.get(i), mSelectedBackgroundColor);
    }

    @Override
    public int getItemCount() {
        return dataObjects.size();
    }

    static class DayViewHolder extends RecyclerView.ViewHolder {
        private TextView dayTextView;

        public DayViewHolder(@NonNull View itemView) {
            super(itemView);
            dayTextView = (TextView) itemView.findViewById(R.id.tv_day);
        }

        public void bindData(DataObject dataObject, int selectedBackgroundColor) {
            dayTextView.setText("");
            dayTextView.setBackground(dataObject.isSelected ? DrawableUtils.circleDrawableWithColor(selectedBackgroundColor) : null);
            if (!TextUtils.isEmpty(dataObject.text)) {
                dayTextView.setText(dataObject.text);
            }
        }
    }
}
