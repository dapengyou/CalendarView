package com.test.calendarview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements NewCalendar.NewCalendarListener {
    private NewCalendar mNewCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNewCalendar = findViewById(R.id.newCalendar);
        mNewCalendar.mListener = this;
    }

    @Override
    public void onItemLongPress(Date day) {
        DateFormat dateFormat = SimpleDateFormat.getDateInstance();
        Toast.makeText(this, "点击" + dateFormat.format(day), Toast.LENGTH_SHORT).show();
    }
}
