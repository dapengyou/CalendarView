package com.test.calendarview;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by lady_zhou on 2017/11/17.
 */

public class NewCalendar extends LinearLayout {
    private ImageView mIvPrev;
    private ImageView mIvNext;
    private TextView mTvDate;

    private GridView mGvGrid;

    private Calendar curDate = Calendar.getInstance();

    public NewCalendar(Context context) {
        super(context);
    }

    public NewCalendar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initControl(context);
    }

    public NewCalendar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initControl(Context context) {
        bindControl(context);
        bindControlEvent();
        renderCantrol();
    }

    private void bindControl(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.calendar_view, this);

        mIvPrev = findViewById(R.id.iv_prev);
        mIvNext = findViewById(R.id.iv_next);
        mTvDate = findViewById(R.id.tv_date);
        mGvGrid = findViewById(R.id.calender_grid);
    }

    private void bindControlEvent() {
        mIvPrev.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                curDate.add(Calendar.MONTH, -1);//向前翻一个月
                renderCantrol();
            }
        });
        mIvNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                curDate.add(Calendar.MONTH, +1);//向后翻一个月
                renderCantrol();
            }
        });
    }

    /**
     * 渲染控件
     */
    private void renderCantrol() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM yyyy");//格式化日期
        mTvDate.setText(sdf.format(curDate.getTime()));

        ArrayList<Date> list = new ArrayList<>();
        Calendar calendar = (Calendar) curDate.clone();//为了避免混乱Title的日期

        //将月份置于当月的第一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        //每个月当月之前有多少天
        int prevDays = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        calendar.add(Calendar.DAY_OF_MONTH, -prevDays);//将当前日期的天数移到我们需要的绘制日期的第一天

        int maxCellCount = 6 * 7;
        while (list.size() < maxCellCount) {
            list.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        mGvGrid.setAdapter(new CalendarAdapter(getContext(), list));
    }

    private class CalendarAdapter extends ArrayAdapter<Date> {
        LayoutInflater inflater;

        public CalendarAdapter(@NonNull Context context, ArrayList<Date> days) {
            super(context, R.layout.calendar_text_day, days);
            inflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            Date date = getItem(position);
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.calendar_text_day, parent, false);
            }
            //获取当前的天
            int day = date.getDate();
            ((TextView) convertView).setText(String.valueOf(day));

            Date now = new Date();
            boolean isTheSameMonth = false;
            if (date.getMonth() == now.getMonth()) {
                isTheSameMonth = true;
            }
            if (isTheSameMonth) {
                ((TextView) convertView).setTextColor(Color.parseColor("#555d68"));
            } else {
                ((TextView) convertView).setTextColor(Color.parseColor("#acb3bd"));

            }

            if (date.getDate() == now.getDate() && date.getMonth() == now.getMonth() && date.getYear() == now.getYear()) {
                ((TextView) convertView).setTextColor(Color.parseColor("#ff0000"));
                ((Calendar_day_textView) convertView).isToday = true;

            }
            return convertView;
        }
    }
}
