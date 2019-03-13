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
    private DisplayParam mDispayParam;

    private int mDisplayMode = CustomCalendarView.DISPLAY_TYPE_SHORT;
    private List<Integer>mSelectedList = new ArrayList<>();
    private Date mToday = new Date();
    private DisplayParam defaultDisplayParam(){
        DisplayParam result = new DisplayParam();
        result.mTodaySelectedTextColor = Color.parseColor("#444444");
        result.mTodayNotSelectedTextColor = Color.parseColor("#1CB0F6");
        result.mSelectedTextColor = Color.WHITE;
        result.mNotSelectedTextColor = Color.parseColor("#444444");

        result.mTodaySelectedBackgroundColor = Color.parseColor("#FA811B");
        result.mTodayNotSelectedBackgroundColor = Color.TRANSPARENT;
        result.mSelectedBackgroundColor = Color.parseColor("#1CB0F6");
        result.mNotSelectedBackgroundColor = Color.TRANSPARENT;

        return result;
    }

    public CalendarAdapter(DisplayParam mDispayParam) {
        if(mDispayParam == null){
            this.mDispayParam = defaultDisplayParam();
        }
        else {
            this.mDispayParam = mDispayParam;
        }
    }
    public void forceRefresh(){
        rebuildData();
    }

    public void clearAllSelected(boolean forceRefresh){
        mSelectedList.clear();
        if(forceRefresh) {
            rebuildData();
        }
    }
    public void setSelected(List<Integer>selected, boolean forceRefresh){
        mSelectedList.clear();
        if(selected != null){
            mSelectedList.addAll(selected);
        }
        if(forceRefresh) {
            rebuildData();
        }
    }
    public void addMore(List<Integer>moreData, boolean forceRefresh){
        if(moreData != null && moreData.size() > 0){
            mSelectedList.addAll(moreData);
            if(forceRefresh) {
                rebuildData();
            }
        }
    }
    public void setDisplayMode(int mode, boolean forceRefresh){
        mDisplayMode = CustomCalendarView.DISPLAY_TYPE_FULL;
        if(mode != CustomCalendarView.DISPLAY_TYPE_FULL){
            mDisplayMode = CustomCalendarView.DISPLAY_TYPE_SHORT;
        }
        if(forceRefresh) {
            rebuildData();
        }
    }
    private void rebuildData(){
        dataObjects.clear();
        if(mDisplayMode == CustomCalendarView.DISPLAY_TYPE_FULL){
            dataObjects.addAll(CalendarUtil.buildData(mToday, mSelectedList));
        }
        else{
            dataObjects.addAll(CalendarUtil.buildShortData(mToday, mSelectedList));
        }
        notifyDataSetChanged();
    }
    private List<DataObject> dataObjects = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_day, viewGroup, false);
        return new DayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        DataObject dataObject = dataObjects.get(i);
        String text = dataObject.text;
        int backgroundColor;
        int textColor;
        if(dataObject.isSelected){
            if(dataObject.isToday){
                backgroundColor = mDispayParam.mTodaySelectedBackgroundColor;
                textColor = mDispayParam.mTodaySelectedTextColor;
            }
            else{
                backgroundColor = mDispayParam.mSelectedBackgroundColor;
                textColor = mDispayParam.mSelectedTextColor;
            }
        }
        else{
            if(dataObject.isToday){
                backgroundColor = mDispayParam.mTodayNotSelectedBackgroundColor;
                textColor = mDispayParam.mTodayNotSelectedTextColor;
            }
            else{
                backgroundColor = mDispayParam.mNotSelectedBackgroundColor;
                textColor = mDispayParam.mNotSelectedTextColor;
            }
        }

        ((DayViewHolder) viewHolder).bindData(text, textColor, backgroundColor);
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
        public void bindData(String text, int textColor, int backgroundColor){
            dayTextView.setBackground(DrawableUtils.circleDrawableWithColor(backgroundColor));
            dayTextView.setTextColor(textColor);
            dayTextView.setText(text);
        }
    }
    public static class DisplayParam{
        public int mTodaySelectedTextColor;
        public int mTodayNotSelectedTextColor;
        public int mNotSelectedTextColor;
        public int mSelectedTextColor;

        public int mTodayNotSelectedBackgroundColor;
        public int mTodaySelectedBackgroundColor;
        public int mNotSelectedBackgroundColor;
        public int mSelectedBackgroundColor;
    }
}
