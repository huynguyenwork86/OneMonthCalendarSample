package com.hn.onemonthcalendarsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.hn.onemonthcalendarview.CustomCalendarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    CustomCalendarView customCalendarView;
    Button mButton;
    int type = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customCalendarView = (CustomCalendarView)findViewById(R.id.calendarview);
        mButton = (Button)findViewById(R.id.btn_type);
        mButton.setText("FULL");

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(type == 0){
                    type  = 1;
                    mButton.setText("SHORT");
                }
                else{
                    type = 0;
                    mButton.setText("FULL");
                }
                customCalendarView.setDisplayType(type);
                customCalendarView.refresh();
            }
        });
        customCalendarView.setDisplayType(type);
        List<Integer>selectedDates = new ArrayList<>();
        selectedDates.add(3);
        selectedDates.add(5);
        selectedDates.add(10);
        selectedDates.add(13);
        selectedDates.add(16);
        customCalendarView.refresh(selectedDates);
    }
}
