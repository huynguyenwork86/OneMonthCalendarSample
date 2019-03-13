package com.hn.onemonthcalendarview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * TODO: document your custom view class.
 */
public class CustomCalendarView extends LinearLayout {
    public static final int DISPLAY_TYPE_SHORT = 0;
    public static final int DISPLAY_TYPE_FULL = 1;
    private static final int SPAN_COUNT = 7;
    public static final int DEFAULT_TEXT_COLOR = Color.BLACK;
    public static final int DEFAULT_TODAY_BACKGROUND_COLOR = Color.YELLOW;
    public static final int DEFAULT_SELECTED_BACKGROUND_COLOR = Color.BLUE;

    private int mDisplayType = DISPLAY_TYPE_SHORT;//short
    private int mTextColor = DEFAULT_TEXT_COLOR;
    private int mTodayBackgroundColor = DEFAULT_TODAY_BACKGROUND_COLOR;
    private int mSelectedBackgroundColor = DEFAULT_SELECTED_BACKGROUND_COLOR;
    private Date mDate;
    private RecyclerView mRecyclerView;
    CalendarAdapter mAdapter;



    public CustomCalendarView(Context context) {
        super(context);
        init(null, 0);
    }

    public CustomCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public CustomCalendarView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.CustomCalendarView, defStyle, 0);
        int displayType = a.getInt(R.styleable.CustomCalendarView_displayStyle, DISPLAY_TYPE_SHORT);
        if(displayType != DISPLAY_TYPE_FULL){
            displayType = DISPLAY_TYPE_SHORT;
        }
        mDisplayType = displayType;
        mTextColor = a.getColor(R.styleable.CustomCalendarView_textColor, DEFAULT_TEXT_COLOR);
        mTodayBackgroundColor = a.getColor(R.styleable.CustomCalendarView_textColor, DEFAULT_TODAY_BACKGROUND_COLOR);
        mSelectedBackgroundColor = a.getColor(R.styleable.CustomCalendarView_textColor, DEFAULT_SELECTED_BACKGROUND_COLOR);
        a.recycle();

        mRecyclerView = new RecyclerView(getContext());
        mAdapter = new CalendarAdapter(null);
        mRecyclerView.setAdapter(mAdapter);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mRecyclerView.setLayoutParams(layoutParams);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), SPAN_COUNT));


        addView(mRecyclerView);
    }


    public void setDisplayType(int displayType, boolean forceRefresh) {
        if(displayType != DISPLAY_TYPE_FULL){
            displayType = DISPLAY_TYPE_SHORT;
        }
        mAdapter.setDisplayMode(displayType, forceRefresh);
    }

    public int getTextColor() {
        return mTextColor;
    }

    public void setTextColor(int mTextColor) {
        this.mTextColor = mTextColor;
    }
    public void setDate(Date aDate){
        mDate = aDate;
    }
    public void setSelected(List<Integer>dates, boolean forceRefresh){
        mAdapter.setSelected(dates, forceRefresh);
    }
}
